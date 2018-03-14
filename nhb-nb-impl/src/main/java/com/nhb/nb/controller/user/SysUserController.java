package com.nhb.nb.controller.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.nhb.nb.dataaccess.entity.SysUser;
import com.nhb.nb.dataaccess.service.user.SysUserService;
import com.nhb.nb.request.user.LoginRequest;
import com.nhb.nb.request.user.UserRequest;
import com.nhb.nb.util.MD5;
import com.nhb.nb.util.NBIoTAppConstant;
import com.nhb.utils.nhb_utils.common.DateUtil;
import com.nhb.utils.nhb_utils.common.RestResultDto;
import com.nhb.utils.nhb_utils.common.StringUtil;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @ClassName: SysUserController
 * @Description: 用户管理接口
 * @author: 徐亚辉
 * @date: 2018年1月30日 上午11:35:41
 */
@RestController
@RequestMapping("nbiot/v1/user")
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;
	
	private final static Logger logger = LoggerFactory.getLogger(SysUserService.class);

	@ApiOperation(value = "登录", notes = "参数是userName和password")
	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public RestResultDto<Object> login(@RequestBody LoginRequest request) {
		RestResultDto<Object> resultDto = new RestResultDto<Object>();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {
			String userName = request.getUserName();
			String password = request.getPassword();

			if (StringUtil.isNullOrEmpty(userName) || StringUtil.isNullOrEmpty(password)) {
				throw new Exception("用户名或密码不能为空!");
			}

			SysUser user = sysUserService.findByUserName(userName);
			if (null == user || !(MD5.md5Hex(password)).equals(user.getPassword())) {
				throw new Exception("用户名或密码错误！");
			}

			user.setLastLoginTime(DateUtil.format(new Date(), DateUtil.DATETIME_FORMAT));
			sysUserService.save(user);

			Map<String, String> response = new HashMap<String, String>();
			response.put("token", UUID.randomUUID().toString());
			response.put("role", user.getRole());
			response.put("userId", user.getUserId());
			data = response;
			msg = "登录成功";
		} catch (Exception e) {
			logger.warn("用户登录失败!");
			msg = "用户登录失败！";
			exception = e.getMessage();
			result = RestResultDto.RESULT_FAIL;
		} finally {
			resultDto.setMsg(msg);
			resultDto.setData(data);
			resultDto.setResult(result);
			resultDto.setException(exception);
		}
		return resultDto;
	}

	@ApiOperation(value = "获取用户列表", notes = "")
	@RequestMapping(value = "findList", method = { RequestMethod.POST })
	public RestResultDto<Object> findList(@RequestBody UserRequest request) {
		RestResultDto<Object> resultDto = new RestResultDto<Object>();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {
			Integer pageNo = request.getPageNo();
			Integer pageSize = request.getPageSize();
			
			if (null == pageNo || null == pageSize) {
				throw new Exception("分页条件不能为空！");
			}
			
			Pageable pageable = new PageRequest(pageNo - 1, pageSize);
			Page<SysUser> pageList = sysUserService.findByRole(NBIoTAppConstant.userRole, pageable);
			
			Map<String, Object> returnValue = Maps.newHashMap();
			returnValue.put("rows", pageList.getContent());
			returnValue.put("totalCounts", pageList.getTotalElements());
			returnValue.put("totalPage", pageList.getTotalPages());
			returnValue.put("currPage", pageNo);
			
			data = returnValue;
			msg = "查询成功";
		} catch (Exception e) {
			logger.warn("获取用户列表失败！");
			msg = "获取用户列表失败！";
			exception = e.getMessage();
			result = RestResultDto.RESULT_FAIL;
		} finally {
			resultDto.setMsg(msg);
			resultDto.setData(data);
			resultDto.setResult(result);
			resultDto.setException(exception);
		}
		return resultDto;
	}

	@ApiOperation(value = "获取用户信息", notes = "参数是userId")
	@RequestMapping(value = "findById", method = { RequestMethod.POST })
	public RestResultDto<Object> findById(@RequestBody LoginRequest request) {
		RestResultDto<Object> resultDto = new RestResultDto<Object>();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {
			String userId = request.getUserId();

			if (StringUtil.isNullOrEmpty(userId)) {
				throw new Exception("用户Id不能为空");
			}

			SysUser user = sysUserService.findByUserId(userId);
			if (null == user) {
				throw new Exception("用户不存在！");
			}
			msg = "查询成功";
			data = user;

		} catch (Exception e) {
			logger.warn("获取用户信息失败！");
			msg = "获取用户信息失败！";
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
		} finally {
			resultDto.setMsg(msg);
			resultDto.setData(data);
			resultDto.setResult(result);
			resultDto.setException(exception);
		}
		return resultDto;
	}
	
	@ApiOperation(value="添加用户信息",notes="请输入合法参数")
	@RequestMapping(value="add",method={RequestMethod.POST})
	public RestResultDto<Object> addUser(@RequestBody UserRequest request){
		RestResultDto<Object> resultDto = new RestResultDto<Object>();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {
			String userName = request.getUserName();
			String password = request.getPassword();
			String name = request.getName();
			String address = request.getAddress();
			String email = request.getEmail();
			String phone = request.getPhone();
			
			if(StringUtil.isNullOrEmpty(userName) || StringUtil.isNullOrEmpty(password)){
				throw new Exception("用户名和密码不能为空！");
			}
			
			// 用户名重复判断
			SysUser sysUser = sysUserService.findByUserName(userName);
			if(null != sysUser){
				throw new Exception("用户名已经存在，请重新输入！");
			}
			sysUser = new SysUser();
			sysUser.setUserName(userName);
			sysUser.setPassword(MD5.md5Hex(password));
			sysUser.setName(name);
			sysUser.setAddress(address);
			sysUser.setRole(NBIoTAppConstant.userRole);
			sysUser.setEmail(email);
			sysUser.setPhone(phone);
			sysUser.setCreateTime(DateUtil.format(new Date(), DateUtil.DATETIME_FORMAT));
			
			sysUserService.save(sysUser);
			msg = "新增成功！";
			data = sysUser;
			
		} catch (Exception e) {
			logger.warn("新增用户信息失败！");
			msg = "新增用户信息失败！";
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
		}finally {
			resultDto.setMsg(msg);
			resultDto.setData(data);
			resultDto.setResult(result);
			resultDto.setException(exception);
		}
		return resultDto;
		
	}

	@ApiOperation(value = "修改用户信息", notes = "请输入合法参数")
	@RequestMapping(value = "modify", method = { RequestMethod.POST })
	public RestResultDto<Object> modifyUser(@RequestBody UserRequest request) {
		RestResultDto<Object> resultDto = new RestResultDto<Object>();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {
			String userId = request.getUserId();
			String userName = request.getUserName();
			String password = request.getPassword();
			
			if (StringUtil.isNullOrEmpty(userId)) {
				throw new Exception("用户Id不能为空");
			}
			if(StringUtil.isNullOrEmpty(userName) || StringUtil.isNullOrEmpty(password)){
				throw new Exception("用户名和密码不能为空！");
			}

			SysUser user = sysUserService.findByUserId(userId);
			if (null == user) {
				throw new Exception("用户不存在！");
			}
			// 用户名重复判断
			if((user.getUserName()).equals(userName)){
				throw new Exception("用户名已经存在，请重新输入！");
			}
			
			user.setUserName(userName);
			user.setPassword(MD5.md5Hex(password));
			user.setName(request.getName());
			user.setEmail(request.getEmail());
			user.setAddress(request.getAddress());
			user.setPhone(request.getPhone());
			user = sysUserService.save(user);
			data = true;
			msg = "修改成功";

		} catch (Exception e) {
			logger.warn("修改用户信息失败！");
			msg = "修改失败";
			data = false;
			exception = e.getMessage();
			result = RestResultDto.RESULT_FAIL;
		} finally {
			resultDto.setMsg(msg);
			resultDto.setData(data);
			resultDto.setResult(result);
			resultDto.setException(exception);
		}
		return resultDto;
	}

	@ApiOperation(value = "删除用户", notes = "删除操作")
	@RequestMapping(value = "delete", method = { RequestMethod.POST })
	public RestResultDto<Object> delete(@RequestBody UserRequest request) {
		RestResultDto<Object> resultDto = new RestResultDto<Object>();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {
			String userId = request.getUserId();

			if (StringUtil.isNullOrEmpty(userId)) {
				throw new Exception("用户Id不能为空");
			}
			
			SysUser user = sysUserService.findByUserId(userId);
			if (null == user) {
				throw new Exception("用户不存在！");
			}
			sysUserService.delete(user);
			msg = "删除成功！";
			data = true;

		} catch (Exception e) {
			logger.warn("删除用户失败!");
			data = false;
			msg = "删除失败！";
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
		} finally {
			resultDto.setMsg(msg);
			resultDto.setData(data);
			resultDto.setResult(result);
			resultDto.setException(exception);
		}
		return resultDto;
	}

}

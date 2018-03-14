package com.nhb.nb.controller.area;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nhb.nb.dataaccess.entity.SysArea;
import com.nhb.nb.dataaccess.service.area.SysAreaService;
import com.nhb.nb.request.area.AreaRequest;
import com.nhb.utils.nhb_utils.common.RestResultDto;
import com.nhb.utils.nhb_utils.common.StringUtil;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @ClassName: SysAreaController
 * @Description: 区域管理接口
 * @author: 徐亚辉
 * @date: 2018年1月30日 上午11:37:27
 */
@RestController
@RequestMapping("nbiot/v1/area")
public class SysAreaController {
	
	private static final Logger logger = LoggerFactory.getLogger(SysAreaController.class);

	@Autowired
	private SysAreaService sysAreaService;
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "获取区域信息", notes = "参数是areaId")
	@RequestMapping(value = "findById", method = { RequestMethod.POST })
	public RestResultDto findById(@RequestBody AreaRequest request) {
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {
			String areaId = request.getAreaId();

			if (StringUtil.isNullOrEmpty(areaId)) {
				throw new Exception("区域Id不能为空");
			}

			SysArea area = sysAreaService.findByAreaId(areaId);
			if (null == area) {
				throw new Exception("区域不存在！");
			}
			msg = "查询成功";
			data = area;

		} catch (Exception e) {
			msg = "查询失败！";
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			logger.warn("获取区域信息失败！");
		} finally {
			resultDto.setMsg(msg);
			resultDto.setData(data);
			resultDto.setResult(result);
			resultDto.setException(exception);
		}
		return resultDto;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "区域信息修改", notes = "修改操作,请输入合法的参数")
	@RequestMapping(value = "modify", method = { RequestMethod.POST })
	public RestResultDto modify(@RequestBody AreaRequest request) {
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {
			String areaId = request.getAreaId();

			if (StringUtil.isNullOrEmpty(areaId)) {
				throw new Exception("区域Id不能为空");
			}

			SysArea area = sysAreaService.findByAreaId(areaId);
			if (null == area) {
				throw new Exception("区域不存在！");
			}

			area.setAreaId(request.getAreaId());
			area.setAreaName(request.getAreaName());
			area.setParentId(request.getParentId());
			area.setTopAreaId(request.getTopAreaId());
			area.setTenantId(request.getTenantId());
			area.setAreaType(request.getAreaType());
			sysAreaService.save(area);
			data = area;
			msg = "修改成功";

		} catch (Exception e) {
			msg = "区域信息修改失败！";
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			logger.warn("区域信息修改失败！");
		} finally {
			resultDto.setMsg(msg);
			resultDto.setData(data);
			resultDto.setResult(result);
			resultDto.setException(exception);
		}
		return resultDto;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "新增区域信息", notes = "新增操作,请输入合法的参数")
	@RequestMapping(value = "add", method = { RequestMethod.POST })
	public RestResultDto add(@RequestBody AreaRequest request) {
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {
			String areaName = request.getAreaName();
			String tenantId = request.getTenantId();

			if (StringUtil.isNullOrEmpty(areaName)) {
				throw new Exception("区域名称不能为空!");
			}
			if (StringUtil.isNullOrEmpty(tenantId)) {
				throw new Exception("租户Id不能为空！");
			}

			SysArea area = sysAreaService.findByAreaName(request.getAreaName());
			if (null != area) {
				throw new Exception("区域名称已存在！");
			}

			area = new SysArea();
			area.setAreaName(request.getAreaName());
			area.setParentId(request.getParentId());
			area.setTopAreaId(request.getTopAreaId());
			area.setTenantId(request.getTenantId());
			area.setAreaType(request.getAreaType());
			sysAreaService.save(area);
			msg = "新增成功！";
			data = area;

		} catch (Exception e) {
			msg = "新增区域信息失败！";
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			logger.warn("新增区域信息失败！");
		} finally {
			resultDto.setMsg(msg);
			resultDto.setData(data);
			resultDto.setResult(result);
			resultDto.setException(exception);
		}
		return resultDto;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "根据租户Id查询区域列表", notes = "参数是：tenantId")
	@RequestMapping(value = "findByTenantId", method = { RequestMethod.POST })
	public RestResultDto findByTenantId(@RequestBody AreaRequest request) {
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {
			String tenantId = request.getTenantId();

			if (StringUtil.isNullOrEmpty(tenantId)) {
				throw new Exception("tenantId不能为空！");
			}

			List<SysArea> areaList = sysAreaService.findByTenantId(tenantId);

			data = areaList;
			msg = "查询成功";
		} catch (Exception e) {
			msg = "查询区域列表失败！";
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			logger.warn("查询区域列表失败！");
		} finally {
			resultDto.setMsg(msg);
			resultDto.setData(data);
			resultDto.setResult(result);
			resultDto.setException(exception);
		}
		return resultDto;

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "删除区域", notes = "参数是:areaId, tenantId")
	@RequestMapping(value = "delete", method = { RequestMethod.POST })
	public RestResultDto delete(@RequestBody AreaRequest request) {
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {
			String areaId = request.getAreaId();
			String tenantId = request.getTenantId();

			if (StringUtil.isNullOrEmpty(areaId)) {
				throw new Exception("区域Id不能为空!");
			}
			if (StringUtil.isNullOrEmpty(tenantId)) {
				throw new Exception("租户Id不能为空！");
			}

			SysArea area = sysAreaService.findByAreaIdAndTenantId(areaId, tenantId);
			if (null == area) {
				throw new Exception("区域不存在！");
			}
			sysAreaService.delete(area);
			msg = "删除成功！";
			data = true;

		} catch (Exception e) {
			data = false;
			msg = "删除区域信息失败！";
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			logger.warn("删除区域信息失败!");
		} finally {
			resultDto.setMsg(msg);
			resultDto.setData(data);
			resultDto.setResult(result);
			resultDto.setException(exception);
		}
		return resultDto;
	}

}

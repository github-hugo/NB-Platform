package com.nhb.nb.controller.user;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.nhb.nb.dataaccess.entity.DeviceInfo;
import com.nhb.nb.dataaccess.entity.SysArea;
import com.nhb.nb.dataaccess.service.area.SysAreaService;
import com.nhb.nb.dataaccess.service.device.DeviceService;
import com.nhb.nb.request.user.LoginRequest;
import com.nhb.utils.nhb_utils.common.RestResultDto;
import com.nhb.utils.nhb_utils.common.StringUtil;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @ClassName:  HomeController   
 * @Description: 首页数据接口   
 * @author: 徐亚辉
 * @date:   2018年3月8日 上午9:08:00
 */
@RestController
@RequestMapping("nbiot/v1/home")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private SysAreaService sysAreaService;
	
	@Autowired
	private DeviceService deviceService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "根据用户Id获取设备列表信息",notes = "参数是userId")
	@RequestMapping(value = "findDeviceListByUserId",method = {RequestMethod.POST})
	public RestResultDto findDeviceListByUserId(@RequestBody LoginRequest request){
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String exception = null;
		Object data = null;
		String msg = null;
		try {
			String userId = request.getUserId();
			
			if(StringUtil.isNullOrEmpty(userId)){
				throw new Exception("userId不能为空！");
			}
			
			List<SysArea> areaList = sysAreaService.findByTenantId(userId);
			List<DeviceInfo> deviceInfos = Lists.newArrayList();
			if(CollectionUtils.isNotEmpty(areaList)){
				List<String> areaIds = Lists.newArrayList();
				for(SysArea area : areaList){
					areaIds.add(area.getAreaId());
				}
				deviceInfos = deviceService.findByEndUserIdIn(areaIds);
			}
			
			data = deviceInfos;
			msg = "查询成功!";
			
		} catch (Exception e) {
			logger.warn("获取用户下设备列表信息失败!");
			msg = "获取用户下设备列表信息失败!";
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
	
}

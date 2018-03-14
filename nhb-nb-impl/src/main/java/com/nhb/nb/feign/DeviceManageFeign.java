package com.nhb.nb.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nhb.nb.feign.exception.DeviceManageFeignEx;
import com.nhb.nb.request.device.DeleteDeviceRequest;
import com.nhb.nb.request.device.RegisterDeviceRequest;
import com.nhb.nb.request.device.UpdateDeviceInfoRequest;
import com.nhb.utils.nhb_utils.common.RestResultDto;

// @FeignClient("https://nbiot-app")
@FeignClient(value = "nbiot-app", fallback = DeviceManageFeignEx.class)
public interface DeviceManageFeign {
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/nhb/nbiot/device/manage/v1/registerDirectlyConnectedDevice", 
	method={RequestMethod.POST})
	public RestResultDto registerDirectlyConnectedDevice(@RequestBody RegisterDeviceRequest registerDevice);
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/nhb/nbiot/device/manage/v1/modifyDeviceInfo",
	method={RequestMethod.POST})
	public RestResultDto modifyDeviceInfo(@RequestBody UpdateDeviceInfoRequest updateDeviceInfo);
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/nhb/nbiot/device/manage/v1/deleteDirectlyConnectedDevice", 
	method={RequestMethod.POST})
	public RestResultDto deleteDirectlyConnectedDevice(@RequestBody DeleteDeviceRequest deleteDevice);
	
}

package com.nhb.nb.feign.exception;

import org.springframework.stereotype.Component;

import com.nhb.nb.feign.DeviceManageFeign;
import com.nhb.nb.request.device.DeleteDeviceRequest;
import com.nhb.nb.request.device.RegisterDeviceRequest;
import com.nhb.nb.request.device.UpdateDeviceInfoRequest;
import com.nhb.utils.nhb_utils.common.RestResultDto;

@Component
public class DeviceManageFeignEx implements DeviceManageFeign {
	
	@SuppressWarnings("rawtypes")
	public RestResultDto registerDirectlyConnectedDevice(RegisterDeviceRequest registerDevice) {
		RestResultDto resultDto = new RestResultDto();
		resultDto.setMsg("DeviceInfo 注册失败！");
		return resultDto;
	}
	
	
	@SuppressWarnings("rawtypes")
	public RestResultDto modifyDeviceInfo(UpdateDeviceInfoRequest updateDeviceInfo) {
		RestResultDto resultDto = new RestResultDto();
		resultDto.setMsg("DeviceInfo 修改失败！");
		return resultDto;
	}


	@SuppressWarnings("rawtypes")
	public RestResultDto deleteDirectlyConnectedDevice(DeleteDeviceRequest deleteDevice) {
		RestResultDto resultDto = new RestResultDto();
		resultDto.setMsg("DeviceInfo 删除失败！");
		return resultDto;
	}

}

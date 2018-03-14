package com.nhb.nb.controller.device;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nhb.nb.dataaccess.entity.DeviceInfo;
import com.nhb.nb.dataaccess.service.device.DeviceCircuitService;
import com.nhb.nb.dataaccess.service.device.DeviceService;
import com.nhb.nb.dto.RegisterResDto;
import com.nhb.nb.dto.UpdateDeviceInfoReqDTO;
import com.nhb.nb.feign.DeviceManageFeign;
import com.nhb.nb.request.area.AreaRequest;
import com.nhb.nb.request.device.DeleteDeviceRequest;
import com.nhb.nb.request.device.DeviceRequest;
import com.nhb.nb.request.device.RegisterDeviceRequest;
import com.nhb.nb.request.device.UpdateDeviceInfoRequest;
import com.nhb.nb.util.NBIoTAppConstant;
import com.nhb.utils.nhb_utils.common.RestResultDto;
import com.nhb.utils.nhb_utils.common.StringUtil;
import com.nhb.utils.nhb_utils.json.JsonMapper;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @ClassName:  DeviceController   
 * @Description: 设备管理接口  
 * @author: 徐亚辉
 * @date:   2018年1月30日 下午3:10:49
 */
@RestController
@RequestMapping("nbiot/v1/device")
public class DeviceController {
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private DeviceCircuitService deviceCircuitService;
	
	@Autowired
	private DeviceManageFeign deviceManageFeign;
	
	private final static Logger logger = LoggerFactory.getLogger(DeviceController.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value="根据deviceId查询设备信息",notes="参数是设备Id")
	@RequestMapping(value="findById",method={RequestMethod.POST})
	public RestResultDto findById(@RequestBody DeviceRequest request){
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {
			String deviceId = request.getDeviceId();
			
			if(StringUtil.isNullOrEmpty(deviceId)){
				throw new Exception("设备Id不能为空!");
			}
			
			DeviceInfo device = deviceService.findByDeviceId(deviceId);
			if(null == device){
				throw new Exception("设备不存在!");
			}
			data = device;
			msg = "查询成功";
			
		} catch (Exception e) {
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			msg = "查询失败！";
			logger.warn("查询设备信息失败!");
		} finally {
			resultDto.setData(data);
			resultDto.setException(exception);
			resultDto.setMsg(msg);
			resultDto.setResult(result);
		}
		return resultDto;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "根据区域Id查询设备列表", notes = "参数是:areaId")
	@RequestMapping(value = "findByAreaId", method = { RequestMethod.POST })
	public RestResultDto findByAreaId(@RequestBody AreaRequest request) {
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {
			String areaId = request.getAreaId();

			if (StringUtil.isNullOrEmpty(areaId)) {
				throw new Exception("areaId不能为空！");
			}

			List<String> areaIds = Lists.newArrayList();
			areaIds.add(areaId);
			List<DeviceInfo> deviceInfos = deviceService.findByEndUserIdIn(areaIds);

			data = deviceInfos;
			msg = "查询成功";
		} catch (Exception e) {
			msg = "查询区域下属的设备列表失败！";
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			logger.warn("查询区域下属的设备列表失败！");
		} finally {
			resultDto.setMsg(msg);
			resultDto.setData(data);
			resultDto.setResult(result);
			resultDto.setException(exception);
		}
		return resultDto;

	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="修改设备信息",notes="请输入合法的参数")
	@RequestMapping(value="modify",method=RequestMethod.POST)
	public RestResultDto modify(@RequestBody DeviceRequest request){
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = "DeviceInfo修改失败！";
		Object data = false;
		String exception = null;
		try {
			String deviceId = request.getDeviceId();
			
			if(StringUtil.isNullOrEmpty(deviceId)){
				throw new Exception("设备Id不能为空!");
			}
			DeviceInfo device = deviceService.findByDeviceId(deviceId);
			if(null == device){
				throw new Exception("设备不存在!");
			}
			
			UpdateDeviceInfoRequest updateDeviceInfo = getUpdateDeviceInfo(request);
			resultDto = deviceManageFeign.modifyDeviceInfo(updateDeviceInfo);
			
			// 本地更新经纬度信息、区域Id信息
			device.setLatitude(request.getLatitude());
			device.setLongitude(request.getLongitude());
			device.setGpsLat(request.getGpsLat());
			device.setGpsLng(request.getGpsLng());
			device.setEndUserId(request.getEndUserId());
			deviceService.save(device);
			
			if(resultDto.getResult() == 0){
				msg = "DeviceInfo修改成功！";
				data = true;
			}
			
		} catch (Exception e) {
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			msg = "DeviceInfo修改失败！";
			logger.warn("DeviceInfo修改失败！");
		} finally {
			resultDto.setMsg(msg);
			resultDto.setData(data);
			resultDto.setException(exception);
			resultDto.setResult(result);
		}
		return resultDto;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value="新增设备信息",notes="请输入合法的参数")
	@RequestMapping(value="add", method={RequestMethod.POST})
	public RestResultDto add(@RequestBody DeviceRequest request){
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = "DeviceInfo新建失败";
		Object data = false;
		String exception = null;
		try {
			String name = request.getName();
			String nodeId = request.getNodeId();
			
			if(StringUtil.isNullOrEmpty(nodeId)){
				throw new Exception("nodeId不能为空！");
			}
			if(StringUtil.isNullOrEmpty(name)){
				throw new Exception("设备名称不能为空");
			}
			
			DeviceInfo deviceInfo = deviceService.findByName(name);
			if(null != deviceInfo){
				throw new Exception("设备名称已经存在！");
			}
			deviceInfo = deviceService.findByNodeId(nodeId); 
			if(null != deviceInfo){
				throw new Exception("nodeId已经存在！");
			}
			
			// 注册
			RegisterDeviceRequest registerDevice = new RegisterDeviceRequest();
			registerDevice.setNodeId(request.getNodeId());
			registerDevice.setVerifyCode(request.getVerifyCode());
			registerDevice.setEndUserId(request.getEndUserId());
			registerDevice.setTimeout(NBIoTAppConstant.TIMEOUT);
			registerDevice.setLatitude(request.getLatitude());
			registerDevice.setLongitude(request.getLongitude());
			registerDevice.setGpsLat(request.getGpsLat());
			registerDevice.setGpsLng(request.getGpsLng());
			resultDto = deviceManageFeign.registerDirectlyConnectedDevice(registerDevice);
			
			// 修改
			JsonMapper jsonMapper = new JsonMapper();
			RegisterResDto registerResDto = jsonMapper.convertValue(resultDto.getData(), RegisterResDto.class);
			//JsonUtil.convertJsonStringToObject(jsonMapper.toJson(resultDto.getData()), RegisterResDto.class);
			UpdateDeviceInfoRequest updateDeviceInfo = getUpdateDeviceInfo(request);
			updateDeviceInfo.setDeviceId(registerResDto.getDeviceId());
			resultDto = deviceManageFeign.modifyDeviceInfo(updateDeviceInfo);
			
			if(resultDto.getResult() == 0) {
				msg = "DeviceInfo新建成功！";
				Map<String, String> map = Maps.newHashMap();
				map.put("deviceId", registerResDto.getDeviceId());
				data = map;
			}
			
		} catch (Exception e) {
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			msg = "DeviceInfo新建失败！";
			logger.warn("DeviceInfo新建失败！");
		} finally {
			resultDto.setData(data);
			resultDto.setException(exception);
			resultDto.setMsg(msg);
			resultDto.setResult(result);
		}
		return resultDto;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value="删除设备信息",notes="参数是:deviceId")
	@RequestMapping(value="delete",method={RequestMethod.POST})
	public RestResultDto delete(@RequestBody DeviceRequest request){
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = "DeviceInfo删除失败！";
		Object data = false;
		String exception = null;
		try {
			String deviceId = request.getDeviceId();
			
			if(StringUtil.isNullOrEmpty(deviceId)){
				throw new Exception("deviceId不能为空！");
			}
			
			// 删除设备之前，先删除下面所有的回路
			deviceCircuitService.deleteByDeviceId(deviceId);
			// 删除设备
			DeleteDeviceRequest deleteDevice = new DeleteDeviceRequest();
			deleteDevice.setDeviceId(deviceId);
			deleteDevice.setCascade(NBIoTAppConstant.CASCADE);
			resultDto = deviceManageFeign.deleteDirectlyConnectedDevice(deleteDevice);
			if(resultDto.getResult() == 0){
				msg = "DeviceInfo删除成功！";
				data = true;
			}
			
		} catch (Exception e) {
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			msg = "DeviceInfo删除失败！";
			logger.warn("DeviceInfo删除失败！");
		} finally {
			resultDto.setData(data);
			resultDto.setException(exception);
			resultDto.setMsg(msg);
			resultDto.setResult(result);
		}
		return resultDto;
		
	}

	private UpdateDeviceInfoRequest getUpdateDeviceInfo(DeviceRequest request) {
		UpdateDeviceInfoRequest updateDeviceInfo = new UpdateDeviceInfoRequest();
		UpdateDeviceInfoReqDTO deviceDto = new UpdateDeviceInfoReqDTO();
		deviceDto.setName(request.getName());
		deviceDto.setManufacturerId(request.getManufacturerId());
		deviceDto.setManufacturerName(request.getManufacturerName());
		deviceDto.setLocation(request.getLocation());
		deviceDto.setDeviceType(request.getDeviceType());
		deviceDto.setModel(request.getModel());
		deviceDto.setProtocolType(request.getProtocolType());
		deviceDto.setEndUser(request.getEndUserId());//区域Id
		deviceDto.setOrganization(request.getOrganization());
		updateDeviceInfo.setDeviceId(request.getDeviceId());
		updateDeviceInfo.setRequest(deviceDto);
		return updateDeviceInfo;
	}
	
}

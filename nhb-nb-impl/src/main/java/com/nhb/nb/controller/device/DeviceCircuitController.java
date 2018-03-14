package com.nhb.nb.controller.device;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nhb.nb.dataaccess.entity.DeviceCircuit;
import com.nhb.nb.dataaccess.service.device.DeviceCircuitService;
import com.nhb.nb.request.device.DeviceCircuitRequest;
import com.nhb.utils.nhb_utils.common.RestResultDto;
import com.nhb.utils.nhb_utils.common.StringUtil;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @ClassName:  DeviceCircuitController   
 * @Description: 设备回路接口  
 * @author: 徐亚辉
 * @date:   2018年3月7日 下午6:48:54
 */
@RestController
@RequestMapping("nbiot/v1/deviceCircuit")
public class DeviceCircuitController {
	
	@Autowired
	private DeviceCircuitService deviceCircuitService;
	
	private final static Logger logger = LoggerFactory.getLogger(DeviceCircuitController.class);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value="根据circuitId查询设备信息",notes="参数是circuitId")
	@RequestMapping(value="findByCircuitId",method={RequestMethod.POST})
	public RestResultDto findByCircuitId(@RequestBody DeviceCircuitRequest request){
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {
			String circuitId = request.getCircuitId();
			
			if(StringUtil.isNullOrEmpty(circuitId)){
				throw new Exception("回路Id不能为空！");
			}
			
			DeviceCircuit deviceCircuit = deviceCircuitService.findByCircuitId(circuitId);
			if(null == deviceCircuit){
				throw new Exception("回路不存在！");
			}
			data = deviceCircuit;
			msg = "查询成功！";
			
		} catch (Exception e) {
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			msg = "查询失败！";
			logger.warn("查询回路信息失败!");
		} finally {
			resultDto.setData(data);
			resultDto.setException(exception);
			resultDto.setMsg(msg);
			resultDto.setResult(result);
		}
		return resultDto;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="获取设备下面的回路列表信息",notes="参数是deviceId")
	@RequestMapping(value="findListByDeviceId",method={RequestMethod.POST})
	public RestResultDto findListByDeviceId(@RequestBody DeviceCircuitRequest request){
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {
			String deviceId = request.getDeviceId();
			
			if(StringUtil.isNullOrEmpty(deviceId)){
				throw new Exception("设备Id不能为空！");
			}
			
			List<DeviceCircuit> deviceCircuits = deviceCircuitService.findByDeviceId(deviceId);
			data = deviceCircuits;
			msg = "查询成功！";
			
		} catch (Exception e) {
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			msg = "查询失败！";
			logger.warn("获取回路列表信息失败！");
		} finally {
			resultDto.setData(data);
			resultDto.setException(exception);
			resultDto.setMsg(msg);
			resultDto.setResult(result);
		}
		return resultDto;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value="修改回路信息",notes="请输入合法的参数")
	@RequestMapping(value="modify",method=RequestMethod.POST)
	public RestResultDto modify(@RequestBody DeviceCircuit request){
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = false;
		String exception = null;
		try {
			String circuitId = request.getCircuitId();
			
			if(StringUtil.isNullOrEmpty(circuitId)){
				throw new Exception("回路Id不能为空！");
			}
			DeviceCircuit deviceCircuit = deviceCircuitService.findByCircuitId(circuitId);
			if(null == deviceCircuit){
				throw new Exception("回路不存在！");
			}
			
			deviceCircuit.setName(request.getName());
			deviceCircuitService.save(deviceCircuit);
			data = deviceCircuit;
			msg = "修改成功！";
			
		} catch (Exception e) {
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			msg = "修改失败！";
			logger.warn("修改回路信息失败！");
		} finally {
			resultDto.setData(data);
			resultDto.setException(exception);
			resultDto.setMsg(msg);
			resultDto.setResult(result);
		}
		return resultDto;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value="新增回路信息",notes="请输入合法的参数")
	@RequestMapping(value="add",method=RequestMethod.POST)
	public RestResultDto add(@RequestBody DeviceCircuit request){
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = false;
		String exception = null;
		try {
			String deviceId = request.getDeviceId();
			String circuitNo = request.getCircuitNo();
			
			if(StringUtil.isNullOrEmpty(deviceId) || StringUtil.isNullOrEmpty(circuitNo)){
				throw new Exception("设备Id和回路编号不能为空！");
			}
			DeviceCircuit deviceCircuit = new DeviceCircuit();
			deviceCircuit.setCircuitId(deviceId+circuitNo);
			deviceCircuit.setCircuitNo(circuitNo);
			deviceCircuit.setDeviceId(deviceId);
			deviceCircuit.setName(request.getName());
			deviceCircuitService.save(deviceCircuit);
			data = deviceCircuit;
			msg = "新增成功！";
			
		} catch (Exception e) {
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			msg = "新增失败！";
		} finally {
			resultDto.setData(data);
			resultDto.setException(exception);
			resultDto.setMsg(msg);
			resultDto.setResult(result);
		}
		return resultDto;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value="删除回路信息",notes="参数是circuitId")
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public RestResultDto delete(@RequestBody DeviceCircuit request){
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = false;
		String exception = null;
		try {
			String circuitId = request.getCircuitId();
			
			if(StringUtil.isNullOrEmpty(circuitId)){
				throw new Exception("回路Id不能为空！");
			}
			deviceCircuitService.deleteById(circuitId);
			msg = "删除成功！";
			
		} catch (Exception e) {
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			msg = "删除失败！";
		} finally {
			resultDto.setData(data);
			resultDto.setException(exception);
			resultDto.setMsg(msg);
			resultDto.setResult(result);
		}
		return resultDto;
	}
	
}

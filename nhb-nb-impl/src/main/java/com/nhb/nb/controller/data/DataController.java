package com.nhb.nb.controller.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nhb.nb.dataaccess.entity.DeviceCircuit;
import com.nhb.nb.dataaccess.entity.DeviceInfo;
import com.nhb.nb.dataaccess.entity.HistoryData;
import com.nhb.nb.dataaccess.entity.RealtimeData;
import com.nhb.nb.dataaccess.service.data.HistoryDataService;
import com.nhb.nb.dataaccess.service.data.RealtimeDataService;
import com.nhb.nb.dataaccess.service.device.DeviceCircuitService;
import com.nhb.nb.dataaccess.service.device.DeviceService;
import com.nhb.nb.dto.DataElectricityDTO;
import com.nhb.nb.request.data.DataHistoryRequest;
import com.nhb.nb.request.data.DataRealtimeRequest;
import com.nhb.nb.util.FileUtil;
import com.nhb.nb.util.JsonUtil;
import com.nhb.utils.nhb_utils.common.DateUtil;
import com.nhb.utils.nhb_utils.common.RestResultDto;
import com.nhb.utils.nhb_utils.common.StringUtil;
import com.nhb.utils.nhb_utils.json.JsonMapper;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("nbiot/v1/data")
public class DataController {

	@Autowired
	private HistoryDataService historyDataService;

	@Autowired
	private RealtimeDataService realtimeDataService;
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private DeviceCircuitService deviceCircuitService;
	
	private static final Logger logger = LoggerFactory.getLogger(DataController.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "查询历史数据")
	@RequestMapping(value = "history", method = { RequestMethod.POST })
	public RestResultDto findHistoryData(@RequestBody DataHistoryRequest request) {
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {
			Map<String, Object> returnValue = Maps.newHashMap();

			Integer pageNo = request.getPageNo();
			Integer pageSize = request.getPageSize();
			String startTime = request.getStartTime();
			String endTime = request.getEndTime();
			String circuitId = request.getCircuitId();
			String tenantId = request.getTenantId();

			if (null == pageNo || null == pageSize) {
				throw new Exception("分页条件不能为空！");
			}
			if (StringUtil.isNullOrEmpty(tenantId)) {
				throw new Exception("租户不能为空！");
			}
			if (StringUtil.isNullOrEmpty(circuitId)) {
				throw new Exception("设备不能为空！");
			}
			if (StringUtil.isNullOrEmpty(startTime) || StringUtil.isNullOrEmpty(endTime)) {
				throw new Exception("时间区间不能为空！");
			}

			Date startDate = DateUtil.parse(startTime, DateUtil.DATETIME_FORMAT);
			Date endDate = DateUtil.parse(endTime, DateUtil.DATETIME_FORMAT);
			Pageable pageable = new PageRequest(pageNo - 1, pageSize, new Sort(Direction.DESC, "readTime"));
			Page<HistoryData> pageList = historyDataService.findByDeviceIdAndMeterTimeBetween(circuitId, startDate,
					endDate, pageable);

			returnValue.put("rows", pageList.getContent());
			returnValue.put("totalCounts", pageList.getTotalElements());
			returnValue.put("totalPage", pageList.getTotalPages());
			returnValue.put("currPage", pageNo);

			data = returnValue;

			msg = "查询成功！";

		} catch (Exception e) {
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			msg = "查询历史数据失败！";
			logger.warn("查询历史数据失败！");
		} finally {
			resultDto.setData(data);
			resultDto.setException(exception);
			resultDto.setMsg(msg);
			resultDto.setResult(result);
		}
		return resultDto;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "查询实时数据")
	@RequestMapping(value = "realtime", method = { RequestMethod.POST })
	public RestResultDto findRealtimeData(@RequestBody DataRealtimeRequest request) {
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {

			String tenantId = request.getTenantId();
			if (StringUtil.isNullOrEmpty(tenantId)) {
				throw new Exception("租户不能为空！");
			}

			List<String> circuitIds = request.getCircuitIds();
			List<RealtimeData> list = realtimeDataService.findByDeviceIdIn(circuitIds);

			data = list;
			msg = "查询成功！";

		} catch (Exception e) {
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			msg = "查询实时数据失败！";
			logger.warn("查询实时数据失败！");
		} finally {
			resultDto.setData(data);
			resultDto.setException(exception);
			resultDto.setMsg(msg);
			resultDto.setResult(result);
		}
		return resultDto;
	}
	
	@ApiOperation(value = "数据导出", 
			notes = "新页面打开网址：http://localhost:9091/nbiot/v1/data/exportData?"
					+ "circuitIds=2d848f0a-807b-4809-9a7d-173c9462ad6f1,"
					+ "4d66d6ea-a694-48a0-b8c2-5d8acc36ed4f1,"
					+ "55415e45-8c2a-44c5-834b-c8304b8838f01&"
					+ "startTime=2018-03-01&endTime=2018-03-10")
	@RequestMapping(value = "exportData",method={RequestMethod.GET})
	public String exportData(@QueryParam("circuitIds") String circuitIds, @QueryParam("startTime") String startTime,
			@QueryParam("endTime") String endTime,HttpServletResponse response) {
		String message = null;
		try {
			if(StringUtil.isNullOrEmpty(circuitIds)){
				throw new Exception("circuitIds不能为空!");
			}
			if(StringUtil.isNullOrEmpty(startTime) || StringUtil.isNullOrEmpty(endTime)){
				throw new Exception("日期条件不能为空!");
			}
			
			String[] circuitIdArray = circuitIds.split(",");
			List<String> circuitIdList = new ArrayList<String>(Arrays.asList(circuitIdArray));
			Date startDate = DateUtil.parse(startTime +" 00:00:00", DateUtil.DATETIME_FORMAT);
			Date endDate = DateUtil.parse(endTime + " 23:59:59", DateUtil.DATETIME_FORMAT);
			
			// 获取deviceId与设备名称、回路名称的对应关系
			List<Map<String, String>> deviceIdNameMapList = Lists.newArrayList();
			Map<String, String> deviceIdNameMap;
			List<String> deviceIds = Lists.newArrayList();
			
			List<DeviceCircuit> deviceCircuits = deviceCircuitService.findByCircuitIdIn(circuitIdList);
			if(CollectionUtils.isNotEmpty(deviceCircuits)){
				for(DeviceCircuit deviceCircuit : deviceCircuits){
					deviceIdNameMap = Maps.newHashMap();
					deviceIds.add(deviceCircuit.getDeviceId());
					deviceIdNameMap.put("deviceId",  deviceCircuit.getDeviceId());
					deviceIdNameMap.put("circuitId",  deviceCircuit.getCircuitId());
					deviceIdNameMap.put("circuitName", deviceCircuit.getName());
					deviceIdNameMapList.add(deviceIdNameMap);
				}
				
				List<DeviceInfo> deviceInfos = deviceService.findByDeviceIdIn(deviceIds);
				for(Map<String, String> nameMap : deviceIdNameMapList){
					for(DeviceInfo deviceInfo : deviceInfos){
						if((deviceInfo.getDeviceId()).equals(nameMap.get("deviceId"))){
							nameMap.put("deviceName", deviceInfo.getName());
						}
					}
				}
			}
			

			List<HistoryData> list = historyDataService.findByDeviceIdInAndMeterTimeBetweenOrderByMeterTimeDesc(circuitIdList,startDate,endDate);
			List<DataElectricityDTO> exportData = transferData(list,deviceIdNameMapList);
			
			if(CollectionUtils.isNotEmpty(exportData)){
				FileUtil.exportExcel(exportData, "数据", "数据一览", DataElectricityDTO.class, "历史数据"+ DateUtil.format(new Date(), DateUtil.DATE_FORMAT)+".xls", response);
				message = "导出成功！";
			}else{
				message = "导出失败！";
				throw new Exception("导出失败!");
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return message;
	}
	
	public List<DataElectricityDTO> transferData(List<HistoryData> historyDataList,List<Map<String, String>> deviceIdNameMapList){
		List<DataElectricityDTO> exportData = Lists.newArrayList();
		JsonMapper jsonMapper = new JsonMapper();
		try {
			if(CollectionUtils.isNotEmpty(historyDataList)){
				DataElectricityDTO dataElectricityDTO;
				String newJson = null;
				for(HistoryData historyData : historyDataList){
					for(Map<String, String> nameMap : deviceIdNameMapList){
						if((historyData.getDeviceId().equals(nameMap.get("circuitId")))){
							newJson = "{\"deviceId\":\"" + historyData.getDeviceId() + "\","
									+ "\"deviceName\":\"" + nameMap.get("deviceName") +  "\","
									+ "\"circuitName\":\"" + nameMap.get("circuitName") +  "\","
								    + jsonMapper.toJson(historyData.getData()).substring(1);
						}
					}
					dataElectricityDTO = JsonUtil.convertJsonStringToObject(newJson, DataElectricityDTO.class);
					exportData.add(dataElectricityDTO);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exportData;
	}


}

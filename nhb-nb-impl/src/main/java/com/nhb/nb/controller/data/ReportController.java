package com.nhb.nb.controller.data;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nhb.nb.dataaccess.entity.DeviceCircuit;
import com.nhb.nb.dataaccess.entity.DeviceInfo;
import com.nhb.nb.dataaccess.entity.EnergyDay;
import com.nhb.nb.dataaccess.entity.SysArea;
import com.nhb.nb.dataaccess.service.area.SysAreaService;
import com.nhb.nb.dataaccess.service.data.EnergyDayService;
import com.nhb.nb.dataaccess.service.device.DeviceCircuitService;
import com.nhb.nb.dataaccess.service.device.DeviceService;
import com.nhb.nb.dto.ExportEnergyDTO;
import com.nhb.nb.enums.DateTypeEnum;
import com.nhb.nb.request.data.EnergyDataRequest;
import com.nhb.nb.request.data.EnergyDayRequest;
import com.nhb.nb.util.FileUtil;
import com.nhb.utils.nhb_utils.common.RestResultDto;
import com.nhb.utils.nhb_utils.common.StringUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("nbiot/v1/report")
public class ReportController {

	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	private EnergyDayService energyDayService;

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private SysAreaService sysAreaService;

	@Autowired
	private DeviceCircuitService deviceCircuitService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "根据区域id查询能耗统计", notes = "参数是areaIds,startDate,endDate")
	@RequestMapping(value = "totalEnergy", method = { RequestMethod.POST })
	public RestResultDto totalEnergy(@RequestBody EnergyDayRequest request) {
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {
			List<String> areaIds = request.getAreaIds();
			String startDate = request.getStartDate();
			String endDate = request.getEndDate();
			// String tenantId = request.getTenantId();

			if (CollectionUtils.isEmpty(areaIds) || StringUtil.isNullOrEmpty(startDate)
					|| StringUtil.isNullOrEmpty(endDate)) {
				throw new Exception("查询参数不能为空！");
			}

			List<SysArea> sysAreas = sysAreaService.findByAreaIdIn(areaIds);
			Map<String, List<String>> areaIdForCircuitIdsMap = Maps.newHashMap();
			List<String> circuitIds = null;
			List<String> deviceIds = null;
			List<String> deviceNodeIds = null;
			// 用于存储所有的丈查询的 circuitId
			List<String> allCircuitIds = Lists.newArrayList();
			Map<String, String> areaIdForName = Maps.newHashMap();
			for (SysArea sysArea : sysAreas) {
				areaIdForName.put(sysArea.getAreaId(), sysArea.getAreaName());
				deviceNodeIds = Lists.newArrayList();
				deviceIds = Lists.newArrayList();
				circuitIds = Lists.newArrayList();

				if (sysArea.getAreaType().equals("VirtualiNode")) { // 虚拟节点
					deviceNodeIds = getDeviceNode(sysArea.getAreaId(), deviceNodeIds);
					if (!CollectionUtils.isEmpty(deviceNodeIds)) {
						for (String nodeId : deviceNodeIds) {
							deviceIds = Lists.newArrayList();
							List<DeviceInfo> deviceInfos = deviceService.findByEndUserId(nodeId);
							for (DeviceInfo deviceInfo : deviceInfos) {
								deviceIds.add(deviceInfo.getDeviceId());
							}
							List<DeviceCircuit> circuits = deviceCircuitService.findByDeviceIdIn(deviceIds);
							for (DeviceCircuit circuit : circuits) {
								circuitIds.add(circuit.getCircuitId());
								if (!allCircuitIds.contains(circuit.getCircuitId())) {
									allCircuitIds.add(circuit.getCircuitId());
								}
							}
						}
					}
				} else if (sysArea.getAreaType().equals("DeviceNode")) { // 如果是设备节点，直接查询下属设备
					List<DeviceInfo> deviceInfos = deviceService.findByEndUserId(sysArea.getAreaId());
					for (DeviceInfo deviceInfo : deviceInfos) {
						deviceIds.add(deviceInfo.getDeviceId());
					}
					List<DeviceCircuit> circuits = deviceCircuitService.findByDeviceIdIn(deviceIds);
					for (DeviceCircuit circuit : circuits) {
						circuitIds.add(circuit.getCircuitId());
						if (!allCircuitIds.contains(circuit.getCircuitId())) {
							allCircuitIds.add(circuit.getCircuitId());
						}
					}
				}
				areaIdForCircuitIdsMap.put(sysArea.getAreaId(), circuitIds);
			}

			List<EnergyDay> energyDays = energyDayService.findByDeviceIdInAndDateBetween(allCircuitIds, startDate,
					endDate);
			Map<String, List<EnergyDay>> energyMap = Maps.newHashMap();
			List<EnergyDay> energyList = null;
			for (EnergyDay entity : energyDays) {
				if (energyMap.containsKey(entity.getDeviceId())) {
					energyList = energyMap.get(entity.getDeviceId());
				} else {
					energyList = Lists.newArrayList();
				}
				energyList.add(entity);
				energyMap.put(entity.getDeviceId(), energyList);
			}

			Map<String, EnergyDay> mapValue = Maps.newHashMap();
			EnergyDay temp = null;
			Double energy = 0.0;
			Double money = 0.0;
			for (String deviceId : energyMap.keySet()) {
				temp = new EnergyDay();
				energy = 0.0;
				money = 0.0;
				for (EnergyDay entity : energyMap.get(deviceId)) {
					energy += entity.getEnergy();
					// money += entity.getMoney();
				}
				temp.setEnergy(energy);
				temp.setMoney(money);
				mapValue.put(deviceId, temp);
			}

			energy = 0.0;
			money = 0.0;
			Map<String, Object> map = null;
			List<Object> resultValue = Lists.newArrayList();
			for (String areaId : areaIds) {
				map = Maps.newHashMap();
				energy = 0.0;
				money = 0.0;
				for (String deviceId : areaIdForCircuitIdsMap.get(areaId)) {
					if (mapValue.containsKey(deviceId)) {
						energy += mapValue.get(deviceId).getEnergy();
					}
					// money += mapValue.get(deviceId).getMoney();
				}
				map.put("areaId", areaId);
				map.put("areaName", areaIdForName.get(areaId));
				map.put("energy", energy);
				map.put("money", money);
				resultValue.add(map);
			}
			data = resultValue;
			msg = "查询成功！";

		} catch (Exception e) {
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			msg = "查询区域能耗数据失败！";
			logger.warn("查询区域能耗数据失败！");
		} finally {
			resultDto.setData(data);
			resultDto.setException(exception);
			resultDto.setMsg(msg);
			resultDto.setResult(result);
		}
		return resultDto;
	}

	/**
	 * 根据虚拟节点的区域id查询下属所有的设备节点id
	 * 
	 * @param areaId
	 * @param deviceNodeIds
	 * @return
	 */
	public List<String> getDeviceNode(String areaId, List<String> deviceNodeIds) {
		List<SysArea> sysAreas = sysAreaService.findByParentId(areaId);
		if (!CollectionUtils.isEmpty(sysAreas)) {
			for (SysArea sysArea : sysAreas) {
				if (sysArea.getAreaType().equals("DeviceNode")) {
					deviceNodeIds.add(sysArea.getAreaId());
				} else {
					getDeviceNode(sysArea.getAreaId(), deviceNodeIds);
				}
			}
		}
		return deviceNodeIds;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "同环比分析", notes = "参数是areaId,months")
	@RequestMapping(value = "analyzeTHB", method = { RequestMethod.POST })
	public RestResultDto analyzeTHB(@RequestBody EnergyDayRequest request) {
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {

			List<String> areaIds = request.getAreaIds();

			List<String> months = request.getMonths();

			List<SysArea> sysAreas = sysAreaService.findByAreaIdIn(areaIds);

			Map<String, List<String>> areaIdForCircuitIdsMap = Maps.newHashMap();
			List<String> circuitIds = Lists.newArrayList();
			List<String> deviceIds = Lists.newArrayList();
			List<String> deviceNodeIds = Lists.newArrayList();
			Map<String, String> areaIdForName = Maps.newHashMap();
			// 用于存储所有的丈查询的 circuitId
			List<String> allCircuitIds = Lists.newArrayList();
			for (SysArea sysArea : sysAreas) {
				areaIdForName.put(sysArea.getAreaId(), sysArea.getAreaName());
				deviceNodeIds = Lists.newArrayList();
				deviceIds = Lists.newArrayList();
				circuitIds = Lists.newArrayList();

				if (sysArea.getAreaType().equals("VirtualiNode")) { // 虚拟节点
					deviceNodeIds = getDeviceNode(sysArea.getAreaId(), deviceNodeIds);
					if (!CollectionUtils.isEmpty(deviceNodeIds)) {
						for (String nodeId : deviceNodeIds) {
							deviceIds = Lists.newArrayList();
							List<DeviceInfo> deviceInfos = deviceService.findByEndUserId(nodeId);
							for (DeviceInfo deviceInfo : deviceInfos) {
								deviceIds.add(deviceInfo.getDeviceId());
							}
							List<DeviceCircuit> circuits = deviceCircuitService.findByDeviceIdIn(deviceIds);
							for (DeviceCircuit circuit : circuits) {
								circuitIds.add(circuit.getCircuitId());
								if (!allCircuitIds.contains(circuit.getCircuitId())) {
									allCircuitIds.add(circuit.getCircuitId());
								}
							}
						}
					}
				} else if (sysArea.getAreaType().equals("DeviceNode")) { // 如果是设备节点，直接查询下属设备
					List<DeviceInfo> deviceInfos = deviceService.findByEndUserId(sysArea.getAreaId());
					for (DeviceInfo deviceInfo : deviceInfos) {
						deviceIds.add(deviceInfo.getDeviceId());
					}
					List<DeviceCircuit> circuits = deviceCircuitService.findByDeviceIdIn(deviceIds);
					for (DeviceCircuit circuit : circuits) {
						circuitIds.add(circuit.getCircuitId());
						if (!allCircuitIds.contains(circuit.getCircuitId())) {
							allCircuitIds.add(circuit.getCircuitId());
						}
					}
				}
				areaIdForCircuitIdsMap.put(sysArea.getAreaId(), circuitIds);
			}

			List<EnergyDay> energyList = energyDayService.findByDeviceIdInAndDateTypeAndDateIn(allCircuitIds,
					DateTypeEnum.MONTH.getKey(), months);

			Map<String, List<EnergyDay>> energyMap = Maps.newHashMap();
			List<EnergyDay> list = null;
			for (EnergyDay entity : energyList) {
				if (energyMap.containsKey(entity.getDeviceId())) {
					list = energyMap.get(entity.getDeviceId());
				} else {
					list = Lists.newArrayList();
				}
				list.add(entity);
				energyMap.put(entity.getDeviceId(), list);
			}

			List<Object> resultValue = Lists.newArrayList();
			List<EnergyDay> listValue = null;
			Map<String, Object> mapValue = null;
			Map<String, Double> map = null;
			List<Object> ll = null;
			Double energy = 0.0;
			for (String areaId : areaIds) {
				mapValue = Maps.newHashMap();
				mapValue.put("areaId", areaId);
				mapValue.put("areaName", areaIdForName.get(areaId));

				map = Maps.newHashMap();
				energy = 0.0;
				ll = Lists.newArrayList();
				for (String circuitId : areaIdForCircuitIdsMap.get(areaId)) {
					listValue = energyMap.get(circuitId);
					if (CollectionUtils.isEmpty(listValue)) {
						continue;
					}
					for (EnergyDay entity : listValue) {
						if (map.containsKey(entity.getDate())) {
							energy = map.get(entity.getDate());
						} else {
							energy = 0.0;
						}
						energy += entity.getEnergy();
						map.put(entity.getDate(), energy);
					}
				}

				// 如果月份没有数据，就补0
				for (String reqDate : months) {
					if (!map.containsKey(reqDate)) {
						map.put(reqDate, 0.00);
					}
				}

				ll.add(map);
				mapValue.put("list", ll);
				resultValue.add(mapValue);

			}

			data = resultValue;
			msg = "查询成功！";

		} catch (Exception e) {
			result = RestResultDto.RESULT_FAIL;
			exception = e.getMessage();
			msg = "查询区域能耗数据失败！";
			logger.warn("查询区域能耗数据失败！");
		} finally {
			resultDto.setData(data);
			resultDto.setException(exception);
			resultDto.setMsg(msg);
			resultDto.setResult(result);
		}
		return resultDto;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "设备日能耗计算", notes = "时间范围，设备ids")
	@RequestMapping(value = "dayEnergy", method = { RequestMethod.POST })
	public RestResultDto calcEnergy(@RequestBody EnergyDataRequest request) {
		RestResultDto resultDto = new RestResultDto();
		Integer result = RestResultDto.RESULT_SUCC;
		String msg = null;
		Object data = null;
		String exception = null;
		try {

			List<String> circuitIds = request.getCircuitIds();

			String startDate = request.getStartDate();
			String endDate = request.getEndDate();
			List<EnergyDay> energyList = energyDayService.findByDeviceIdInAndDateBetweenOrderByDateDesc(circuitIds,
					startDate, endDate);

			Map<String, List<EnergyDay>> mapValue = Maps.newHashMap();
			List<EnergyDay> list = null;
			for (String circuitId : circuitIds) {
				list = Lists.newArrayList();
				for (EnergyDay energyDay : energyList) {
					if (energyDay.getDeviceId().equals(circuitId)) {
						list.add(energyDay);
					}
				}
				mapValue.put(circuitId, list);
			}

			data = mapValue;
			msg = "查询成功！";

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

	@ApiOperation(value = "报表数据导出", notes = "新页面打开网址：http://localhost:9091/nbiot/v1/report/exportData?"
			+ "areaIds=5a8fa89c46f64c1604e0a3a0,5aa1237ddb757f00012c352c,"
			+ "5aa12459db757f00012c352d&startDate=2018-03-01&endDate=2018-03-10")
	@RequestMapping(value = "exportData", method = { RequestMethod.GET })
	public String exportExcel(@QueryParam("areaIds") String areaIds, @QueryParam("startDate") String startDate,
			@QueryParam("endDate") String endDate, HttpServletResponse response) {
		String message = null;
		try {
			if (StringUtil.isNullOrEmpty(areaIds) || StringUtil.isNullOrEmpty(startDate)
					|| StringUtil.isNullOrEmpty(endDate)) {
				throw new Exception("查询参数不能为空！");
			}

			String[] areaIdArray = areaIds.split(",");
			List<String> areaIdList = new ArrayList<String>(Arrays.asList(areaIdArray));

			// 根据区域Id查询区域信息
			List<SysArea> sysAreas = sysAreaService.findByAreaIdIn(areaIdList);
			Map<String, List<String>> areaIdForCircuitIdsMap = Maps.newHashMap();
			List<String> circuitIds = null;
			List<String> deviceIds = null;
			List<String> deviceNodeIds = null;
			// 用于存储所有的丈查询的 circuitId
			List<String> allCircuitIds = Lists.newArrayList();
			Map<String, String> areaIdForName = Maps.newHashMap();
			for (SysArea sysArea : sysAreas) {
				areaIdForName.put(sysArea.getAreaId(), sysArea.getAreaName());
				deviceNodeIds = Lists.newArrayList();
				deviceIds = Lists.newArrayList();
				circuitIds = Lists.newArrayList();

				if (sysArea.getAreaType().equals("VirtualiNode")) { // 虚拟节点
					deviceNodeIds = getDeviceNode(sysArea.getAreaId(), deviceNodeIds);
					if (!CollectionUtils.isEmpty(deviceNodeIds)) {
						for (String nodeId : deviceNodeIds) {
							deviceIds = Lists.newArrayList();
							List<DeviceInfo> deviceInfos = deviceService.findByEndUserId(nodeId);
							for (DeviceInfo deviceInfo : deviceInfos) {
								deviceIds.add(deviceInfo.getDeviceId());
							}
							List<DeviceCircuit> circuits = deviceCircuitService.findByDeviceIdIn(deviceIds);
							for (DeviceCircuit circuit : circuits) {
								circuitIds.add(circuit.getCircuitId());
								if (!allCircuitIds.contains(circuit.getCircuitId())) {
									allCircuitIds.add(circuit.getCircuitId());
								}
							}
						}
					}
				} else if (sysArea.getAreaType().equals("DeviceNode")) { // 如果是设备节点，直接查询下属设备
					List<DeviceInfo> deviceInfos = deviceService.findByEndUserId(sysArea.getAreaId());
					for (DeviceInfo deviceInfo : deviceInfos) {
						deviceIds.add(deviceInfo.getDeviceId());
					}
					List<DeviceCircuit> circuits = deviceCircuitService.findByDeviceIdIn(deviceIds);
					for (DeviceCircuit circuit : circuits) {
						circuitIds.add(circuit.getCircuitId());
						if (!allCircuitIds.contains(circuit.getCircuitId())) {
							allCircuitIds.add(circuit.getCircuitId());
						}
					}
				}
				areaIdForCircuitIdsMap.put(sysArea.getAreaId(), circuitIds);
			}

			List<EnergyDay> energyDays = energyDayService.findByDeviceIdInAndDateBetween(allCircuitIds, startDate,
					endDate);
			Map<String, List<EnergyDay>> energyMap = Maps.newHashMap();
			List<EnergyDay> energyList = null;
			for (EnergyDay entity : energyDays) {
				if (energyMap.containsKey(entity.getDeviceId())) {
					energyList = energyMap.get(entity.getDeviceId());
				} else {
					energyList = Lists.newArrayList();
				}
				energyList.add(entity);
				energyMap.put(entity.getDeviceId(), energyList);
			}

			Map<String, EnergyDay> mapValue = Maps.newHashMap();
			EnergyDay temp = null;
			Double energy = 0.0;
			Double money = 0.0;
			for (String deviceId : energyMap.keySet()) {
				temp = new EnergyDay();
				energy = 0.0;
				money = 0.0;
				for (EnergyDay entity : energyMap.get(deviceId)) {
					energy += entity.getEnergy();
					// money += entity.getMoney();
				}
				temp.setEnergy(energy);
				temp.setMoney(money);
				mapValue.put(deviceId, temp);
			}

			List<ExportEnergyDTO> resultValue = Lists.newArrayList();
			ExportEnergyDTO exportEnergy;
			for (String areaId : areaIdList) {
				exportEnergy = new ExportEnergyDTO();
				DecimalFormat df = new DecimalFormat("######0.00");
				energy = 0.0;
				money = 0.0;
				for (String deviceId : areaIdForCircuitIdsMap.get(areaId)) {
					if (mapValue.containsKey(deviceId)) {
						energy += mapValue.get(deviceId).getEnergy();
						// money += mapValue.get(deviceId).getMoney();
					}
				}
				exportEnergy.setAreaId(areaId);
				exportEnergy.setAreaName(areaIdForName.get(areaId));
				// exportEnergy.setAreaName("name");
				exportEnergy.setEnergy(df.format(energy));
				exportEnergy.setMoney(df.format(money));
				resultValue.add(exportEnergy);
			}
			if (CollectionUtils.isNotEmpty(resultValue)) {
				FileUtil.exportExcel(resultValue, "数据", "数据一览", ExportEnergyDTO.class, "HistoryData.xls", response);
				message = "导出成功！";
			} else {
				message = "导出失败！";
				throw new Exception("导出失败!");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return message;
	}

}

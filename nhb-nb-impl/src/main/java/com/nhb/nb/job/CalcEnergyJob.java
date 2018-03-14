package com.nhb.nb.job;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nhb.nb.dataaccess.entity.DeviceCircuit;
import com.nhb.nb.dataaccess.entity.EnergyDay;
import com.nhb.nb.dataaccess.entity.HistoryData;
import com.nhb.nb.dataaccess.service.data.EnergyDayService;
import com.nhb.nb.dataaccess.service.data.HistoryDataService;
import com.nhb.nb.dataaccess.service.device.DeviceCircuitService;
import com.nhb.nb.enums.DateTypeEnum;
import com.nhb.nb.util.DateTimeUtils;
import com.nhb.utils.nhb_utils.common.DateUtil;
import com.nhb.utils.nhb_utils.json.JsonMapper;

@Component
public class CalcEnergyJob {

	@Autowired
	private DeviceCircuitService deviceCircuitService;

	@Autowired
	private HistoryDataService historyDataService;

	@Autowired
	private EnergyDayService energyDayService;

	@Scheduled(cron = "0 5 0 * * ?")
	public void calcEnergyLastDay() throws Exception {

		// 昨天的日期
		String day = DateTimeUtils.getBeforeDate();
		// 月份
		String month = day.substring(0, 7);

		// 获取前昨天时间（yyyy-mm-dd 00:00:00 - yyyy-mm-dd 23:59:59）
		Map<String, String> dayRange = DateTimeUtils.getDateRange();
		// 获取前天时间范围
		Map<String, String> lastDayRange = DateTimeUtils.getLastBeforeDateRange();

		// 昨天开始结束时间
		Date startTime = DateUtil.parse(dayRange.get("beginTime"));
		Date endTime = DateUtil.parse(dayRange.get("endTime"));
		// 前天的开始结束时间
		Date lastStartTime = DateUtil.parse(lastDayRange.get("beginTime"));
		Date lastEndTime = DateUtil.parse(lastDayRange.get("endTime"));

		List<DeviceCircuit> deviceCircuits = deviceCircuitService.findAll();
		List<String> circuitIds = Lists.newArrayList();
		if (!CollectionUtils.isEmpty(deviceCircuits)) {
			for (DeviceCircuit circuit : deviceCircuits) {
				circuitIds.add(circuit.getCircuitId());
			}
		}
		deleteData(month, day, circuitIds);
		calcData(month, day, circuitIds, deviceCircuits, startTime, endTime, lastStartTime, lastEndTime);
	}

	/**
	 * 
	 * @param month
	 * @param day
	 * @param circuitIds
	 */
	public void deleteData(String month, String day, List<String> circuitIds) {
		energyDayService.deleteByDateAndDeviceIdIn(day, circuitIds);
		energyDayService.deleteByDateAndDeviceIdIn(month, circuitIds);
	}

	@SuppressWarnings("unchecked")
	public void calcData(String month, String day, List<String> circuitIds, List<DeviceCircuit> deviceCircuits,
			Date startTime, Date endTime, Date lastStartTime, Date lastEndTime) {

		List<HistoryData> lastHistoryDatas = historyDataService
				.findByDeviceIdInAndMeterTimeBetweenOrderByMeterTimeDesc(circuitIds, startTime, endTime);
		List<HistoryData> lastBeforeHistoryDatas = historyDataService
				.findByDeviceIdInAndMeterTimeBetweenOrderByMeterTimeDesc(circuitIds, lastStartTime, lastEndTime);

		Map<String, List<HistoryData>> lastDataMap = Maps.newHashMap();
		List<HistoryData> lastDatas = null;
		for (HistoryData historyData : lastHistoryDatas) {
			if (lastDataMap.containsKey(historyData.getDeviceId())) {
				lastDatas = lastDataMap.get(historyData.getDeviceId());
			} else {
				lastDatas = Lists.newArrayList();
			}
			lastDatas.add(historyData);
			lastDataMap.put(historyData.getDeviceId(), lastDatas);
		}

		Map<String, List<HistoryData>> lastBeforeDataMap = Maps.newHashMap();
		List<HistoryData> lastBeforeDatas = null;
		for (HistoryData historyData : lastBeforeHistoryDatas) {
			if (lastBeforeDataMap.containsKey(historyData.getDeviceId())) {
				lastBeforeDatas = lastBeforeDataMap.get(historyData.getDeviceId());
			} else {
				lastBeforeDatas = Lists.newArrayList();
			}
			lastBeforeDatas.add(historyData);
			lastBeforeDataMap.put(historyData.getDeviceId(), lastBeforeDatas);
		}

		JsonMapper jsonMapper = new JsonMapper();
		Map<String, Object> lastMapValue = Maps.newHashMap();
		Map<String, Object> lastBeforeMapValue = Maps.newHashMap();

		List<EnergyDay> energyMonth = energyDayService.findByDeviceIdInAndDateTypeAndDateLike(circuitIds,
				DateTypeEnum.DAY.getKey(), month);
		Map<String, List<EnergyDay>> energyMap = Maps.newHashMap();
		List<EnergyDay> energyDayList = null;
		for (EnergyDay energyDay : energyMonth) {
			if (energyMap.containsKey(energyDay.getDeviceId())) {
				energyDayList = energyMap.get(energyDay.getDeviceId());
			} else {
				energyDayList = Lists.newArrayList();
			}
			energyDayList.add(energyDay);
			energyMap.put(energyDay.getDeviceId(), energyDayList);
		}

		List<EnergyDay> energyDays = Lists.newArrayList();
		EnergyDay energyDay = null;
		for (DeviceCircuit circuit : deviceCircuits) {
			energyDay = new EnergyDay();
			energyDay.setDeviceId(circuit.getCircuitId());
			if (!lastDataMap.containsKey(circuit.getCircuitId())) {
				energyDay.setEnergy(0.0);
			} else {
				lastMapValue = jsonMapper.convertValue(lastDataMap.get(circuit.getCircuitId()).get(0).getData(),
						Map.class);
				if (!lastBeforeDataMap.containsKey(circuit.getCircuitId())) {
					lastBeforeMapValue = jsonMapper.convertValue(lastDataMap.get(circuit.getCircuitId())
							.get(lastDataMap.get(circuit.getCircuitId()).size() - 1).getData(), Map.class);
				} else {
					lastBeforeMapValue = jsonMapper
							.convertValue(lastBeforeDataMap.get(circuit.getCircuitId()).get(0).getData(), Map.class);
				}
				energyDay.setEnergy(Double.parseDouble(String.valueOf(lastMapValue.get("kwhTotal")))
						- Double.parseDouble(String.valueOf(lastBeforeMapValue.get("kwhTotal"))));
			}
			energyDay.setDate(day);
			// energyDay.setDataType(lastDataMap.get(circuit.getCircuitId()).get(0).getServiceId());
			energyDay.setDateType(DateTypeEnum.DAY.getKey());
			energyDays.add(energyDay);

		}
		energyDayService.saveList(energyDays);

		Double energy = 0.0;
		energyDays = Lists.newArrayList();
		for (DeviceCircuit circuit : deviceCircuits) {
			energy = 0.0;
			energyDay = new EnergyDay();
			energyDay.setDeviceId(circuit.getCircuitId());
			energyDay.setDate(month);
			energyDay.setDateType(DateTypeEnum.MONTH.getKey());
			if (energyMap.containsKey(circuit.getCircuitId())) {
				for (EnergyDay entity : energyMap.get(circuit.getCircuitId())) {
					energy += entity.getEnergy();
				}
			}
			energyDay.setEnergy(energy);
			energyDays.add(energyDay);
		}
		energyDayService.saveList(energyDays);
	}

}

package com.nhb.nb.dataaccess.service.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhb.nb.dataaccess.dao.data.EnergyDayDao;
import com.nhb.nb.dataaccess.entity.EnergyDay;

@Service
public class EnergyDayService {

	@Autowired
	private EnergyDayDao energyDayDao;

	public void saveList(List<EnergyDay> list) {
		energyDayDao.save(list);
	}

	public List<EnergyDay> findByDeviceIdInAndDateBetween(List<String> deviceIds, String startDate, String endDate) {
		return energyDayDao.findByDeviceIdInAndDateBetween(deviceIds, startDate, endDate);
	}

	public List<EnergyDay> findByDeviceIdAndDateBetween(String deviceId, String startDate, String endDate) {
		return energyDayDao.findByDeviceIdAndDateBetween(deviceId, startDate, endDate);
	}

	public void deleteByDateAndDeviceIdIn(String beforeDate, List<String> circuitIds) {
		energyDayDao.deleteByDateAndDeviceIdIn(beforeDate, circuitIds);
	}

	public List<EnergyDay> findByDeviceIdInAndDateTypeAndDateLike(List<String> circuitIds, String dateType,
			String date) {
		return energyDayDao.findByDeviceIdInAndDateTypeAndDateLike(circuitIds, dateType, date);
	}

	public List<EnergyDay> findByDeviceIdInAndDateTypeAndDateIn(List<String> circuitIds, String dateType,
			List<String> date) {
		return energyDayDao.findByDeviceIdInAndDateTypeAndDateIn(circuitIds, dateType, date);
	}

	public List<EnergyDay> findByDeviceIdInAndDateBetweenOrderByDateDesc(List<String> circuitIds, String startDate,
			String endDate) {
		return energyDayDao.findByDeviceIdInAndDateBetweenOrderByDateDesc(circuitIds, startDate, endDate);
	}

}

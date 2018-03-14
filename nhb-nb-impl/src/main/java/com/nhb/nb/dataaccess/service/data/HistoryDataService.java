package com.nhb.nb.dataaccess.service.data;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nhb.nb.dataaccess.dao.data.HistoryDataDao;
import com.nhb.nb.dataaccess.entity.HistoryData;

@Service
public class HistoryDataService {

	@Autowired
	private HistoryDataDao historyDataDao;

	/**
	 * 历史数据分页查询
	 * 
	 * @param deviceId
	 * @param startDate
	 * @param endDate
	 * @param pageable
	 * @return
	 */
	public Page<HistoryData> findByDeviceIdAndMeterTimeBetween(String deviceId, Date startDate, Date endDate,
			Pageable pageable) {
		return historyDataDao.findByDeviceIdAndMeterTimeBetween(deviceId, startDate, endDate, pageable);
	}

	public List<HistoryData> findByDeviceIdAndMeterTimeBetween(String deviceId, Date startDate, Date endDate) {
		return historyDataDao.findByDeviceIdAndMeterTimeBetween(deviceId, startDate, endDate);
	}

	public List<HistoryData> findByDeviceId(String deviceId) {
		return historyDataDao.findByDeviceId(deviceId);
	}

	public List<HistoryData> findByDeviceIdInAndMeterTimeBetweenOrderByMeterTimeDesc(List<String> circuitIds,
			Date startTime, Date endTime) {
		return historyDataDao.findByDeviceIdInAndMeterTimeBetweenOrderByMeterTimeDesc(circuitIds, startTime, endTime);
	}

}

package com.nhb.nb.dataaccess.dao.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nhb.nb.dataaccess.entity.HistoryData;

@Repository
public interface HistoryDataDao extends MongoRepository<HistoryData, String> {

	Page<HistoryData> findByDeviceIdAndMeterTimeBetween(String deviceId, Date startDate, Date endDate,
			Pageable pageable);
	
	List<HistoryData> findByDeviceIdAndMeterTimeBetween(String deviceId, Date startDate, Date endDate);
	
	List<HistoryData> findByDeviceId(String deviceId);

	List<HistoryData> findByDeviceIdInAndMeterTimeBetweenOrderByMeterTimeDesc(List<String> circuitIds, Date startTime,
			Date endTime);

}

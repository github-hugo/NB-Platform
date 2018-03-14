package com.nhb.nb.dataaccess.dao.data;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nhb.nb.dataaccess.entity.EnergyDay;

@Repository
public interface EnergyDayDao extends MongoRepository<EnergyDay, String> {

	List<EnergyDay> findByDeviceIdInAndDateBetween(List<String> deviceIds, String startDate, String endDate);

	List<EnergyDay> findByDeviceIdAndDateBetween(String deviceId, String startDate, String endDate);

	void deleteByDateAndDeviceIdIn(String beforeDate, List<String> circuitIds);

	List<EnergyDay> findByDeviceIdInAndDateType(List<String> circuitIds, String month, String dateType);

	List<EnergyDay> findByDeviceIdInAndDateTypeAndDateLike(List<String> circuitIds, String dateType, String date);

	List<EnergyDay> findByDeviceIdInAndDateTypeAndDateIn(List<String> circuitIds, String dateType, List<String> date);

	List<EnergyDay> findByDeviceIdInAndDateBetweenOrderByDateDesc(List<String> circuitIds, String startDate,
			String endDate);

}

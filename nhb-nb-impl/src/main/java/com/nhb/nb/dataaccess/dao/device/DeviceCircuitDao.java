package com.nhb.nb.dataaccess.dao.device;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nhb.nb.dataaccess.entity.DeviceCircuit;

@Repository
public interface DeviceCircuitDao extends MongoRepository<DeviceCircuit, String> {

	List<DeviceCircuit> findByDeviceId(String deviceId);
	
	DeviceCircuit findByCircuitId(String circuitId);

	List<DeviceCircuit> findByCircuitIdIn(List<String> circuitIds);

	List<DeviceCircuit> findByDeviceIdIn(List<String> deviceIds);

	void deleteByDeviceId(String deviceId);

}

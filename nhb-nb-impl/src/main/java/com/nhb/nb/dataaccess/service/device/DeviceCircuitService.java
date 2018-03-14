package com.nhb.nb.dataaccess.service.device;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhb.nb.dataaccess.dao.device.DeviceCircuitDao;
import com.nhb.nb.dataaccess.entity.DeviceCircuit;

@Service
public class DeviceCircuitService {

	@Autowired
	private DeviceCircuitDao deviceCircuitDao;

	public DeviceCircuit save(DeviceCircuit circuit) {
		return deviceCircuitDao.save(circuit);
	}

	public DeviceCircuit findByCircuitId(String circuitId) {
		return deviceCircuitDao.findByCircuitId(circuitId);
	}
	
	public List<DeviceCircuit> findByCircuitIdIn(List<String> circuitIds){
		return deviceCircuitDao.findByCircuitIdIn(circuitIds);
	}

	public List<DeviceCircuit> findByDeviceId(String deviceId) {
		return deviceCircuitDao.findByDeviceId(deviceId);
	}
	
	public List<DeviceCircuit> findByDeviceIdIn(List<String> deviceIds){
		return deviceCircuitDao.findByDeviceIdIn(deviceIds);
	}

	public void deleteById(String circuitId) {
		deviceCircuitDao.delete(circuitId);
	}
	
	public void deleteByDeviceId(String deviceId){
		deviceCircuitDao.deleteByDeviceId(deviceId);
	}

	public List<DeviceCircuit> findAll() {
		return deviceCircuitDao.findAll();
	}

}

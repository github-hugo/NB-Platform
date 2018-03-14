package com.nhb.nb.dataaccess.service.device;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nhb.nb.dataaccess.dao.device.DeviceDao;
import com.nhb.nb.dataaccess.entity.DeviceInfo;

@Service
public class DeviceService {

	@Autowired
	private DeviceDao deviceDao;

	public DeviceInfo findByDeviceId(String deviceId) {
		return deviceDao.findByDeviceId(deviceId);
	}

	public DeviceInfo findByName(String name) {
		return deviceDao.findByName(name);
	}

	public Page<DeviceInfo> findList(Pageable pageable) {
		return deviceDao.findAll(pageable);
	}

	public List<DeviceInfo> findList() {
		return deviceDao.findAll();
	}

	public void save(DeviceInfo deviceInfo) {
		deviceDao.save(deviceInfo);
	}

	public List<DeviceInfo> findByEndUserIdIn(List<String> endUserIds) {
		return deviceDao.findByEndUserIdIn(endUserIds);
	}

	public List<DeviceInfo> findByEndUserId(String areaId) {
		return deviceDao.findByEndUserId(areaId);
	}
	
	public List<DeviceInfo> findByDeviceIdIn(List<String> deviceIds){
		return deviceDao.findByDeviceIdIn(deviceIds);
	}

	public DeviceInfo findByNodeId(String nodeId) {
		return deviceDao.findByNodeId(nodeId);
	}

}

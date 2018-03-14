package com.nhb.nb.dataaccess.dao.device;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nhb.nb.dataaccess.entity.DeviceInfo;

@Repository
public interface DeviceDao extends MongoRepository<DeviceInfo, String>{

	DeviceInfo findByDeviceId(String deviceId);

	DeviceInfo findByName(String name);

	List<DeviceInfo> findByEndUserIdIn(List<String> endUserIds);

	List<DeviceInfo> findByEndUserId(String areaId);

	List<DeviceInfo> findByDeviceIdIn(List<String> deviceIds);

	DeviceInfo findByNodeId(String nodeId);
}

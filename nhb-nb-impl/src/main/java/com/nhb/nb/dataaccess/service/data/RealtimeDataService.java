package com.nhb.nb.dataaccess.service.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhb.nb.dataaccess.dao.data.RealtimeDataDao;
import com.nhb.nb.dataaccess.entity.RealtimeData;

@Service
public class RealtimeDataService {

	@Autowired
	private RealtimeDataDao realtimeDataDao;

	public List<RealtimeData> findByDeviceIdIn(List<String> deviceIds) {
		return realtimeDataDao.findByDeviceIdIn(deviceIds);
	}

}

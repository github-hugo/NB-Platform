package com.nhb.nb.dataaccess.service.area;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nhb.nb.dataaccess.dao.area.SysAreaDao;
import com.nhb.nb.dataaccess.entity.SysArea;

@Service
public class SysAreaService {
	
	@Autowired
	private SysAreaDao sysAreaDao;
	
	public SysArea save(SysArea area){
		return sysAreaDao.save(area);
	}
	
	public void delete(SysArea area){
		sysAreaDao.delete(area);
	}
	
	public SysArea findByAreaId(String areaId){
		return sysAreaDao.findByAreaId(areaId);
	}
	
	public SysArea findByAreaName(String areaName){
		return sysAreaDao.findByAreaName(areaName);
	}
	
	public Page<SysArea> findByTenantId(String tenantId,Pageable pageable){
		return sysAreaDao.findByTenantId(tenantId,pageable);
	}
	
	public List<SysArea> findByTenantId(String tenantId){
		return sysAreaDao.findByTenantId(tenantId);
	}
	
	public SysArea findByAreaIdAndTenantId(String areaId,String tenantId){
		return sysAreaDao.findByAreaIdAndTenantId(areaId,tenantId);
	}
	
	public List<SysArea> findByAreaIdIn(List<String> areaIds){
		return sysAreaDao.findByAreaIdIn(areaIds);
	}

	public List<SysArea> findByParentId(String areaId) {
		return sysAreaDao.findByParentId(areaId);
	}
	
}

package com.nhb.nb.dataaccess.dao.area;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nhb.nb.dataaccess.entity.SysArea;

@Repository
public interface SysAreaDao extends MongoRepository<SysArea, String> {

	SysArea findByAreaId(String areaId);

	SysArea findByAreaName(String areaName);

	Page<SysArea> findByTenantId(String tenantId, Pageable pageable); 
	
	List<SysArea> findByTenantId(String tenantId);

	SysArea findByAreaIdAndTenantId(String areaId, String tenantId);

	List<SysArea> findByAreaIdIn(List<String> areaIds);

	List<SysArea> findByParentId(String areaId);

}

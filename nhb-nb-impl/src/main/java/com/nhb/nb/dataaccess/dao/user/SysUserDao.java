package com.nhb.nb.dataaccess.dao.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nhb.nb.dataaccess.entity.SysUser;

@Repository
public interface SysUserDao extends MongoRepository<SysUser, String> {

	SysUser findByUserId(String userId);

	SysUser findByUserName(String userName);

	Page<SysUser> findByRole(String role, Pageable pageable);

}

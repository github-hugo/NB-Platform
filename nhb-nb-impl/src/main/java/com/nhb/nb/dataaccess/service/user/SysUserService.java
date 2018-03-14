package com.nhb.nb.dataaccess.service.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nhb.nb.dataaccess.dao.user.SysUserDao;
import com.nhb.nb.dataaccess.entity.SysUser;

@Service
public class SysUserService {
	
	@Autowired
	private SysUserDao sysUserDao;
	
	public SysUser findByUserId(String userId){
		return sysUserDao.findByUserId(userId);
	}
	
	
	public SysUser findByUserName(String userName){
		return sysUserDao.findByUserName(userName);
	}
	
	public Page<SysUser> findList(Pageable pageable){
		return sysUserDao.findAll(pageable);
	}
	
	public Page<SysUser> findByRole(String role,Pageable pageable){
		return sysUserDao.findByRole(role,pageable);
	}
	
	public SysUser save(SysUser user){
		return sysUserDao.save(user);
	}
	
	public void delete(SysUser user){
		sysUserDao.delete(user);
	}
	
}

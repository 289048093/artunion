package com.artunion.service.role;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.artunion.BaseService;
import com.artunion.dao.RoleDAO;

@Service("roleService")
public class RoleService extends BaseService{
	@SuppressWarnings("unused")
	@Resource
	private RoleDAO roleDAO;

}
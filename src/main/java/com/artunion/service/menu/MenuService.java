package com.artunion.service.menu;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.artunion.BaseService;
import com.artunion.dao.MenuDAO;

@Service("menuService")
public class MenuService extends BaseService{
	@SuppressWarnings("unused")
	@Resource
	private MenuDAO menuDAO;

}

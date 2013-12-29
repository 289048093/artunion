package com.artunion.service.rights;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.artunion.BaseService;
import com.artunion.dao.RightsDAO;

@Service("rightsService")
public class RightsService extends BaseService{
	@SuppressWarnings("unused")
	@Resource
	private RightsDAO rightsDAO;

}

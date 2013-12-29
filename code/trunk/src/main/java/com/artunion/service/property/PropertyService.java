package com.artunion.service.property;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.artunion.BaseService;
import com.artunion.dao.PropertyDAO;

@Service("propertyService")
public class PropertyService extends BaseService{
    @SuppressWarnings("unused")
	@Resource
	private PropertyDAO propertyDAO;
}
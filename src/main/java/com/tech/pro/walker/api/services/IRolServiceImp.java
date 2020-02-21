package com.tech.pro.walker.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.pro.walker.api.models.dao.IRolDao;
import com.tech.pro.walker.api.models.entity.Rol;

@Service
public class IRolServiceImp implements IRolService{
	
	@Autowired
	private IRolDao iRolDao;
	
	@Override
	public List<Rol> findAll() {
		return iRolDao.findAll();
	}

}

package com.tech.pro.walker.api.services;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tech.pro.walker.api.models.dao.IConfiguracionDao;
import com.tech.pro.walker.api.models.entity.Configuracion;

@Service
public class IConfiguracionServiceImp implements IConfiguracionService{
	
	@Autowired
	private IConfiguracionDao iConfiguracionDao;

	@Override
	@Transactional( readOnly = true)
	public List<Configuracion> findAllByIdProyecto(Long id_proyecto) {
		return iConfiguracionDao.findAllByIdProyecto(id_proyecto);
	}

	@Override
	@Transactional( readOnly = true)
	public Configuracion getSemanaActual(Date now, Long id_proyecto) {
		return iConfiguracionDao.getSemanaActual(now, id_proyecto);
	}
	
	
	
	

}

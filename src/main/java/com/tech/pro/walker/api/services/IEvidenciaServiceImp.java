package com.tech.pro.walker.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.pro.walker.api.models.dao.IEvidenciaDao;
import com.tech.pro.walker.api.models.entity.Evidencia;


@Service
public class IEvidenciaServiceImp implements IEvidenciaService {
	
	@Autowired
	private IEvidenciaDao iEvidenciaDao;

	@Override
	public List<Evidencia> findAll() {
		return iEvidenciaDao.findAll();
	}

}

package com.tech.pro.walker.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.pro.walker.api.models.dao.IProyectoDao;
import com.tech.pro.walker.api.models.entity.Proyecto;

@Service
public class IProyectoServiceImp implements IProyectoService{
	
	@Autowired
	private IProyectoDao iProyectoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Proyecto> findAll() {
		return iProyectoDao.findAll();
	}

	@Override
	@Transactional
	public Proyecto save(Proyecto proyecto) {
		return iProyectoDao.save(proyecto);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Proyecto> findById(Long id_proyecto) {
		return iProyectoDao.findById(id_proyecto);
	}

	@Override
	@Transactional
	public void deleteById(Long id_proyecto) {
		 iProyectoDao.deleteById(id_proyecto);
	}





}
package com.tech.pro.walker.api.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tech.pro.walker.api.models.dao.IGridDao;
import com.tech.pro.walker.api.models.entity.Grid;

@Service
public class IGridServiceImp implements IGridService {
	
	@Autowired
	private IGridDao iGridDao;

	@Override
	@Transactional(readOnly=true)
	public List<Grid> findAll() {
		return iGridDao.findAll();
	}

	@Override
	@Transactional
	public Grid save(Grid grid) {
		return iGridDao.save(grid);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Grid> findAllGridByIp(Long id_ip) {
		return iGridDao.findAllGridByIp(id_ip);
	}

	@Override
	@Transactional(readOnly=true)
	public int findByGrid(String numero_plano, Long id_ip) {
		return iGridDao.findByGrid(numero_plano, id_ip);
	}

	@Override
	@Transactional(readOnly=true)
	public Grid findGridByIdGrid(String numero_plano, Long id_ip) {
		return iGridDao.findGridByIdGrid(numero_plano, id_ip);
	}

	@Override
	@Transactional(readOnly=true)
	public Grid fingGridById(Long id_grid) {	
		return iGridDao.fingGridById(id_grid);
	}

	@Override
	@Transactional
	public void deleteById(Long id_grid) {
		iGridDao.deleteById(id_grid);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Object> rptGlobalProyecto(Long id_proyecto) {
		return iGridDao.rptGlobalProyecto(id_proyecto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Object> rptGlobalProyectoByDay(String date, Long id_proyecto) {
		return iGridDao.rptGlobalProyectoByDay(date, id_proyecto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Object> rptGlobalProyectoSemanal(String fecha_incio, String fecha_final, Long id_proyecto) {		
		return iGridDao.rptGlobalProyectoSemanal(fecha_incio, fecha_final, id_proyecto);
	}
	
	
	
	

	
	

	
	
	
	
	
	
	
	

}

package com.tech.pro.walker.api.services;


import java.util.List;

import com.tech.pro.walker.api.models.entity.Grid;

public interface IGridService {
	
	public List<Grid> findAll();
	
	public Grid save(Grid grid);
	
	public List<Grid> findAllGridByIp(Long id_ip);
	
	public Grid findGridByIdGrid(String numero_plano, Long id_ip);
	
	public int findByGrid(String numero_plano, Long id_ip);
	
	public Grid fingGridById(Long id_grid);
	
	public void deleteById(Long id_grid);
	
	public List<Object> rptGlobalProyecto(Long id_proyecto);
	
	public List<Object> rptGlobalProyectoByDay(String date, Long id_proyecto);
	
	public List<Object> rptGlobalProyectoSemanal(String fecha_incio, String fecha_final, Long id_proyecto);
	
	

}

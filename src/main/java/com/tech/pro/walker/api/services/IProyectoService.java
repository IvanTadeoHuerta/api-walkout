package com.tech.pro.walker.api.services;

import java.util.List;
import java.util.Optional;

import com.tech.pro.walker.api.models.entity.Proyecto;

public interface IProyectoService {
	
	public List<Proyecto> findAll();
	
	public Proyecto save(Proyecto proyecto);
	
	public  Optional<Proyecto> findById(Long id_proyecto);
	
	public void deleteById(Long proyecto);
	
}

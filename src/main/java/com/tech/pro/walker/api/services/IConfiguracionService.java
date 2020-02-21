package com.tech.pro.walker.api.services;

import java.util.Date;
import java.util.List;
import com.tech.pro.walker.api.models.entity.Configuracion;


public interface IConfiguracionService {
	
	 public List<Configuracion> findAllByIdProyecto(Long id_proyecto);
	 
	 public Configuracion getSemanaActual(Date now, Long id_proyecto);

}

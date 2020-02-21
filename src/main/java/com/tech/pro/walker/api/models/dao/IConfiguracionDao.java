package com.tech.pro.walker.api.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tech.pro.walker.api.models.entity.Configuracion;

public interface IConfiguracionDao extends JpaRepository<Configuracion, Long>{
	
	
	@Query("from Configuracion c where proyecto.id_proyecto =?1 and c.estatus = 1 order by c.fecha_fin desc")
	public List<Configuracion> findAllByIdProyecto(Long id_proyecto);
	
	@Query("from Configuracion c where (c.fecha_inicio <=?1 and c.fecha_fin >=?1 and  c.proyecto.id_proyecto =?2)")
	public Configuracion getSemanaActual(Date  now, Long id_proyecto);

}

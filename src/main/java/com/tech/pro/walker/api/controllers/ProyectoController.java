package com.tech.pro.walker.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.pro.walker.api.models.entity.Proyecto;
import com.tech.pro.walker.api.services.IProyectoServiceImp;

@RestController
@RequestMapping("api/walkout")
public class ProyectoController {
	
	@Autowired
	private IProyectoServiceImp iProyectoServiceImp;
	
	@Secured({ "ROLE_HQ", "ROLE_ADMIN", "ROLE_WALKER", "ROLE_CLIENTE" })
	@GetMapping("/get-proyectos")
	public List<Proyecto> index(){
		return iProyectoServiceImp.findAll();
	}
	
	@Secured({ "ROLE_HQ", "ROLE_ADMIN" })
	@PostMapping("/crear-proyecto")
	public Proyecto save(@RequestBody Proyecto proyecto ) {
		return iProyectoServiceImp.save(proyecto);
	}
	
	@Secured({ "ROLE_HQ", "ROLE_ADMIN" })
	@GetMapping("/get-proyecto/{id_proyecto}")
	public Optional<Proyecto> getProyecto(@PathVariable Long  id_proyecto) {
		return iProyectoServiceImp.findById(id_proyecto);
	}
	
	@Secured({ "ROLE_HQ", "ROLE_ADMIN" })
	@DeleteMapping("/delete-proyecto/{id_proyecto}")
	public void delete(@PathVariable Long id_proyecto) {
		iProyectoServiceImp.deleteById(id_proyecto);
	}
	
	@Secured({ "ROLE_HQ", "ROLE_ADMIN" })
	@PutMapping("/update-proyecto")
	public Proyecto update(@RequestBody Proyecto proyecto) {
		return iProyectoServiceImp.save(proyecto);
	}

}

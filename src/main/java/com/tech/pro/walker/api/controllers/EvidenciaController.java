package com.tech.pro.walker.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.pro.walker.api.models.entity.Evidencia;
import com.tech.pro.walker.api.services.IEvidenciaServiceImp;

@RestController
@RequestMapping("api/walkout")
public class EvidenciaController {
	
	@Autowired
	private IEvidenciaServiceImp iEvidenciaServiceImp;
	
	@Secured({ "ROLE_HQ"})
	@GetMapping("/get-Evidencias")
	public List<Evidencia> index(){
		return iEvidenciaServiceImp.findAll();
	}

}

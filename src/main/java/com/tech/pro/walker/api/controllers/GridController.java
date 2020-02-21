package com.tech.pro.walker.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.pro.walker.api.models.entity.Grid;
import com.tech.pro.walker.api.services.IGridServiceImp;
import com.tech.pro.walker.api.services.IWalkerServiceImp;

@RestController
@RequestMapping("api/walkout")
public class GridController {
	
	@Autowired
	private IGridServiceImp iGridServiceImp;
	
	@Autowired
	private IWalkerServiceImp iWalkerServiceImp;
	
	@Secured({ "ROLE_HQ", "ROLE_ADMIN" })
	@GetMapping("/get-Grids")
	public List<Grid> index(){
		return iGridServiceImp.findAll();
	} 
	
	@Secured({ "ROLE_HQ"})
	@PostMapping("/crear-Grid")
	public ResponseEntity<?> crear(@RequestBody Grid grid) {
		
		Map<String, Object> response = new HashMap<>();	
		int reg = iGridServiceImp.findByGrid(grid.getNumero_plano().trim(), grid.getIp().getId_ip());
		boolean b = true;
		String menssage = "";
		
		if( reg == 0) {
			
			response.put("grid", iGridServiceImp.save(grid));
			menssage = "Registro exitoso!";
			
		}else {
			b= false;
			menssage = "Número de plano ya fue registrado";
		}
		
		
		response.put("successful", b);
		response.put("message", menssage);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	
	@Secured({ "ROLE_HQ", "ROLE_ADMIN" })
	@GetMapping("get-Grids/{id_ip}")
	public List<Grid> gridsByIp(@PathVariable Long id_ip){
		return iGridServiceImp.findAllGridByIp(id_ip);
	}
	
	@Secured({ "ROLE_HQ", "ROLE_ADMIN" })
	@GetMapping("get-Grid/{id_grid}")
	public Grid findGridByIdGrid(@PathVariable("id_grid") Long id_grid){
		return iGridServiceImp.fingGridById(id_grid);
	}
	
	@Secured({ "ROLE_HQ", "ROLE_ADMIN" })
	@GetMapping("/get-Grids/{id_ip}/All-walkers")
	public ResponseEntity<?> getGridsAllWalker(@PathVariable Long id_ip) {

		Map<String, Object> response = new HashMap<>();
		response.put("walkers", iWalkerServiceImp.findAllWalkersByRol(1L));
		response.put("grids", iGridServiceImp.findAllGridByIp(id_ip));
		response.put("successful", true);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@Secured({ "ROLE_HQ"})
	@PostMapping("/update-Grid")
	public ResponseEntity<?> update(@RequestBody Grid grid) {
		
		Map<String, Object> response = new HashMap<>();	
		
		Grid reg = iGridServiceImp.findGridByIdGrid(grid.getNumero_plano().trim(), grid.getIp().getId_ip());
		
		boolean b = true;
		String menssage = "";
		
		if( reg == null) {
			
			response.put("grid", iGridServiceImp.save(grid));
			menssage = "Actualización exitosa!";
			
		}else {
			
		
			
			if( reg.getId_grid() == grid.getId_grid()) {
				
				response.put("grid", iGridServiceImp.save(grid));
				menssage = "Actualización exitosa!";
				
			}else {
				
				b= false;
				menssage = "Número de plano ya fue registrado";
			}
			
		}
		
		
		response.put("successful", b);
		response.put("message", menssage);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	
	@Secured({ "ROLE_HQ"})
	@DeleteMapping("/delete-Grid/{id_grid}")
	public void delete(@PathVariable Long id_grid) {
		iGridServiceImp.deleteById(id_grid);
	}
	
	

}

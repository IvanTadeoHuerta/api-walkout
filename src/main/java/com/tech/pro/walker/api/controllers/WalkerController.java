package com.tech.pro.walker.api.controllers;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.tech.pro.walker.api.models.entity.Walker;
import com.tech.pro.walker.api.services.IWalkerServiceImp;
import com.tech.pro.walker.api.services.MailServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/walkout")
public class WalkerController {
	
	@Autowired
	private IWalkerServiceImp iWalkerServiceImp;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private MailServiceImpl mailServiceImpl;
	
	
	@Secured({ "ROLE_HQ", "ROLE_ADMIN" })
	@GetMapping("/get-walkers")
	public List<Walker> index(){
		return iWalkerServiceImp.findAll();
	}
	
	@Secured({ "ROLE_HQ", "ROLE_ADMIN" })
	@PostMapping("/crear-walker")
	public Walker crear(@RequestBody Walker walker) {
		return iWalkerServiceImp.save(walker);
	}
	
	@Secured({ "ROLE_HQ", "ROLE_ADMIN", "ROLE_WALKER" })
	@GetMapping("/get-walker/{id_walker}")
	public Optional<Walker> getWalker(@PathVariable Long id_walker){
		return iWalkerServiceImp.findById(id_walker);
	}
	
	@Secured({ "ROLE_HQ", "ROLE_ADMIN" })
	@DeleteMapping("/delete-walker/{id_walker}")
	public void delete(@PathVariable Long id_walker) {
		iWalkerServiceImp.deleteById(id_walker);
	}
	
	@Secured({ "ROLE_HQ", "ROLE_ADMIN" })
	@PutMapping("/update-walker")
	public Walker update(@RequestBody Walker walker) {
		return iWalkerServiceImp.save(walker);
	}
	
	@Secured({ "ROLE_HQ", "ROLE_ADMIN" })
	@GetMapping("/get-walker-by-rol/{id_rol}")
	public Optional<Walker> getWalkerByRol(@PathVariable Long id_rol){
		return iWalkerServiceImp.findById(id_rol);
	}
	
	
	@PostMapping(path = "/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody Map<String, String> params) {
		Map<String, Object> response = new HashMap<>();
		String actual = params.get("actual");
		String nueva = params.get("nueva");
		Long id_walker = Long.valueOf(params.get("id_walker"));

		Walker walker = iWalkerServiceImp.findById( id_walker ).orElse(null);
		

		if (passwordEncoder.matches(actual, walker.getPwd() )) {

			if (passwordEncoder.matches(nueva, walker.getPwd())) {
				response.put("successful", false);
				response.put("message", "La contraseña nueva no puede ser igual a la actual");
			} else {

				String encryp = passwordEncoder.encode(nueva);
				iWalkerServiceImp.updateContrasenia(encryp, walker.getId_walker());
				response.put("successful", true);
				response.put("message", "Contraseña actualizada");
			}

		} else {
			response.put("successful", false);
			response.put("message", "La contraseña actual es incorrecta");
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
	
	@PostMapping(path = "/forgot-password-user")
	public ResponseEntity<?> forgotPasswordUser(@RequestBody String params) {
		Map<String, Object> response = new HashMap<>();

		String correo_electronico = params;
		Walker existe_user = iWalkerServiceImp.findByEmail(correo_electronico).orElse(null);

		if (existe_user != null) {

			if (existe_user.isEstatus()) {
				
				String destinatario = existe_user.getEmail();
				
				String nueva_pwd = RandomStringUtils.randomAlphanumeric(10);
				String encryp = passwordEncoder.encode(nueva_pwd);
				
				
				try {
					mailServiceImpl.sendEmail(destinatario,"Recuperación de contraseña", "Hola "+ existe_user.getNombre() +"!, nos has solicitado restablecer tu contraseña para nuestro sistema. Ingresa:  <b>" + nueva_pwd + "</b>  para iniciar sesión");
					iWalkerServiceImp.updateContrasenia(encryp, existe_user.getId_walker());
					response.put("nueva", nueva_pwd);
					response.put("successful", true);
					response.put("message", existe_user);
				}catch(Exception ex) {
					
					response.put("successful", false);
					response.put("message", ex.getMessage());
				}
				
			
				
			} else {
				response.put("successful", false);
				response.put("message", "Se inhabilitó la cuenta para: " + correo_electronico);
			}

		} else {
			response.put("successful", false);
			response.put("message", "El correo electrónico no existe");
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}

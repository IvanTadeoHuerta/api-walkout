package com.tech.pro.walker.api.controllers;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.pro.walker.api.models.entity.Configuracion;

import com.tech.pro.walker.api.services.IConfiguracionServiceImp;
import com.tech.pro.walker.api.services.IGridServiceImp;
import com.tech.pro.walker.api.services.IIpServiceImp;

@RestController
@RequestMapping("api/walkout")
public class ReportesController {
	
	@Autowired
	private IGridServiceImp iGridServiceImp;
	
	@Autowired
	private IConfiguracionServiceImp iConfiguracionServiceImp;
	
	@Autowired
	IIpServiceImp iIpServiceImp; 
	
	
	@GetMapping("/rpt-global/{id_proyecto}")
	public ResponseEntity<?> rptGlobalProyecto(@PathVariable Long id_proyecto){
		Map<String, Object> response = new HashMap<>();	
		
		List<Object> datos = iGridServiceImp.rptGlobalProyecto(id_proyecto);
		
		response.put("successful", true);
		response.put("datos", datos);
		
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		 
	}
	
	@GetMapping("/rpt-dia/{id_proyecto}/{date}")
	public ResponseEntity<?> rptGlobalProyectoByDay(@PathVariable Long id_proyecto, @PathVariable Date date){
		Map<String, Object> response = new HashMap<>();	
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dia = formatter.format(date);
		
		List<Object> datos = iGridServiceImp.rptGlobalProyectoByDay( dia, id_proyecto);
		
		response.put("successful", true);
		response.put("datos", datos);
		response.put("dia", dia);
		
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		 
	}
	
	@GetMapping("/rpt-semanal/{id_proyecto}")
	public ResponseEntity<?> rptGlobalProyectoSemanal(@PathVariable Long id_proyecto){
		
		Map<String, Object> response = new HashMap<>();	
		List<Configuracion> config_semanas = iConfiguracionServiceImp.findAllByIdProyecto(id_proyecto);
		List<Object> datos = new ArrayList<Object>();
		
		
		for( Configuracion c : config_semanas ) {
			
			/*c.setFecha_inicio(this.utils.getDateCorrecta(c.getFecha_inicio(), 1));
			c.setFecha_fin(this.utils.getDateCorrecta(c.getFecha_fin(), 1));*/
			
			List<Object> aux = new ArrayList<Object>();			
					
			SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
			String f_start = formatter.format(  c.getFecha_inicio() );
			String f_end = formatter.format( c.getFecha_fin() );
			
			aux.add(iGridServiceImp.rptGlobalProyectoSemanal(f_start, f_end, id_proyecto));
			aux.add(c);
			
			datos.add( aux );
			
		}
		
		response.put("successful", true);
		response.put("datos", datos);
			
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		 
	}
	
	@GetMapping("/rpt-ip-semanal-actual/{id_proyecto}")
	public ResponseEntity<?> rptIpSemanalActual(@PathVariable Long id_proyecto){
		Map<String, Object> response = new HashMap<>();
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String ahora = formatter.format(now);
		
		try {
			now = formatter.parse(ahora);
			Configuracion sem = iConfiguracionServiceImp.getSemanaActual(now, id_proyecto);
			
			if( sem != null) {
				//Long proyecto_id = sem.getProyecto().getId_proyecto();
				Date inicio = sem.getFecha_inicio();
				Date fin = sem.getFecha_fin();
				
				
				response.put("successful", true);
				response.put("km_total_shared", iIpServiceImp.kmTotalShared(id_proyecto));
				response.put("pool_cliente_sem", iIpServiceImp.getIpPoolClientSemana(fin, id_proyecto));
				response.put("qc_sem",  iIpServiceImp.getIpOnQC(fin , id_proyecto));
				response.put("shared_sem", iIpServiceImp.getIpSharedSemana(inicio, fin , id_proyecto));
				
				/*sem.setFecha_inicio(this.utils.getDateCorrecta(inicio, 1));
				sem.setFecha_fin(this.utils.getDateCorrecta(fin, 1));*/
				response.put("semana", sem);
				

			}else {
				
				response.put("successful", false);
				response.put("message",  "No hay registro de semana para este proyecto");
				
			}
			
		} catch (ParseException e) {
			response.put("successful", false);
			response.put("message",  e.getMessage());
			
		}
		

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	

}

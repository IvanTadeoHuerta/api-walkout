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
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.pro.walker.api.models.entity.IP;
import com.tech.pro.walker.api.services.IIpServiceImp;

@RestController
@RequestMapping("api/walkout")
public class IpController {

	@Autowired
	private IIpServiceImp iIpServiceImp;



	@Secured({ "ROLE_HQ", "ROLE_ADMIN", "ROLE_C_IP" })
	@GetMapping("/get-IPS")
	public List<IP> index() {
		return iIpServiceImp.findAll();
	}

	@Secured({ "ROLE_CREAR_IP" })
	@PostMapping("/crear-IP")
	public ResponseEntity<?> save(@RequestBody IP IP) {
		Map<String, Object> response = new HashMap<>();
		

		int reg = iIpServiceImp.findByIp(IP.getIp().trim());
		boolean b = true;
		String menssage = "";

		if (reg == 0) {
			
			//IP.setFecha_asignacion(this.utils.getDateCorrecta( IP.getFecha_asignacion(), -1));

			response.put("ip", iIpServiceImp.save(IP));
			menssage = "Registro exitoso!";

		} else {
			b = false;
			menssage = "La IP ya fue registrada";
		}

		response.put("successful", b);
		response.put("message", menssage);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@Secured({ "ROLE_EDITAR_IP" })
	@PostMapping("/update-IP")
	public ResponseEntity<?> update(@RequestBody IP IP) {
		Map<String, Object> response = new HashMap<>();

		int reg = iIpServiceImp.findByIp(IP.getIp().trim());
		boolean b = true;
		String menssage = "";

		if (reg == 0) {

			response.put("ip", iIpServiceImp.save(IP));
			menssage = "Actualización exitosa!";

		} else {

			Long id_ip = iIpServiceImp.getIdIpByIP(IP.getIp());

			if (id_ip.equals(IP.getId_ip())) {

				// Condiciones para no alterar la fecha
				
				/*Date das = this.utils.getDateCorrecta(iIpServiceImp.fecha_asignacion(id_ip), 1);
				
				if( das == null) {
					
					IP.setFecha_asignacion(IP.getFecha_asignacion());
					
				}else if (das != null && das.getTime() != IP.getFecha_asignacion().getTime()) {

					IP.setFecha_asignacion(IP.getFecha_asignacion());

				} else {
					IP.setFecha_asignacion((this.utils.getDateCorrecta(IP.getFecha_asignacion(), -1)));
				}
				
				
				Date dcamp = this.utils.getDateCorrecta(iIpServiceImp.fecha_envio_campo(id_ip), 1);

				if (dcamp != null && dcamp.getTime() != IP.getFecha_envio_campo().getTime()) {

					IP.setFecha_envio_campo(IP.getFecha_envio_campo());

				} else {
					IP.setFecha_envio_campo((this.utils.getDateCorrecta(IP.getFecha_envio_campo(), -1)));
				}
				

				

				Date dl = this.utils.getDateCorrecta(iIpServiceImp.fecha_levantamiento(id_ip), 1);
				
				if(dl == null) {
					
					IP.setFecha_levantamiento(IP.getFecha_levantamiento());
					
				}else if (dl != null && dl.getTime() != IP.getFecha_levantamiento().getTime()) {

					IP.setFecha_levantamiento(IP.getFecha_levantamiento());

				} else {
					IP.setFecha_levantamiento(this.utils.getDateCorrecta(IP.getFecha_levantamiento(), -1));
				}

				Date dq = this.utils.getDateCorrecta(iIpServiceImp.fecha_qc(id_ip), 1);
				if (dq!= null && dq.getTime() != IP.getFecha_qc().getTime()) {

					IP.setFecha_qc(IP.getFecha_qc());

				} else {
					IP.setFecha_qc(this.utils.getDateCorrecta(IP.getFecha_qc(), -1));
				}
				
				Date dc = this.utils.getDateCorrecta(iIpServiceImp.fecha_cliente(id_ip), 1);
				if (dc!= null && dc.getTime() != IP.getFecha_cliente().getTime()) {

					IP.setFecha_cliente(IP.getFecha_cliente());

				} else {
					IP.setFecha_cliente(this.utils.getDateCorrecta(IP.getFecha_cliente(), -1));
				}
				
				Date ds = this.utils.getDateCorrecta(iIpServiceImp.fecha_shared_point(id_ip), 1);
				if (ds!=null && ds.getTime() != IP.getFecha_shared_point().getTime()) {

					IP.setFecha_shared_point(IP.getFecha_shared_point());

				} else {
					IP.setFecha_shared_point(this.utils.getDateCorrecta(IP.getFecha_shared_point(), -1));
				}
								
				
				IP.setFecha_asignacion_caminar(this.utils.getDateCorrecta(IP.getFecha_asignacion_caminar(), -1)); */

				// Fin de Condiciones para no alterar la fecha

				response.put("ip", iIpServiceImp.save(IP));
				menssage = "Actualización exitosa!";

			} else {
				b = false;
				menssage = "La IP ya fue registrada";
			}

		}

		response.put("successful", b);
		response.put("message", menssage);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@Secured({ "ROLE_HQ", "ROLE_ADMIN", "ROLE_CONSULTA_IP" })
	@GetMapping("/get-IP/{id_IP}")
	public ResponseEntity<?> getIP(@PathVariable Long id_IP) {
		Map<String, Object> response = new HashMap<>();

		IP ip = iIpServiceImp.findById(id_IP).orElse(null);

		/*ip.setFecha_asignacion(this.utils.getDateCorrecta(ip.getFecha_asignacion(), 1));
		ip.setFecha_envio_campo(this.utils.getDateCorrecta(ip.getFecha_envio_campo(), 1));
		ip.setFecha_levantamiento(this.utils.getDateCorrecta(ip.getFecha_levantamiento(), 1));
		ip.setFecha_qc(this.utils.getDateCorrecta(ip.getFecha_qc(), 1));
		ip.setFecha_cliente(this.utils.getDateCorrecta(ip.getFecha_cliente(), 1));
		ip.setFecha_shared_point(this.utils.getDateCorrecta(ip.getFecha_shared_point(), 1));
		ip.setFecha_asignacion_caminar(this.utils.getDateCorrecta(ip.getFecha_asignacion_caminar(), 1));*/

		response.put("ip", ip);
		response.put("id_proyecto", iIpServiceImp.getIdProyecto(id_IP));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@Secured({ "ROLE_HQ" })
	@DeleteMapping("/delete-IP/{id_IP}")
	public ResponseEntity<?> delete(@PathVariable Long id_IP) {
		Map<String, Object> response = new HashMap<>();
		iIpServiceImp.deleteById(id_IP);
		response.put("successful", true);
		response.put("message", " IP eliminada ");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@Secured({ "ROLE_HQ" })
	@PostMapping("/changeStatusIP")
	public ResponseEntity<?> changeStatus(@RequestBody IP IP) {

		Map<String, Object> response = new HashMap<>();
		Date dia = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String ahora = formatter.format(dia);
		try {
			dia = formatter.parse(ahora);

			iIpServiceImp.changeStatus(IP.getId_ip(), IP.getQC());

			switch (IP.getQC()) {

			case 0:
				IP.setFecha_envio_campo(dia);
				iIpServiceImp.updateFechaCampo(IP.getId_ip(), dia, IP.getTotal_grids(), IP.isActualizacion(),
						IP.getKm_actualizados());
				break;
			case 1:
				IP.setFecha_qc(dia);
				iIpServiceImp.updateFechaQC(IP.getId_ip(), dia);
				break;
			case 2:
				IP.setFecha_cliente(dia);
				iIpServiceImp.updateFechaCliente(IP.getId_ip(), dia);
				break;
			case 3:
				IP.setFecha_shared_point(dia);
				iIpServiceImp.updateFechaShared(IP.getId_ip(), dia);
				break;

			}

			response.put("successful", true);
			response.put("message", " Se actualizó el estatus");
			response.put("dia", dia);

		} catch (ParseException e) {
			response.put("successful", false);
			response.put("message", e.getMessage());
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@Secured({ "ROLE_HQ", "ROLE_ADMIN", "ROLE_CONSULTA_IP" })
	@GetMapping("/get-IPS/proyecto/{id_proyecto}")
	public ResponseEntity<?> getIpsProyecto(@PathVariable Long id_proyecto) {
		Map<String, Object> response = new HashMap<>();

		List<IP> ips = iIpServiceImp.findAllWhereIdProyecto(id_proyecto);
		//ips = this.utils.setFechas(ips, 1);

		List<Object> all = new ArrayList<>();

		for (IP ip : ips) {

			List<Object> participantes = new ArrayList<>();
			participantes.add(ip.getId_ip());
			participantes.add(iIpServiceImp.getParticipantesByIp(ip.getId_ip()));

			all.add(participantes);

		}

		response.put("ips", ips);
		response.put("participantes", all);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@Secured({ "ROLE_HQ", "ROLE_IPS_EN_CAMPO" })
	@GetMapping("get-ips-en-campo/proyecto/{id_proyecto}")
	public ResponseEntity<?> getIpsEnCampo(@PathVariable Long id_proyecto) {
		Map<String, Object> response = new HashMap<>();
		List<IP> ips = iIpServiceImp.getIpsEnCampo(id_proyecto);

		//ips = utils.setFechas(ips, 1);

		response.put("ips", ips);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@Secured({ "ROLE_HQ" })
	@PostMapping("/update-fecha-asigna-camina")
	public ResponseEntity<?> updateFechaAsignacion(@RequestBody IP ip) {

		Map<String, Object> response = new HashMap<>();
		iIpServiceImp.updateFechaAsignacionCamina(ip.getId_ip(), ip.getFecha_asignacion_caminar());

		//ip.setFecha_asignacion(utils.getDateCorrecta(ip.getFecha_asignacion_caminar(), 1));

		response.put("successful", true);
		response.put("datos", ip);
		response.put("message", " Se actualizó la fecha");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}

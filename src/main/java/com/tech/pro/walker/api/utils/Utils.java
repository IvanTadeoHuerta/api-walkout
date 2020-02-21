package com.tech.pro.walker.api.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tech.pro.walker.api.models.entity.IP;



@Component
public class Utils {
	

	
	public  Date getDateCorrecta(Date f, int dias) {
		
		if( f != null ) {
		Calendar c = Calendar.getInstance();
		c.setTime(f);
	    c.add(Calendar.DATE, dias);
	    return c.getTime();
	    }else {
	    	return null;
	    }
	}
	
	public  List<IP> setFechas(List<IP> ips, int dias){
		for(IP ip: ips) {
			ip.setFecha_asignacion(this.getDateCorrecta(ip.getFecha_asignacion(), dias));
			ip.setFecha_envio_campo(this.getDateCorrecta(ip.getFecha_envio_campo(), dias));
			ip.setFecha_levantamiento(this.getDateCorrecta(ip.getFecha_levantamiento(), dias));
			ip.setFecha_qc(this.getDateCorrecta(ip.getFecha_qc(), dias));
			ip.setFecha_cliente(this.getDateCorrecta(ip.getFecha_cliente(), dias));
			ip.setFecha_shared_point(this.getDateCorrecta(ip.getFecha_shared_point(), dias));
			ip.setFecha_asignacion_caminar(this.getDateCorrecta(ip.getFecha_asignacion_caminar(), dias));
		}
		
		return ips;
	}
	
	

}

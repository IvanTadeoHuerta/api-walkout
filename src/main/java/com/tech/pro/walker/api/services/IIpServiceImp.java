package com.tech.pro.walker.api.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tech.pro.walker.api.models.dao.IIpDao;
import com.tech.pro.walker.api.models.entity.IP;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class IIpServiceImp implements IIpService{
	
	@Autowired
	private IIpDao iIpDao;

	@Override
	@Transactional(readOnly = true)
	public List<IP> findAll() {
		return iIpDao.findAll();
	}

	@Override
	@Transactional
	public IP save(IP IP) {
		return iIpDao.save(IP);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<IP> findById(Long id_IP) {
		return iIpDao.findById(id_IP);
	}

	@Override
	@Transactional
	public void deleteById(Long id_IP) {
		iIpDao.deleteById(id_IP);
	}

	@Override
	@Transactional(readOnly= true)
	public List<IP> findAllWhereIdProyecto(Long id_proyecto) {
		return iIpDao.findAllWhereIdProyecto(id_proyecto);
	}

	@Override
	@Transactional(readOnly= true)
	public int findByIp(String ip) {
		return iIpDao.findByIp(ip);
	}

	@Override
	@Transactional(readOnly= true)
	public Long getIdIpByIP(String ip) {
		return iIpDao.getIdIpByIP(ip);
	}

	@Override
	@Transactional(readOnly= true)
	public Long getIdProyecto(Long id_ip) {
		return iIpDao.getIdProyecto(id_ip);
	}

	@Override
	@Transactional(readOnly= true)
	public List<Object> getParticipantesByIp(Long id_ip) {
		return iIpDao.getParticipantesByIp(id_ip);
	}

	@Override
	@Transactional
	public void changeStatus(Long id_ip, int estatus) {
		iIpDao.changeStatus(id_ip, estatus);
	}

	

	@Override
	@Transactional
	public void updateFechaCliente(Long id_ip, Date dia) {
		// TODO Auto-generated method stub
		iIpDao.updateFechaCliente(id_ip, dia);
	}

	@Override
	@Transactional
	public void updateFechaQC(Long id_ip, Date dia) {
		// TODO Auto-generated method stub
		iIpDao.updateFechaQC(id_ip, dia);
	}

	@Override
	@Transactional
	public void updateFechaShared(Long fecha_shared_point, Date dia) {
		// TODO Auto-generated method stub
		iIpDao.updateFechaShared(fecha_shared_point, dia);
	}

	@Override
	@Transactional
	public void updateFechaCampo(Long id_ip, Date dia, int total_grids, boolean actualizacion, Double km_actualizados) {
		iIpDao.updateFechaCampo(id_ip, dia, total_grids, actualizacion, km_actualizados);
		
	}

	@Override
	@Transactional(readOnly= true)
	public List<IP> getIpSharedSemana(Date fecha_inicio, Date fecha_fin, Long id_proyecto) {
		return iIpDao.getIpSharedSemana(fecha_inicio, fecha_fin, id_proyecto);
	}

	@Override
	@Transactional(readOnly= true)
	public List<IP> getIpPoolClientSemana(Date fecha_fin, Long id_proyecto) {
		return iIpDao.getIpPoolClientSemana(fecha_fin, id_proyecto);
	}

	@Override
	@Transactional(readOnly= true)
	public List<IP> getIpOnQC(Date fecha_fin, Long id_proyecto) {
		return iIpDao.getIpOnQC(fecha_fin, id_proyecto);
	}

	@Override
	@Transactional(readOnly= true)
	public Double kmTotalShared(Long id_proyecto) {
		return iIpDao.kmTotalShared(id_proyecto);
	}

	@Override
	@Transactional(readOnly= true)
	public List<IP> getIpsEnCampo(Long id_proyecto) {
		return iIpDao.getIpsEnCampo(id_proyecto);
	}

	@Override
	@Transactional
	public void updateFechaAsignacionCamina(Long id_ip, Date fecha) {
		iIpDao.updateFechaAsignacionCamina(id_ip, fecha);
	}


	@Override
	@Transactional(readOnly= true)
	public Date fecha_levantamiento(Long id_ip) {
		return iIpDao.fecha_levantamiento(id_ip);
	}

	@Override
	@Transactional(readOnly= true)
	public Date fecha_qc(Long id_ip) {
		return iIpDao.fecha_qc(id_ip);
	}

	@Override
	@Transactional(readOnly= true)
	public Date fecha_cliente(Long id_ip) {
		return iIpDao.fecha_cliente(id_ip);
	}

	@Override
	@Transactional(readOnly= true)
	public Date fecha_shared_point(Long id_ip) {
		return iIpDao.fecha_shared_point(id_ip);
	}

	@Override
	@Transactional(readOnly= true)
	public Date fecha_asignacion(Long id_ip) {
		return iIpDao.fecha_asignacion(id_ip);
	}

	@Override
	@Transactional(readOnly= true)
	public Date fecha_envio_campo(Long id_ip) {
		return iIpDao.fecha_envio_campo(id_ip);
	}


	
	
	
	
	
	
	
	
	
	
	
	

}

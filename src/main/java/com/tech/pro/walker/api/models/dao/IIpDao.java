package com.tech.pro.walker.api.models.dao;

import com.tech.pro.walker.api.models.entity.IP;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IIpDao extends JpaRepository<IP, Long>{
	
	@Query("from IP i where i.proyecto.id_proyecto =?1 order by fecha_registro desc")
	public List<IP> findAllWhereIdProyecto(Long id_proyecto);
	
	@Query("select count(1) from IP i where i.ip =?1")
	public int findByIp(String ip);
	
	@Query("select i.id_ip from IP i where i.ip =?1")
	public Long getIdIpByIP(String ip);
	
	@Query("select i.proyecto.id_proyecto from IP i where i.id_ip =?1")
	public Long  getIdProyecto(Long id_ip); 
	
	@Modifying
	@Query( value= "SELECT CONCAT(W.nombre, ' ', W.apellido_paterno ) AS walker FROM walkout.ips AS  IP\r\n" + 
			"INNER JOIN walkout.grids AS G ON ( G.id_ip = IP.id_ip AND G.estatus = 1)\r\n" + 
			"INNER JOIN walkout.asignacion AS A ON ( A.id_grid = G.id_grid )\r\n" + 
			"INNER JOIN walkout.walkers AS W ON ( A.id_walker = W.id_walker)\r\n" + 
			"INNER JOIN walkout.proyecto_walkers as PW ON ( PW.id_walker = W.id_walker )\r\n" + 
			"WHERE IP.id_ip =?1 GROUP BY W.id_walker", nativeQuery = true)
	public List<Object> getParticipantesByIp(Long id_ip);
	
	@Modifying
	@Query("update IP i set i.QC =?2 where i.id_ip =?1")
	public void changeStatus(Long id_ip, int estatus);
	
	@Modifying
	@Query("update IP i set i.fecha_envio_campo =?2 , i.total_grids =?3, i.actualizacion =?4, i.km_actualizados =?5  where i.id_ip =?1")
	public void updateFechaCampo(Long id_ip, Date dia, int total_grids, boolean actualizacion, Double km_actualizados );
	
	@Modifying
	@Query("update IP i set i.fecha_cliente =?2 where i.id_ip =?1")
	public void updateFechaCliente(Long id_ip, Date dia);
	
	@Modifying
	@Query("update IP i set i.fecha_qc =?2 where i.id_ip =?1")
	public void updateFechaQC(Long id_ip, Date dia);
	
	@Modifying
	@Query("update IP i set i.fecha_shared_point =?2 where i.id_ip =?1")
	public void updateFechaShared(Long fecha_shared_point, Date dia);
	

	@Query("from IP i where i.fecha_shared_point >=?1 and i.fecha_shared_point <=?2 and i.proyecto.id_proyecto =?3 and i.QC=3 order by i.fecha_shared_point desc")
	public List<IP> getIpSharedSemana(Date fecha_inicio, Date fecha_fin, Long id_proyecto);


	@Query("from IP i where (i.fecha_cliente<=?1 ) and i.proyecto.id_proyecto=?2 and i.QC=2 order by i.fecha_cliente  desc")
	public List<IP> getIpPoolClientSemana(Date fecha_fin, Long id_proyecto);
	

	@Query("from IP i where ( i.fecha_qc<=?1 ) and i.proyecto.id_proyecto=?2 and (i.QC=1 OR  i.QC=4) order by i.fecha_qc desc")
	public List<IP> getIpOnQC(Date fecha_fin, Long id_proyecto);
	

	@Query("select sum(pies) from IP i where i.QC=3 and i.proyecto.id_proyecto=?1")
	public Double kmTotalShared(Long id_proyecto);
	
	@Query("from IP i where  i.proyecto.id_proyecto=?1 and i.QC=0 order by ip.fecha_envio_campo desc")
	public List<IP> getIpsEnCampo(Long id_proyecto);
	
	@Modifying
	@Query("update IP i set i.fecha_asignacion_caminar =?2 where i.id_ip =?1")
	public void updateFechaAsignacionCamina(Long id_ip, Date fecha);
	
	@Query("select i.fecha_asignacion from IP i where i.id_ip=?1")
	public Date fecha_asignacion(Long id_ip);
	
	@Query("select i.fecha_envio_campo from IP i where i.id_ip=?1")
	public Date fecha_envio_campo(Long id_ip);
	
	@Query("select i.fecha_levantamiento from IP i where i.id_ip=?1")
	public Date fecha_levantamiento(Long id_ip);
	
	@Query("select i.fecha_qc from IP i where i.id_ip=?1")
	public Date fecha_qc(Long id_ip);
	
	@Query("select i.fecha_cliente from IP i where i.id_ip=?1")
	public Date fecha_cliente(Long id_ip);
	
	@Query("select i.fecha_shared_point from IP i where i.id_ip=?1")
	public Date fecha_shared_point(Long id_ip);
	
	@Modifying
	@Query("update IP i set i.ip =?2, i.pies=?3, i.QC=?4, i.fecha_asignacion=?5, i.fecha_envio_campo=?6, i.fecha_levantamiento=?7, i.fecha_qc=?8, i.fecha_cliente=?9, i.fecha_shared_point=?10, i.fecha_asignacion_caminar=?11, i.total_grids=?12, i.actualizacion=?13, i.km_actualizados=?14, i.tipo=?15, i.estatus=?16   where i.id_ip =?1")
	public void updateIP(Long id_ip, 
			String ip, 
			Double pies, 
			int QC, 
			Date fecha_asignacion,
			Date fecha_envio_campo,
			Date fecha_levantamiento,
			Date fecha_qc,
			Date fecha_cliente,
			Date fecha_shared_point,
			Date fecha_asignacion_caminar,
			int total_grids, 
			boolean actualizacion,
			Double km_actualizados, 
			int tipo, boolean estatus);
	
	

}

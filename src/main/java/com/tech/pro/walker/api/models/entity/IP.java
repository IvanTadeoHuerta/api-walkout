package com.tech.pro.walker.api.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "IPS")
public class IP  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_ip;
	
	@NotEmpty(message="IP requerida")
	@Column(nullable=false, unique = true)
	private String ip;
	
	@Column
	private Double pies;
	
	@Column
	private int QC;
	
	
	@Column(nullable = false)
	private Date fecha_asignacion;
	
	@Column(nullable = true)
	private Date fecha_envio_campo;
	
	@Column(nullable = true)
	private Date fecha_levantamiento;
	
	@Column(nullable = true)
	private Date fecha_qc;
	
	@Column(nullable = true)
	private Date fecha_cliente;
	
	@Column(nullable = true)
	private Date fecha_shared_point;
	
	@Column(nullable = true)
	private Date fecha_asignacion_caminar;
	
	@Column(nullable = true)
	private int total_grids;
	
	@Column(columnDefinition = "boolean default false", nullable = false)
	private boolean actualizacion;
	
	@Column
	private Double km_actualizados;
	
	@Column
	private int tipo;
	
	@NotEmpty(message="Ubicación requerida")
	@Column(nullable=false, length=300)
	private String ubicacion;
	
	@Column
	private boolean estatus;
	
	@Column
	private Date fecha_registro;
	
	@Column
	private Long usuario_registro;
	
	@Column
	private Date fecha_update;
	
	@Column
	private Long usuario_update;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name =  "id_proyecto")
	@JsonBackReference(value="proyectoIp")
	private Proyecto proyecto;
	
	@OneToMany(mappedBy= "ip" , cascade = CascadeType.ALL)
	@JsonManagedReference(value="ipGrid")
	private List<Grid> grids = new ArrayList<>();
	
	public Long getId_ip() {
		return id_ip;
	}
	public void setId_ip(Long id_ip) {
		this.id_ip = id_ip;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public Double getPies() {
		return pies;
	}
	
	public void setPies(Double pies) {
		this.pies = pies;
	}
	public int getQC() {
		return QC;
	}
	
	public void setQC(int qC) {
		QC = qC;
	}
	
	public String getUbicacion() {
		return ubicacion;
	}
	
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public boolean isEstatus() {
		return estatus;
	}
	
	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	
	public Date getFecha_registro() {
		return fecha_registro;
	}
	
	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	public Long getUsuario_registro() {
		return usuario_registro;
	}
	
	public void setUsuario_registro(Long usuario_registro) {
		this.usuario_registro = usuario_registro;
	}
	
	public Date getFecha_update() {
		return fecha_update;
	}
	
	public void setFecha_update(Date fecha_update) {
		this.fecha_update = fecha_update;
	}
	
	public Long getUsuario_update() {
		return usuario_update;
	}
	
	public void setUsuario_update(Long usuario_update) {
		this.usuario_update = usuario_update;
	}
	
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	
	public List<Grid> getGrids() {
		return grids;
	}
	
	public void setGrids(List<Grid> grids) {
		this.grids = grids;
	}
	
	public Date getFecha_levantamiento() {
		return fecha_levantamiento;
	}
	public void setFecha_levantamiento(Date fecha_levantamiento) {
		this.fecha_levantamiento = fecha_levantamiento;
	}
	
	public int getTipo() {
		return tipo;
	}
	
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public Date getFecha_asignacion() {
		return fecha_asignacion;
	}
	
	public void setFecha_asignacion(Date fecha_asignacion) {
		this.fecha_asignacion = fecha_asignacion;
	}
	
	public Date getFecha_envio_campo() {
		return fecha_envio_campo;
	}
	
	public void setFecha_envio_campo(Date fecha_envio_campo) {
		this.fecha_envio_campo = fecha_envio_campo;
	}
	
	public Date getFecha_qc() {
		return fecha_qc;
	}
	
	public void setFecha_qc(Date fecha_qc) {
		this.fecha_qc = fecha_qc;
	}
	
	public Date getFecha_cliente() {
		return fecha_cliente;
	}
	
	public void setFecha_cliente(Date fecha_cliente) {
		this.fecha_cliente = fecha_cliente;
	}
	
	
	public Date getFecha_shared_point() {
		return fecha_shared_point;
	}
	public void setFecha_shared_point(Date fecha_shared_point) {
		this.fecha_shared_point = fecha_shared_point;
	}
	public int getTotal_grids() {
		return total_grids;
	}
	public void setTotal_grids(int total_grids) {
		this.total_grids = total_grids;
	}
	public boolean isActualizacion() {
		return actualizacion;
	}
	
	public void setActualizacion(boolean actualizacion) {
		this.actualizacion = actualizacion;
	}
	
	public Double getKm_actualizados() {
		return km_actualizados;
	}
	
	public void setKm_actualizados(Double km_actualizados) {
		this.km_actualizados = km_actualizados;
	}
	
	public Date getFecha_asignacion_caminar() {
		return fecha_asignacion_caminar;
	}
	
	public void setFecha_asignacion_caminar(Date fecha_asignacion_caminar) {
		this.fecha_asignacion_caminar = fecha_asignacion_caminar;
	}
	

}

package com.tech.pro.walker.api.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.JoinColumn;

@Entity
@Table(name = "Proyectos")
public class Proyecto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_proyecto;
	
	@NotEmpty(message="Nombre es requerido")
	@Column(nullable=false, length=300)
	private String nombre;
	
	@NotEmpty(message="Descripción requerida")
	@Column(nullable=false, length=500)
	private String descripcion;
	
	@Column
	private boolean estatus;
	
	@JsonIgnore
	@OneToMany(mappedBy= "proyecto" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference("proyectoIp")
	private List<IP> ips;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "proyecto_walkers",
			joinColumns = @JoinColumn(name = "id_proyecto", nullable = false),
					 inverseJoinColumns = @JoinColumn(name="id_walker", nullable = true)
	)
	@JsonIgnore
	@JsonManagedReference
	private List<Walker> walkers_proyecto; 
	
	
	@OneToMany(mappedBy = "proyecto")
	@JsonManagedReference("configProyecto")
	private List<Configuracion> configuraciones;
	


	public Long getId_proyecto() {
		return id_proyecto;
	}

	public void setId_proyecto(Long id_proyecto) {
		this.id_proyecto = id_proyecto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}

	public List<IP> getIps() {
		return ips;
	}

	public void setIps(List<IP> ips) {
		this.ips = ips;
	}

	public List<Walker> getWalkers_proyecto() {
		return walkers_proyecto;
	}

	public void setWalkers_proyecto(List<Walker> walkers_proyecto) {
		this.walkers_proyecto = walkers_proyecto;
	}

	public List<Configuracion> getConfiguraciones() {
		return configuraciones;
	}

	public void setConfiguraciones(List<Configuracion> configuraciones) {
		this.configuraciones = configuraciones;
	}
	
	



}

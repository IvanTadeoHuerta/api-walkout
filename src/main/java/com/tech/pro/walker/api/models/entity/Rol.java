package com.tech.pro.walker.api.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Roles")
public class Rol implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_rol;
	
	@NotEmpty(message="Nombre de rol es requerido")
	@Column(nullable=false, unique = true, length=100)
	private String rol;
	
	@NotEmpty(message="Descripción es requerido")
	@Column(nullable=false, length=300)
	private String descripcion;
	
	@Column
	private boolean estatus;
	
	@JsonIgnore
	@ManyToMany(mappedBy="roles" ,fetch = FetchType.LAZY)
	private List<Walker> walker;

	public Long getId_rol() {
		return id_rol;
	}

	public void setId_rol(Long id_rol) {
		this.id_rol = id_rol;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
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

	public List<Walker> getWalker() {
		return walker;
	}

	public void setWalker(List<Walker> walker) {
		this.walker = walker;
	}

	
	
	
	
	

}

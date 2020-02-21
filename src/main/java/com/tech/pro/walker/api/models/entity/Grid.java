package com.tech.pro.walker.api.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Grids")
public class Grid implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_grid;
	
	@Column
	private Double total_pies;
	
	@Column
	private int total_casas;
	
	@Column
	private int total_negocios;
	
	@Column
	private int total_escuelas;
	
	@Column
	private int total_iglesias;
	
	@Column
	private int total_baldios;
	
	@NotNull(message="Numero de plano es requerido")
	@Column(nullable=false)
	private String numero_plano;
	
	@Column(length=300)
	private String comentarios;
	
	
	@Column
	private String archivo;
	
	@Column
	private boolean estatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name =  "id_ip")
	@JsonBackReference(value="ipGrid")
	private IP ip;
	
	@OneToMany(mappedBy="grid")
	@JsonManagedReference(value="evidenciaGrid")
	private List<Evidencia> evidencias;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinTable(name="Asignacion", joinColumns = @JoinColumn(name="id_grid")
	, inverseJoinColumns = @JoinColumn(name ="id_walker")
	, uniqueConstraints = {@UniqueConstraint(columnNames= {"id_grid","id_walker"})})
	@JsonIgnore
	@JsonManagedReference
	private List<Walker> walkers;
	
	
	public Long getId_grid() {
		return id_grid;
	}

	public void setId_grid(Long id_grid) {
		this.id_grid = id_grid;
	}


	public Double getTotal_pies() {
		return total_pies;
	}

	public void setTotal_pies(Double total_pies) {
		this.total_pies = total_pies;
	}

	public int getTotal_casas() {
		return total_casas;
	}

	public void setTotal_casas(int total_casas) {
		this.total_casas = total_casas;
	}

	public int getTotal_negocios() {
		return total_negocios;
	}

	public void setTotal_negocios(int total_negocios) {
		this.total_negocios = total_negocios;
	}

	public int getTotal_escuelas() {
		return total_escuelas;
	}

	public void setTotal_escuelas(int total_escuelas) {
		this.total_escuelas = total_escuelas;
	}

	public int getTotal_iglesias() {
		return total_iglesias;
	}

	public void setTotal_iglesias(int total_iglesias) {
		this.total_iglesias = total_iglesias;
	}

	public int getTotal_baldios() {
		return total_baldios;
	}

	public void setTotal_baldios(int total_baldios) {
		this.total_baldios = total_baldios;
	}

	public String getNumero_plano() {
		return numero_plano;
	}

	public void setNumero_plano(String numero_plano) {
		this.numero_plano = numero_plano;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}

	public IP getIp() {
		return ip;
	}

	public void setIp(IP ip) {
		this.ip = ip;
	}

	public List<Evidencia> getEvidencias() {
		return evidencias;
	}

	public void setEvidencias(List<Evidencia> evidencias) {
		this.evidencias = evidencias;
	}

	public List<Walker> getWalkers() {
		return walkers;
	}

	public void setWalkers(List<Walker> walkers) {
		this.walkers = walkers;
	}
	
	
	
	
	
	
	
}

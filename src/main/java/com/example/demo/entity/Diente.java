package com.example.demo.entity;

import jakarta.persistence.Entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Diente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int numero;
	private String notas;
	private int paciente;
	private int cuadrante;
	private String estado;
    private String imagenUrl; 
	
	public int getCuadrante() {
		return cuadrante;
	}
	public void setCuadrante(int cuadrante) {
		this.cuadrante = cuadrante;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPaciente() {
		return paciente;
	}
	public void setPaciente(int paciente) {
		this.paciente = paciente;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNotas() {
		return notas;
	}
	public void setNotas(String notas) {
		this.notas = notas;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getImagenUrl() {
		return imagenUrl;
	}
	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}
	@Override
	public String toString() {
		return "Diente [id=" + id + ", numero=" + numero + ", notas=" + notas + ", paciente=" + paciente
				+ ", cuadrante=" + cuadrante + ", estado=" + estado + ", imagenUrl=" + imagenUrl + "]";
	}
	
	
	
	
		
	
}

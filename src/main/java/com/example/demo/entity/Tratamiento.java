package com.example.demo.entity;

import jakarta.persistence.Entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tratamiento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String tipo, fecha, procedimientos, resultados, medicamentos, instrucciones;
	//relación many to one id paciente y odontologos
	private int paciente;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getProcedimientos() {
		return procedimientos;
	}
	public void setProcedimientos(String procedimientos) {
		this.procedimientos = procedimientos;
	}
	public String getResultados() {
		return resultados;
	}
	public void setResultados(String resultados) {
		this.resultados = resultados;
	}
	public String getMedicamentos() {
		return medicamentos;
	}
	public void setMedicamentos(String medicamentos) {
		this.medicamentos = medicamentos;
	}
	public String getInstrucciones() {
		return instrucciones;
	}
	public void setInstrucciones(String instrucciones) {
		this.instrucciones = instrucciones;
	}
	public int getPaciente() {
		return paciente;
	}
	public void setPaciente(int paciente) {
		this.paciente = paciente;
	}
	@Override
	public String toString() {
		return "Tratamiento [id=" + id + ", paciente=" + paciente + ", tipo=" + tipo + ", fecha=" + fecha
				+ ", procedimientos=" + procedimientos + ", resultados=" + resultados + ", medicamentos=" + medicamentos
				+ ", instrucciones=" + instrucciones + "]";
	}
	
		
	
}

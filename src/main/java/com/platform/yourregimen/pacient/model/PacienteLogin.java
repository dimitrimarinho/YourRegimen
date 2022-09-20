package com.platform.yourregimen.pacient.model;

import java.util.UUID;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

public class PacienteLogin {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID id;
	
	private String nome;
	
	private String usuario;
	
	private String senha;
	
	private String token;
	
	private double altura;

	private double peso;

	private double maxCalories;

	private double minCalories;

	public PacienteLogin(UUID id, String nome, String usuario, String senha, String token, double altura, double peso,
			double maxCalories, double minCalories) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.token = token;
		this.altura = altura;
		this.peso = peso;
		this.maxCalories = maxCalories;
		this.minCalories = minCalories;
	}

	public PacienteLogin() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getMaxCalories() {
		return maxCalories;
	}

	public void setMaxCalories(double maxCalories) {
		this.maxCalories = maxCalories;
	}

	public double getMinCalories() {
		return minCalories;
	}

	public void setMinCalories(double minCalories) {
		this.minCalories = minCalories;
	}
		
}

package com.platform.yourregimen.model;

import java.util.UUID;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

public class AdminLogin {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID id;
	
	private String nomeUsuario;
	
	private String loginUsuario;
	
	private String senhaUsuario;
	
	private String token;

	public AdminLogin(UUID id, String nomeUsuario, String loginUsuario, String senhaUsuario, String token) {
		this.id = id;
		this.nomeUsuario = nomeUsuario;
		this.loginUsuario = loginUsuario;
		this.senhaUsuario = senhaUsuario;
		this.token = token;
	}

	public AdminLogin() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
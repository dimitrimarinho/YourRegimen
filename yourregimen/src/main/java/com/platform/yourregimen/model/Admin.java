package com.platform.yourregimen.model;

import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_admin")
public class Admin {
    
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Id
    private UUID id;

	@NotNull
	private String nome;

	@NotNull
	private String usuario;

	@NotNull
	private String senha;
    
	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("admin")
	private List<Regimen>regimen;
	
	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("admin")
	private List<Financeiro>financeiro;

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

	public List<Regimen> getRegimen() {
		return regimen;
	}

	public void setRegimen(List<Regimen> regimen) {
		this.regimen = regimen;
	}

	public List<Financeiro> getFinanceiro() {
		return financeiro;
	}

	public void setFinanceiro(List<Financeiro> financeiro) {
		this.financeiro = financeiro;
	}
    
}
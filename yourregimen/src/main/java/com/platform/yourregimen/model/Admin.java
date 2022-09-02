package com.platform.yourregimen.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tb_admin")
public class Admin {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

	@NotNull
	private String nomeUsuario;
	
	@Schema(example = "email@email.com.br")
	@NotNull(message = "O campo Usuário é obrigatório!")
	@Email(message = "O campo Usuário deve ser um e-mail válido!")
	private String loginUsuario;
	
	@NotNull
	@Size(min = 8,max = 64)
	private String senhaUsuario;
    
	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("admin")
	private List<Regimen>regimen;
	
	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("admin")
	private List<Financeiro>financeiro;

	public Admin(UUID id, @NotNull @NotBlank String nomeUsuario,
			@NotNull(message = "O campo Usuário é obrigatório!") @NotBlank @Email(message = "O campo Usuário deve ser um e-mail válido!") String loginUsuario,
			@NotNull @Size(min = 8, max = 64) String senhaUsuario, List<Regimen> regimen, List<Financeiro> financeiro) {
		this.id = id;
		this.nomeUsuario = nomeUsuario;
		this.loginUsuario = loginUsuario;
		this.senhaUsuario = senhaUsuario;
		this.regimen = regimen;
		this.financeiro = financeiro;
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
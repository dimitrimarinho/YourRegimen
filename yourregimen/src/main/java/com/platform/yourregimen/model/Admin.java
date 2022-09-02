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

	@NotNull(message = "O atributo Nome é Obrigatório!")
	private String nome;

	@Schema(example = "email@email.com.br")
	@NotNull(message = "O atributo Usuário é Obrigatório!")
	@Email(message = "O atributo Usuário deve ser um email válido!")
	private String usuario;

	@NotNull(message = "O atributo Senha é Obrigatório!")
	@Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres")
	private String senha;
    
	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("admin")
	private List<Regimen>regimen;
	
	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("admin")
	private List<Financeiro>financeiro;

	public Admin(UUID id, @NotNull(message = "O atributo Nome é Obrigatório!") String nome,
			@NotNull(message = "O atributo Usuário é Obrigatório!") @Email(message = "O atributo Usuário deve ser um email válido!") String usuario,
			@NotBlank(message = "O atributo Senha é Obrigatório!") @Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres") String senha, List<Regimen> regimen, List<Financeiro> financeiro) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.regimen = regimen;
		this.financeiro = financeiro;
	}

	public Admin() {
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
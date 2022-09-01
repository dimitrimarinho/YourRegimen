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
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_admin")
public class Admin {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID idAdmin;

    @NotBlank
    private String nomeAdmin;

    @Email
    @NotBlank
    private String loginAdmin;

    @NotBlank
    private String senhaAdmin;
    
	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("admin")
	private List<Regimen>regimen;
	
	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("admin")
	private List<Financeiro>financeiro;

	public Admin(UUID idAdmin, @NotBlank String nomeAdmin, @Email @NotBlank String loginAdmin,
			@NotBlank String senhaAdmin, List<Regimen> regimen, List<Financeiro> financeiro) {
		this.idAdmin = idAdmin;
		this.nomeAdmin = nomeAdmin;
		this.loginAdmin = loginAdmin;
		this.senhaAdmin = senhaAdmin;
		this.regimen = regimen;
		this.financeiro = financeiro;
	}

	public UUID getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(UUID idAdmin) {
		this.idAdmin = idAdmin;
	}

	public String getNomeAdmin() {
		return nomeAdmin;
	}

	public void setNomeAdmin(String nomeAdmin) {
		this.nomeAdmin = nomeAdmin;
	}

	public String getLoginAdmin() {
		return loginAdmin;
	}

	public void setLoginAdmin(String loginAdmin) {
		this.loginAdmin = loginAdmin;
	}

	public String getSenhaAdmin() {
		return senhaAdmin;
	}

	public void setSenhaAdmin(String senhaAdmin) {
		this.senhaAdmin = senhaAdmin;
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
package com.platform.yourregimen.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "financeiro")
public class Financeiro {

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
	private UUID idFinanceiro;
	
	@NotNull
	boolean pagamentoEfetuado;
	
	@ManyToOne
	@JsonIgnoreProperties("financeiro")
	private Admin admin;

	public UUID getIdFinanceiro() {
		return idFinanceiro;
	}

	public void setIdFinanceiro(UUID idFinanceiro) {
		this.idFinanceiro = idFinanceiro;
	}

	public boolean isPagamentoEfetuado() {
		return pagamentoEfetuado;
	}

	public void setPagamentoEfetuado(boolean pagamentoEfetuado) {
		this.pagamentoEfetuado = pagamentoEfetuado;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
}
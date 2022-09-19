package com.platform.yourregimen.finance.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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
	
}
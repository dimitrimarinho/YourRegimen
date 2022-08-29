package com.platform.yourregimen.financeiro;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "financeiro")
public class FinanceiroModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idFinanceiro;
	
	@NotNull
	boolean pagamentoEfetuado;

	public FinanceiroModel(Long idFinanceiro, @NotNull boolean pagamentoEfetuado) {
		this.idFinanceiro = idFinanceiro;
		this.pagamentoEfetuado = pagamentoEfetuado;
	}

	public Long getIdFinanceiro() {
		return idFinanceiro;
	}

	public void setIdFinanceiro(Long idFinanceiro) {
		this.idFinanceiro = idFinanceiro;
	}

	public boolean isPagamentoEfetuado() {
		return pagamentoEfetuado;
	}

	public void setPagamentoEfetuado(boolean pagamentoEfetuado) {
		this.pagamentoEfetuado = pagamentoEfetuado;
	}
	
}

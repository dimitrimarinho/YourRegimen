package com.br.microserviceregimen.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categoria")
public class CategoriaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCategoria;

	@NotNull
	private String objetivoDieta;
	
	@NotNull
	private String restricaoSaude;
	
	@NotNull
	private boolean restricaoFinanceira;

	public CategoriaModel(long idCategoria, @NotNull String objetivoDieta, @NotNull String restricaoSaude,
			@NotNull boolean restricaoFinanceira) {
		this.idCategoria = idCategoria;
		this.objetivoDieta = objetivoDieta;
		this.restricaoSaude = restricaoSaude;
		this.restricaoFinanceira = restricaoFinanceira;
	}

	public long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getObjetivoDieta() {
		return objetivoDieta;
	}

	public void setObjetivoDieta(String objetivoDieta) {
		this.objetivoDieta = objetivoDieta;
	}

	public String getRestricaoSaude() {
		return restricaoSaude;
	}

	public void setRestricaoSaude(String restricaoSaude) {
		this.restricaoSaude = restricaoSaude;
	}

	public boolean isRestricaoFinanceira() {
		return restricaoFinanceira;
	}

	public void setRestricaoFinanceira(boolean restricaoFinanceira) {
		this.restricaoFinanceira = restricaoFinanceira;
	}
	
	
}

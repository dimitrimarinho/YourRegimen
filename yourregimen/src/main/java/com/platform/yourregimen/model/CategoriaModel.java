package com.platform.yourregimen.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "categoria")
public class CategoriaModel {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID idCategoria;

	@NotNull
	private String objetivoDieta;
	
	@NotNull
	private String restricaoSaude;
	
	@NotNull
	private boolean restricaoFinanceira;

	public CategoriaModel(UUID idCategoria, @NotNull String objetivoDieta, @NotNull String restricaoSaude,
			@NotNull boolean restricaoFinanceira) {
		this.idCategoria = idCategoria;
		this.objetivoDieta = objetivoDieta;
		this.restricaoSaude = restricaoSaude;
		this.restricaoFinanceira = restricaoFinanceira;
	}

	public UUID getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(UUID idCategoria) {
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
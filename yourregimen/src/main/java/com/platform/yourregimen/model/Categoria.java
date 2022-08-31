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
@Table(name = "categoria")
public class Categoria {
	
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

	//@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	//@JsonIgnoreProperties("categoria")
	//private List<Regimen> regimen;

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
	/*
	public List<Regimen> getRegimen() {
		return regimen;
	}

	public void setRegimen(List<Regimen> regimen) {
		this.regimen = regimen;
	}
	*/
}
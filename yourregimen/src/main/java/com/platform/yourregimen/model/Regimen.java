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
@Table(name = "regimen")
public class Regimen {
	
	@Id
	@GeneratedValue(generator="UUID")
    @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
	private UUID idRegimen;
	
	@NotNull
	private String regimenName;
	
	@NotNull
	private String foodList;
	/*
	@ManyToOne
	@JsonIgnoreProperties("regimen")
	private Categoria categoria;
	*/
	public UUID getIdRegimen() {
		return idRegimen;
	}

	public void setIdRegimen(UUID idRegimen) {
		this.idRegimen = idRegimen;
	}

	public String getRegimenName() {
		return regimenName;
	}

	public void setRegimenName(String regimenName) {
		this.regimenName = regimenName;
	}

	public String getFoodList() {
		return foodList;
	}

	public void setFoodList(String foodList) {
		this.foodList = foodList;
	}
	/*
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	*/	
}
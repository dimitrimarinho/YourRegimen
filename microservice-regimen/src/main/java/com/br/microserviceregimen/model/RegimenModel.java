package com.br.microserviceregimen.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "regimen")
public class RegimenModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String regimenName;
	
	@NotNull
	private String foodList;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		
}

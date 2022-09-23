package com.platform.yourregimen.diet.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "tb_info")
public class InfoApi {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID id;
	private String FoodSearch;

	private String infoApi;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getInfoApi() {
		return infoApi;
	}

	public void setInfoApi(String infoApi) {
		this.infoApi = infoApi;
	}

	public String getFoodSearch() {
		return FoodSearch;
	}

	public void setFoodSearch(String foodSearch) {
		FoodSearch = foodSearch;
	}

}
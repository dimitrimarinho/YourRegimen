package com.platform.yourregimen.pacient.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "tb_paciente")
public class Paciente{

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Id
	private UUID idPaciente;
    
    @NotNull
    private String nomePaciente;
    
    @NotNull
    private String loginPaciente;
    
    @NotNull
    private String senhaPaciente;
	
	@NotNull
	private double altura;

	@NotNull
	private double peso;

	@NotNull
	private double maxCalories;

	@NotNull
	private double minCalories;

	public UUID getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(UUID idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getMaxCalories() {
		return maxCalories;
	}

	public void setMaxCalories(double maxCalories) {
		this.maxCalories = maxCalories;
	}

	public double getMinCalories() {
		return minCalories;
	}

	public void setMinCalories(double minCalories) {
		this.minCalories = minCalories;
	}

	public String getLoginPaciente() {
		return loginPaciente;
	}

	public void setLoginPaciente(String loginPaciente) {
		this.loginPaciente = loginPaciente;
	}

	public String getSenhaPaciente() {
		return senhaPaciente;
	}

	public void setSenhaPaciente(String senhaPaciente) {
		this.senhaPaciente = senhaPaciente;
	}

	

}


package com.platform.yourregimen.paciente;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_paciente")
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPaciente;
	
	@NotNull
	private String nomePaciente;

	@Email
	@NotNull
	private String loginPaciente;

	@NotNull
	private String senhaPaciente;
	
	@NotNull
	private double altura;
	
	@NotNull
	private double peso;
	
	@NotNull
	private double maxNutrientes;
	
	@NotNull
	private double minNutrientes;

	public Paciente(long idPaciente, @NotNull String nomePaciente, @Email @NotNull String loginPaciente,
			@NotNull String senhaPaciente, @NotNull double altura, @NotNull double peso, @NotNull double maxNutrientes,
			@NotNull double minNutrientes) {
		this.idPaciente = idPaciente;
		this.nomePaciente = nomePaciente;
		this.loginPaciente = loginPaciente;
		this.senhaPaciente = senhaPaciente;
		this.altura = altura;
		this.peso = peso;
		this.maxNutrientes = maxNutrientes;
		this.minNutrientes = minNutrientes;
	}

	public long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(long idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
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

	public double getMaxNutrientes() {
		return maxNutrientes;
	}

	public void setMaxNutrientes(double maxNutrientes) {
		this.maxNutrientes = maxNutrientes;
	}

	public double getMinNutrientes() {
		return minNutrientes;
	}

	public void setMinNutrientes(double minNutrientes) {
		this.minNutrientes = minNutrientes;
	}
		

}


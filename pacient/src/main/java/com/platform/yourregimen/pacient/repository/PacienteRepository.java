package com.platform.yourregimen.pacient.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platform.yourregimen.pacient.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, UUID>{
		
	Optional<Paciente> findByLoginPaciente(String paciente);
	
	public List<Paciente> findAllByNomePacienteContainingIgnoreCase(String nomePaciente);

}
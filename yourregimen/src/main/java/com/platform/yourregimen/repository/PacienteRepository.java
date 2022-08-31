package com.platform.yourregimen.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.platform.yourregimen.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, UUID>{
	
	
	public List<Paciente> findAllByNomePacienteContainingIgnoreCase(String nomePaciente);

}
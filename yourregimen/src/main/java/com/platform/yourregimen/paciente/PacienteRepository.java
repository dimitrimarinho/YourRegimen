

package com.platform.yourregimen.paciente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
	
	
	public List<Paciente> findAllByNomePacienteContainingIgnoreCase(String nomePaciente);

}


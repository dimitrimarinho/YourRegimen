package com.platform.yourregimen.controller;

import javax.validation.Valid;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.platform.yourregimen.model.Paciente;
import com.platform.yourregimen.repository.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PacienteController {

	@Autowired
	private PacienteRepository repository;	
	
	@GetMapping("/{id}")
	public ResponseEntity<Paciente> getById(@PathVariable UUID id) {
		return repository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Paciente> AddPaciente(@Valid@RequestBody Paciente paciente){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(paciente));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Paciente> put(@Valid @RequestBody Paciente paciente){
		return ResponseEntity.ok(repository.save(paciente));
				
	}
	
	 @DeleteMapping("/{id}")
	 public void delete (@PathVariable UUID id) {
		 repository.deleteById(id);
	 }
	 
}
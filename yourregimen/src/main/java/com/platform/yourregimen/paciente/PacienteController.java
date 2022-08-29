package com.platform.yourregimen.paciente;

import java.util.List;

import javax.validation.Valid;

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


@RestController
@RequestMapping("/pacientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PacienteController {

	@Autowired
	private PacienteRepository repository;
	
	@GetMapping("/all")
	public ResponseEntity<List<Paciente>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/nomePaciente/{nomePaciente}")
	public ResponseEntity<List<Paciente>> getByName(@PathVariable String nomePaciente){
		return ResponseEntity.ok(repository.findAllByNomePacienteContainingIgnoreCase(nomePaciente));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Paciente> getById(@PathVariable Long id) {
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
	 public void delete (@PathVariable long id) {
		 repository.deleteById(id);
	 }
}

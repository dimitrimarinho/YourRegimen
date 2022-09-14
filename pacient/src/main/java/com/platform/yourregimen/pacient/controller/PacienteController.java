package com.platform.yourregimen.pacient.controller;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
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

import com.platform.yourregimen.pacient.model.Paciente;
import com.platform.yourregimen.pacient.model.PacienteLogin;
import com.platform.yourregimen.pacient.repository.PacienteRepository;
import com.platform.yourregimen.pacient.service.PacienteService;

@RestController
@RequestMapping("/pacientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PacienteController {

	@Autowired
	private PacienteRepository repository;	
	
	@Autowired
	private PacienteService service;
	
	@GetMapping("/all")
	public ResponseEntity<List<Paciente>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Paciente> getById(@PathVariable UUID id) {
		return repository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/logar")
	public ResponseEntity<PacienteLogin> autenticationUsuario(@RequestBody Optional<PacienteLogin> usuario) {		
		return service.autenticarUsuario(usuario)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Paciente> postUsuario(@Valid @RequestBody Paciente usuario) {		
		return service.cadastrarUsuario(usuario)
			.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());		
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Paciente> putUsuario(@Valid @RequestBody Paciente usuario){		
		return service.atualizarUsuario(usuario)
			.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable UUID id) {
		repository.deleteById(id);
	}
	
}
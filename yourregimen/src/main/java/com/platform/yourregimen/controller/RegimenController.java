package com.platform.yourregimen.controller;

import java.util.List;
import java.util.UUID;
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
import org.springframework.web.bind.annotation.RestController;
import com.platform.yourregimen.model.Regimen;
import com.platform.yourregimen.repository.RegimenRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegimenController {

	@Autowired
	private RegimenRepository repository;

	@GetMapping("/regimen")
	public ResponseEntity<List<Regimen>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/regimen/{id}")
	public ResponseEntity<Regimen> getById(@PathVariable UUID id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/regimenName/{regimenName}")
	public ResponseEntity<List<Regimen>> getByName(@PathVariable String regimenName){
		return ResponseEntity.ok(repository.findAllByRegimenNameContainingIgnoreCase(regimenName));
	}
	
	@PostMapping(value = "/regimen")
	public ResponseEntity <Regimen> post(@Valid @RequestBody Regimen regimen){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(regimen));
	}
	
	@PutMapping(value = "/regimen") 
	public ResponseEntity <Regimen> put (@Valid @RequestBody Regimen regimen){
		return ResponseEntity.ok(repository.save(regimen));
	}
	
	@DeleteMapping(value="/regimen/{id}")
	public void delete (@PathVariable UUID id) {
		repository.deleteById(id);
	}
	
}
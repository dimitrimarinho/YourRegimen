package com.br.microserviceregimen.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.microserviceregimen.model.RegimenModel;
import com.br.microserviceregimen.repository.RegimenRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/regimen")
public class RegimenController {

	@Autowired
	private RegimenRepository repository;

	@GetMapping
	public ResponseEntity<List<RegimenModel>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@PostMapping
	public ResponseEntity <RegimenModel> post(@Valid @RequestBody RegimenModel regimen){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(regimen));
	}
	
	 @PutMapping 
	 public ResponseEntity <RegimenModel> put (@Valid @RequestBody RegimenModel regimen){
		 return ResponseEntity.ok(repository.save(regimen));
	 }


}

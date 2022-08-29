package com.br.microserviceregimen.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.br.microserviceregimen.model.CategoriaModel;
import com.br.microserviceregimen.repository.CategoriaRepository;

@RestController("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping("/all")
	public ResponseEntity<List<CategoriaModel>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{idCategoria}")
	public ResponseEntity<CategoriaModel> getById(@PathVariable Long idCategoria){
		return repository.findById(idCategoria).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/{objetivoDieta}")
	public ResponseEntity<List<CategoriaModel>> getByObjetivoDieta (@PathVariable String objetivoDieta){
		return ResponseEntity.ok(repository.findAllByObjetivoDietaContainingIgnoreCase(objetivoDieta));
	}
	
	@GetMapping("/restricaofinanceira/{restricaoFinanceira}")
	public ResponseEntity<List<CategoriaModel>> getByRestricaoFinanceira (@PathVariable boolean restricaoFinanceira){
		return ResponseEntity.ok(repository.findAllByRestricaoFinanceira(restricaoFinanceira));
	}
	
	@GetMapping("/{restricaoSaude}")
	public ResponseEntity<List<CategoriaModel>> getByRestricaoSaude (@PathVariable String restricaoSaude){
		return ResponseEntity.ok(repository.findAllByRestricaoSaudeContainingIgnoreCase(restricaoSaude));
	}
	
	@PostMapping("/postarcategoria")
	public ResponseEntity <CategoriaModel> post (@Valid @RequestBody CategoriaModel categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
	}
	
	@PutMapping("/alterarcategoria")
	public ResponseEntity <CategoriaModel> put (@Valid @RequestBody CategoriaModel categoria){
		return ResponseEntity.ok(repository.save(categoria));
	}
	
	@DeleteMapping("/deletarcategoria/{id}")
	public void delete (@PathVariable Long id) {
		repository.deleteById(id);
	}

}

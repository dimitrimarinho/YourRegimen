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
import com.platform.yourregimen.model.Categoria;
import com.platform.yourregimen.repository.CategoriaRepository;

@RestController("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping("/all")
	public ResponseEntity<List<Categoria>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{idCategoria}")
	public ResponseEntity<Categoria> getById(@PathVariable UUID idCategoria){
		return repository.findById(idCategoria).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/{objetivoDieta}")
	public ResponseEntity<List<Categoria>> getByObjetivoDieta (@PathVariable String objetivoDieta){
		return ResponseEntity.ok(repository.findAllByObjetivoDietaContainingIgnoreCase(objetivoDieta));
	}
	
	@GetMapping("/restricaofinanceira/{restricaoFinanceira}")
	public ResponseEntity<List<Categoria>> getByRestricaoFinanceira (@PathVariable boolean restricaoFinanceira){
		return ResponseEntity.ok(repository.findAllByRestricaoFinanceira(restricaoFinanceira));
	}
	
	@GetMapping("/{restricaoSaude}")
	public ResponseEntity<List<Categoria>> getByRestricaoSaude (@PathVariable String restricaoSaude){
		return ResponseEntity.ok(repository.findAllByRestricaoSaudeContainingIgnoreCase(restricaoSaude));
	}
	
	@PostMapping("/postarcategoria")
	public ResponseEntity <Categoria> post (@Valid @RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
	}
	
	@PutMapping("/alterarcategoria")
	public ResponseEntity <Categoria> put (@Valid @RequestBody Categoria categoria){
		return ResponseEntity.ok(repository.save(categoria));
	}
	
	@DeleteMapping("/deletarcategoria/{idCategoria}")
	public void delete (@PathVariable UUID idCategoria) {
		repository.deleteById(idCategoria);
	}

}
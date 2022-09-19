package com.platform.yourregimen.finance.controller;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platform.yourregimen.finance.model.Financeiro;
import com.platform.yourregimen.finance.repository.FinanceiroRepository;

@RestController
@RequestMapping("/financeiro")
public class FinanceiroController {

	@Autowired
	private FinanceiroRepository repository;
	
	@GetMapping("/all")
	public ResponseEntity <List<Financeiro>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{idFinanceiro}")
	public ResponseEntity <Financeiro> getById (@PathVariable UUID idFinanceiro){
		return repository.findById(idFinanceiro)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/controlefinanceiro")
	public ResponseEntity<Financeiro> registroDePagamentos (@Valid @RequestBody Financeiro financeiro){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(financeiro));
	}
	
	@PutMapping("/alterarfinanceiro")
	public ResponseEntity <Financeiro> alterarRegistro (@Valid @RequestBody Financeiro financeiro){
		return ResponseEntity.ok(repository.save(financeiro));
	}
	
	@DeleteMapping("/deletarregistro/{idFinanceiro}")
	public void delete (@PathVariable UUID idFinanceiro) {
		repository.deleteById(idFinanceiro);
	}
	
}
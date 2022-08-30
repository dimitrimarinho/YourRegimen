package com.platform.yourregimen.admin.controller;

import java.util.List;
import java.util.Optional;

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

import com.platform.yourregimen.admin.model.Admin;
import com.platform.yourregimen.admin.model.AdminLogin;
import com.platform.yourregimen.admin.repository.AdminRepository;
import com.platform.yourregimen.admin.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {

	@Autowired
	private AdminRepository repository;
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping("/all")
	public ResponseEntity <List<Admin>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{idAdmin}")
	public ResponseEntity<Admin> getById (@PathVariable Long idAdmin){
		return repository.findById(idAdmin)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/logar")
	public ResponseEntity<AdminLogin> loginAdmin (@RequestBody Optional<AdminLogin> user){
		return service.autenticarAdmin(user)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Admin> cadastroAdmin (@Valid @RequestBody Admin admin){
		return service.cadastroAdmin(admin)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Admin> atualizarAdmin (@Valid @RequestBody Admin admin){
		return service.atualizarAdmin(admin)
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@DeleteMapping("/deletaradmin/{idAdmin}")
	public void delete (@PathVariable Long idAdmin) {
		repository.deleteById(idAdmin);
	}
}

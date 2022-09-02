package com.platform.yourregimen.controller;

import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.platform.yourregimen.model.Admin;
import com.platform.yourregimen.model.AdminLogin;
import com.platform.yourregimen.repository.AdminRepository;
import com.platform.yourregimen.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {

	@Autowired
	private UsuarioService service;

	@Autowired
	private AdminRepository adminRepository;

	@GetMapping("/all")
	public ResponseEntity<List<Admin>> buscaUsuarios(){
		return ResponseEntity.ok(adminRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Admin> buscaUsuarioById(@PathVariable UUID id){
		return adminRepository.findById(id).map(produto->ResponseEntity.ok(produto))
				.orElse(ResponseEntity.notFound().build());
	}


	@PostMapping("/logar")
	public ResponseEntity<AdminLogin> autenticationUsuario(@RequestBody Optional<AdminLogin> usuario) {		
		return service.logarUsuario(usuario)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Admin> cadastroAdmin (@Valid @RequestBody Admin usuario){
		return service.cadastrarUsuario(usuario)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Admin> putUsuario(@Valid @RequestBody Admin usuario){		
		return service.atualizarUsuario(usuario)
			.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable UUID id) {
		adminRepository.deleteById(id);
	}
	
}
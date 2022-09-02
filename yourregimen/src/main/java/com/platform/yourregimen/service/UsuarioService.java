package com.platform.yourregimen.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.platform.yourregimen.model.Admin;
import com.platform.yourregimen.model.AdminLogin;
import com.platform.yourregimen.repository.AdminRepository;

@Service
public class UsuarioService {

	@Autowired
	private AdminRepository repository;

	public Optional<Admin> cadastrarUsuario(Admin usuario) {

		if (repository.findByLoginUsuario(usuario.getLoginUsuario()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

		usuario.setSenhaUsuario(criptografarSenha(usuario.getSenhaUsuario()));

		return Optional.of(repository.save(usuario));
	}

	public Optional<Admin> atualizarUsuario(Admin usuario) {

		if (repository.findById(usuario.getId()).isPresent()) {
			Optional<Admin> buscaUsuario = repository.findByLoginUsuario(usuario.getLoginUsuario());

			if (buscaUsuario.isPresent()) {				
				if (buscaUsuario.get().getId() != usuario.getId())
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
			}
			
			usuario.setSenhaUsuario(criptografarSenha(usuario.getSenhaUsuario()));

			return Optional.of(repository.save(usuario));
		} 
			
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);		
	}	
	
	public Optional<AdminLogin> logarUsuario(Optional<AdminLogin> usuarioLogin) {
		
		Optional<Admin> usuario = repository.findByLoginUsuario(usuarioLogin.get().getLoginUsuario());

		if (usuario.isPresent()) {
			if (compararSenhas(usuarioLogin.get().getSenhaUsuario(), usuario.get().getSenhaUsuario())) {

				usuarioLogin.get().setId(usuario.get().getId());				
				usuarioLogin.get().setNomeUsuario(usuario.get().getNomeUsuario());
				usuarioLogin.get().setLoginUsuario(usuario.get().getLoginUsuario());
				usuarioLogin.get().setSenhaUsuario(usuario.get().getSenhaUsuario());
				usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getLoginUsuario(), usuarioLogin.get().getSenhaUsuario()));
				
				return usuarioLogin;

			}
		}		
		
		throw new ResponseStatusException(
				HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!", null);
	}
	
	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(senha);

		return senhaEncoder;
	}
	
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.matches(senhaDigitada, senhaBanco);		
	}
	
	private String gerarBasicToken(String email, String password) {
		String estrutura = email + ":" + password;
		byte[] estruturaBase64 = Base64.encodeBase64(estrutura.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(estruturaBase64);
	}

}
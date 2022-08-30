package com.platform.yourregimen.admin.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.platform.yourregimen.admin.model.Admin;
import com.platform.yourregimen.admin.model.AdminLogin;
import com.platform.yourregimen.admin.repository.AdminRepository;

@Service
public class UsuarioService {

	@Autowired
	private AdminRepository repository;

	public Optional<Admin> cadastroAdmin(Admin admin) {
		if (repository.findByLoginAdmin(admin.getLoginAdmin()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
		admin.setSenhaAdmin(criptografarSenha(admin.getSenhaAdmin()));
		return Optional.of(repository.save(admin));
	}

	public Optional<Admin> atualizarAdmin(Admin admin) {
		if (repository.findById(admin.getIdAdmin()).isPresent()) {
			Optional<Admin> buscaLoginAdmin = repository.findByLoginAdmin(admin.getLoginAdmin());
			if (buscaLoginAdmin.get().getIdAdmin() != admin.getIdAdmin()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O usuário já existe!", null);
			}
			admin.setSenhaAdmin(criptografarSenha(admin.getSenhaAdmin()));
			return Optional.of(repository.save(admin));
		}
		return Optional.empty();
	}
	
	public Optional<AdminLogin> autenticarAdmin(Optional<AdminLogin>user){
		Optional<Admin> admin = repository.findByLoginAdmin(user.get().getSenha());
		if (admin.isPresent()) {
			if(compararSenhas(user.get().getSenha(), admin.get().getSenhaAdmin())) {
				user.get().setToken(gerarBasicToken(admin.get().getLoginAdmin(), user.get().getSenha()));
				user.get().setId(admin.get().getIdAdmin());
				user.get().setNome(admin.get().getNomeAdmin());
				user.get().setUsuario(admin.get().getLoginAdmin());
				user.get().setSenha(admin.get().getSenhaAdmin());
				return user;
			}
		}
		return Optional.empty();
	}
	
	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}
	
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senhaDigitada, senhaBanco);
	}
	
    private String gerarBasicToken(String email, String password) {
    	String estrutura = email+": "+password;
    	byte[] estruturaBase64 = Base64.encodeBase64(estrutura.getBytes(Charset.forName("US-ASCII")));
    	return "Basic "+new String(estruturaBase64);
    }

}



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
	private AdminRepository usuarioRepository;

	public Optional<Admin> cadastrarUsuario(Admin usuario) {

		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

		usuario.setSenha(criptografarSenha(usuario.getSenha()));

		return Optional.of(usuarioRepository.save(usuario));

	}

	public Optional<Admin> atualizarUsuario(Admin usuario) {

		if (usuarioRepository.findById(usuario.getId()).isPresent()) {

			Optional<Admin> buscaLoginUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());

			if (buscaLoginUsuario.isPresent()) {				
				if (buscaLoginUsuario.get().getId() != usuario.getId())
					throw new ResponseStatusException(
							HttpStatus.BAD_REQUEST, "O Usuário já existe!", null);
			}

			usuario.setSenha(criptografarSenha(usuario.getSenha()));

			return Optional.of(usuarioRepository.save(usuario));
		} 

		return Optional.empty();
	}

	public Optional<AdminLogin> autenticarUsuario(Optional<AdminLogin> usuarioLogin) {

		Optional<Admin> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());

		if (usuario.isPresent()) {
			if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
				usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setUsuario(usuario.get().getUsuario());
				usuarioLogin.get().setSenha(usuario.get().getSenha());

				return usuarioLogin;

			}
		}	

		return Optional.empty();

	}

	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.encode(senha);

	}

	private boolean compararSenhas(String senhaDigitada, String senhaBD) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.matches(senhaDigitada, senhaBD);

	}

	private String gerarBasicToken(String email, String password) {

		String tokenBase = email + ":" + password;
		byte[] tokenBase64 = Base64.encodeBase64(tokenBase.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);

	}
	
}
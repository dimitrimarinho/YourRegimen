
package com.platform.yourregimen.pacient.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.platform.yourregimen.pacient.model.Paciente;
import com.platform.yourregimen.pacient.model.PacienteLogin;
import com.platform.yourregimen.pacient.repository.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository usuarioRepository;

	public Optional<Paciente> cadastrarUsuario(Paciente usuario) {

		if (usuarioRepository.findByLoginPaciente(usuario.getLoginPaciente()).isPresent())

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

		usuario.setSenhaPaciente(criptografarSenha(usuario.getSenhaPaciente()));

		return Optional.of(usuarioRepository.save(usuario));

	}

	public Optional<Paciente> atualizarUsuario(Paciente usuario) {

		if (usuarioRepository.findById(usuario.getIdPaciente()).isPresent()) {

			Optional<Paciente> buscaLoginUsuario = usuarioRepository.findByLoginPaciente(usuario.getLoginPaciente());

			if (buscaLoginUsuario.isPresent()) {				
				if (buscaLoginUsuario.get().getIdPaciente() != usuario.getIdPaciente())
					throw new ResponseStatusException(
							HttpStatus.BAD_REQUEST, "O Usuário já existe!", null);
			}

			usuario.setSenhaPaciente(criptografarSenha(usuario.getSenhaPaciente()));

			return Optional.of(usuarioRepository.save(usuario));
		} 

		return Optional.empty();
	}

	public Optional<PacienteLogin> autenticarUsuario(Optional<PacienteLogin> usuarioLogin) {

		Optional<Paciente> usuario = usuarioRepository.findByLoginPaciente(usuarioLogin.get().getUsuario());

		if (usuario.isPresent()) {
			if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenhaPaciente())) {
				usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setId(usuario.get().getIdPaciente());
				usuarioLogin.get().setNome(usuario.get().getNomePaciente());
				usuarioLogin.get().setUsuario(usuario.get().getLoginPaciente());
				usuarioLogin.get().setSenha(usuario.get().getSenhaPaciente());
				usuarioLogin.get().setAltura(usuario.get().getAltura());
				usuarioLogin.get().setPeso(usuario.get().getPeso());
				usuarioLogin.get().setMaxCalories(usuario.get().getMaxCalories());
				usuarioLogin.get().setMinCalories(usuario.get().getMinCalories());

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

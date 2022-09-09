
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
	private PacienteRepository repository;
	
	public Optional<Paciente> cadastroPaciente(Paciente paciente) {
		if (repository.findByLoginPaciente(paciente.getLoginPaciente()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
		paciente.setSenhaPaciente(criptografarSenha(paciente.getSenhaPaciente()));
		return Optional.of(repository.save(paciente));
	}

	public Optional<Paciente> atualizarPaciente(Paciente paciente) {
		if (repository.findById(paciente.getIdPaciente()).isPresent()) {
			Optional<Paciente> buscaLoginPaciente = repository.findByLoginPaciente(paciente.getLoginPaciente());
			if (buscaLoginPaciente.get().getIdPaciente() != paciente.getIdPaciente()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O usuário já existe!", null);
			}
			paciente.setSenhaPaciente(criptografarSenha(paciente.getSenhaPaciente()));
			return Optional.of(repository.save(paciente));
		}
		return Optional.empty();
	}
	
	public Optional<PacienteLogin> autenticarPaciente(Optional<PacienteLogin>user){
		Optional<Paciente> paciente = repository.findByLoginPaciente(user.get().getSenha());
		if (paciente.isPresent()) {
			if(compararSenhas(user.get().getSenha(), paciente.get().getSenhaPaciente())) {
				user.get().setToken(gerarBasicToken(paciente.get().getLoginPaciente(), user.get().getSenha()));
				user.get().setId(paciente.get().getIdPaciente());
				user.get().setNome(paciente.get().getNomePaciente());
				user.get().setUsuario(paciente.get().getLoginPaciente());
				user.get().setSenha(paciente.get().getSenhaPaciente());
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

	

		
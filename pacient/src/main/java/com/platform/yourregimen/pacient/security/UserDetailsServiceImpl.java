package com.platform.yourregimen.pacient.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.platform.yourregimen.pacient.model.Paciente;
import com.platform.yourregimen.pacient.repository.PacienteRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private PacienteRepository repository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<Paciente> usuario = repository.findByLoginPaciente(userName);
		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + " não encontrado."));

		return usuario.map(UserDetailsImpl::new).get();
	}
}

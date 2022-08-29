package com.platform.yourregimen.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
	
	/*
	public Optional<Admin> findByLoginUsuario(String loginUsuario);

	public List<Admin> findAllByNomeUsuarioContainingIgnoreCase(String nomeUsuario);
	*/
}

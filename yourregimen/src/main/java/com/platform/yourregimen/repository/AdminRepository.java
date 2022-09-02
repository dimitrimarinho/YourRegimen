package com.platform.yourregimen.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.platform.yourregimen.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID>{

	public Optional<Admin> findByUsuario(String usuario);
	
	public List<Admin> findAllByNomeContainingIgnoreCase(String nome);
	
}
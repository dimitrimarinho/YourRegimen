package com.platform.yourregimen.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platform.yourregimen.admin.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
	
	public List<Admin> findAllByNomeAdminContainingIgnoreCase(String nomeAdmin);
	
	public Optional<Admin> findByLoginAdmin(String loginAdmin);
}

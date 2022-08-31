package com.platform.yourregimen.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.platform.yourregimen.model.Regimen;

@Repository
public interface RegimenRepository extends JpaRepository<Regimen, UUID> {

	public List<Regimen> findAllByRegimenNameContainingIgnoreCase(String regimenName);
	
}
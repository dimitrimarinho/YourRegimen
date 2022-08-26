package com.br.microserviceregimen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.microserviceregimen.model.RegimenModel;

@Repository
public interface RegimenRepository extends JpaRepository<RegimenModel, Long> {

	public List<RegimenModel> findAllByRegimenNameContainingIgnoreCase(String regimenName);
	
}
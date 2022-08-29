package com.br.microserviceregimen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.microserviceregimen.model.CategoriaModel;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long>{
	
	public List<CategoriaModel> findAllByObjetivoDietaContainingIgnoreCase (String objetivoDieta);
	public List<CategoriaModel> findAllByRestricaoFinanceira (boolean restricaoFinanceira);
	public List<CategoriaModel> findAllByRestricaoSaudeContainingIgnoreCase (String restricaoSaude);

}

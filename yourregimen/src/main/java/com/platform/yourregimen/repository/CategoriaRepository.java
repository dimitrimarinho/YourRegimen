package com.platform.yourregimen.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.platform.yourregimen.model.CategoriaModel;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel, UUID>{
	
	public List<CategoriaModel> findAllByObjetivoDietaContainingIgnoreCase (String objetivoDieta);
	public List<CategoriaModel> findAllByRestricaoFinanceira (boolean restricaoFinanceira);
	public List<CategoriaModel> findAllByRestricaoSaudeContainingIgnoreCase (String restricaoSaude);

}
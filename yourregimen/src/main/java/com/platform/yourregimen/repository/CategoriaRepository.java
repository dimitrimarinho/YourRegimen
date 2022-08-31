package com.platform.yourregimen.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.platform.yourregimen.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, UUID>{
	
	public List<Categoria> findAllByObjetivoDietaContainingIgnoreCase (String objetivoDieta);
	public List<Categoria> findAllByRestricaoFinanceira (boolean restricaoFinanceira);
	public List<Categoria> findAllByRestricaoSaudeContainingIgnoreCase (String restricaoSaude);

}
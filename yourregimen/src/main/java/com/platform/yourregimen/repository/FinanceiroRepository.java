package com.platform.yourregimen.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.platform.yourregimen.model.FinanceiroModel;

@Repository
public interface FinanceiroRepository extends JpaRepository<FinanceiroModel, UUID>{

	public List<FinanceiroModel> findAllByPagamentoEfetuado (boolean pagamentoEfetuado);
	
}
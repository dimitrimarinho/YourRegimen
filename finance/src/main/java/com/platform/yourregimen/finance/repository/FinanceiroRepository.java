package com.platform.yourregimen.finance.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platform.yourregimen.finance.model.Financeiro;

@Repository
public interface FinanceiroRepository extends JpaRepository<Financeiro, UUID>{

	public List<Financeiro> findAllByPagamentoEfetuado (boolean pagamentoEfetuado);
	
}
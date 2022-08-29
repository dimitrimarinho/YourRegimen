package com.platform.yourregimen.financeiro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceiroRepository extends JpaRepository<FinanceiroModel, Long>{

	public List<FinanceiroModel> findAllByPagamentoEfetuado (boolean pagamentoEfetuado);
}

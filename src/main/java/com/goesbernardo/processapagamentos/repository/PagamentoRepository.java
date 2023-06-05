package com.goesbernardo.processapagamentos.repository;

import com.goesbernardo.processapagamentos.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento,Long> {
}

package com.goesbernardo.recebepagamentos.repository;

import com.goesbernardo.recebepagamentos.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessaPagamentoRepository extends JpaRepository<Pagamento,Long> {
}

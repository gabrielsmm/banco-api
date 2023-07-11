package br.com.banco.repositories;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    @Query(value = "SELECT t FROM Transferencia t WHERE " +
            "(:dataInicial IS NULL OR t.dataTransferencia >= :dataInicial) AND " +
            "(:dataFinal IS NULL OR t.dataTransferencia <= :dataFinal) AND " +
            "(:nomeOperador IS NULL OR t.nomeOperadorTransacao = :nomeOperador) AND " +
            "t.conta = :conta",
            countQuery = "SELECT count(t) FROM Transferencia t WHERE " +
                    "(:dataInicial IS NULL OR t.dataTransferencia >= :dataInicial) AND " +
                    "(:dataFinal IS NULL OR t.dataTransferencia <= :dataFinal) AND " +
                    "(:nomeOperador IS NULL OR t.nomeOperadorTransacao = :nomeOperador) AND " +
                    "t.conta = :conta")
    Page<Transferencia> findByFilter(Conta conta, Date dataInicial, Date dataFinal, String nomeOperador, Pageable pageable);

}

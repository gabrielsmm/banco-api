package br.com.banco.repositories;

import br.com.banco.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query("SELECT SUM(t.valor) FROM Transferencia t WHERE t.conta.id = :idConta")
    Double obterSaldoDaConta(Long idConta);

}

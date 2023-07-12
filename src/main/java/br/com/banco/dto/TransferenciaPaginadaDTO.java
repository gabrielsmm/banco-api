package br.com.banco.dto;

import br.com.banco.entities.Transferencia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransferenciaPaginadaDTO {

    Page<Transferencia> transferencias;
    Double saldoTotal;
    Double saldoPeriodo;

}

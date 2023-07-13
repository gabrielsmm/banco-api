package br.com.banco.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransferenciaPaginadaDTO {

    Page<TransferenciaDTO> transferencias;
    Double saldoTotal;
    Double saldoPeriodo;

}

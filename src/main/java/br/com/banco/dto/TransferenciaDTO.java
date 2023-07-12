package br.com.banco.dto;

import br.com.banco.entities.Transferencia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransferenciaDTO {

    private Long id;
    private Date dataTransferencia;
    private Double valor;
    private String tipo;
    private String nomeOperadorTransacao;

    public TransferenciaDTO(Transferencia obj) {
        this.id = obj.getId();
        this.dataTransferencia = obj.getDataTransferencia();
        this.valor = obj.getValor();
        this.tipo = obj.getTipo();
        this.nomeOperadorTransacao = obj.getNomeOperadorTransacao();
    }

}

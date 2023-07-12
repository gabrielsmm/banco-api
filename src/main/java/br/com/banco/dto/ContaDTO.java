package br.com.banco.dto;

import br.com.banco.entities.Conta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContaDTO {

    private Long id;
    private String nomeResponsavel;

    public ContaDTO(Conta obj) {
        this.id = obj.getId();
        this.nomeResponsavel = obj.getNomeResponsavel();
    }

}

package br.com.banco.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContaDTO {

    private Long id;
    private String nomeResponsavel;
    private Double saldo;

}

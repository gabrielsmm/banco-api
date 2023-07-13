package br.com.banco.controllers;

import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.dto.TransferenciaPaginadaDTO;
import br.com.banco.entities.Transferencia;
import br.com.banco.services.TransferenciaService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/transferencias")
@AllArgsConstructor
public class TransferenciaController {

    private TransferenciaService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Transferencia> find(@PathVariable Long id) {
        Transferencia transferencia = service.find(id);
        return ResponseEntity.ok().body(transferencia);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Transferencia obj) {
        Transferencia objInserido = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(objInserido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Transferencia obj) {
        service.update(id, obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TransferenciaDTO>> findAll() {
        List<TransferenciaDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<TransferenciaPaginadaDTO> findPage(
            @RequestParam(value = "idConta") Long idConta,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "registrosPorPagina", defaultValue = "24") Integer registrosPorPagina,
            @RequestParam(value = "dataInicial", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicial,
            @RequestParam(value = "dataFinal", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFinal,
            @RequestParam(value = "nomeOperador", required = false) String nomeOperador
            ) {
        TransferenciaPaginadaDTO transferenciaPaginada = service.findPage(pagina, registrosPorPagina, idConta, dataInicial, dataFinal, nomeOperador);
        return ResponseEntity.ok().body(transferenciaPaginada);
    }

}

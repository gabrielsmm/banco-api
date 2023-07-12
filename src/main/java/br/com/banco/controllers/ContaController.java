package br.com.banco.controllers;

import br.com.banco.dto.ContaDTO;
import br.com.banco.entities.Conta;
import br.com.banco.services.ContaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/contas")
@AllArgsConstructor
public class ContaController {

    private ContaService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ContaDTO> find(@PathVariable Long id) {
        ContaDTO conta = service.findWithSaldo(id);
        return ResponseEntity.ok().body(conta);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Conta obj) {
        Conta objInserido = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(objInserido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Conta obj) {
        service.update(id, obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ContaDTO>> findAll() {
        List<ContaDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

}

package br.com.banco.services;

import br.com.banco.dto.ContaDTO;
import br.com.banco.entities.Conta;
import br.com.banco.repositories.ContaRepository;
import br.com.banco.services.exceptions.DataIntegrityException;
import br.com.banco.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContaService {

    private ContaRepository repository;

    public Conta find(Long id) {
        Optional<Conta> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " +
                "Id: " + id + ", Tipo: " + Conta.class.getName()));
    }

    public Conta insert(Conta obj) {
        try {
            return repository.save(obj);
        } catch(DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível inserir, erro de integridade de dados");
        }
    }

    public Conta update(Long id, Conta obj) {
        Conta objExistente = find(id);
        this.updateData(objExistente, obj);
        return repository.save(objExistente);
    }

    public void delete(Long id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível excluir, erro de integridade de dados");
        }
    }

    public List<ContaDTO> findAll() {
        return repository.findAll().stream().map(ContaDTO::new).collect(Collectors.toList());
    }

    private void updateData(Conta objExistente, Conta obj) {
        objExistente.setNomeResponsavel(obj.getNomeResponsavel());
    }

}

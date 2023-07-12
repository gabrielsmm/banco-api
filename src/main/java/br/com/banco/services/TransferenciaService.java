package br.com.banco.services;

import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.ContaRepository;
import br.com.banco.repositories.TransferenciaRepository;
import br.com.banco.services.exceptions.DataIntegrityException;
import br.com.banco.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransferenciaService {

    private TransferenciaRepository repository;

    private ContaRepository contaRepository;

    public Transferencia find(Long id) {
        Optional<Transferencia> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " +
                "Id: " + id + ", Tipo: " + Transferencia.class.getName()));
    }

    public Transferencia insert(Transferencia obj) {
        try {
            return repository.save(obj);
        } catch(DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível inserir, erro de integridade de dados");
        }
    }

    public Transferencia update(Long id, Transferencia obj) {
        Transferencia objExistente = find(id);
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

    public List<TransferenciaDTO> findAll() {
        return repository.findAll().stream().map(TransferenciaDTO::new).collect(Collectors.toList());
    }

    public Page<TransferenciaDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction,
                                           Long idConta, Date dataInicial, Date dataFinal, String nomeOperador) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Conta conta = contaRepository.findById(idConta).orElse(null);
        return repository.findByFilter(conta, dataInicial, dataFinal, nomeOperador, pageRequest).map(TransferenciaDTO::new);
    }

    private void updateData(Transferencia objExistente, Transferencia obj) {
        objExistente.setDataTransferencia(obj.getDataTransferencia());
        objExistente.setValor(obj.getValor());
        objExistente.setTipo(obj.getTipo());
        objExistente.setNomeOperadorTransacao(obj.getNomeOperadorTransacao());
    }


}

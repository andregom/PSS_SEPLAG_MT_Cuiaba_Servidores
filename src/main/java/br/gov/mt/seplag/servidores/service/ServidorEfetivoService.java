package br.gov.mt.seplag.servidores.service;

import br.gov.mt.seplag.servidores.entity.ServidorEfetivo;
import br.gov.mt.seplag.servidores.repository.ServidorEfetivoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServidorEfetivoService {

    private final ServidorEfetivoRepository repository;

    public Page<ServidorEfetivo> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public ServidorEfetivo salvar(ServidorEfetivo servidor) {
        return repository.save(servidor);
    }

    public ServidorEfetivo buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Servidor efetivo não encontrado com ID: " + id));
    }

    public ServidorEfetivo atualizar(Long id, ServidorEfetivo atualizado) {
        ServidorEfetivo existente = buscarPorId(id);

        existente.setNome(atualizado.getNome());
        existente.setDataNascimento(atualizado.getDataNascimento());
        existente.setFoto(atualizado.getFoto());
        existente.setLotacao(atualizado.getLotacao());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Servidor efetivo não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

    public Page<ServidorEfetivo> buscarPorUnidade(Long unidadeId, Pageable pageable) {
        return repository.findByLotacao_Unidade_Id(unidadeId, pageable);
    }

    public Page<ServidorEfetivo> buscarPorNome(String nome, Pageable pageable) {
        return repository.findByNomeContainingIgnoreCase(nome, pageable);
    }
}

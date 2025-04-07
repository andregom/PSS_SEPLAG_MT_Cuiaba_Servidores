package br.gov.mt.seplag.servidores.service;

import  br.gov.mt.seplag.servidores.entity.ServidorEfetivo;
import  br.gov.mt.seplag.servidores.repository.ServidorEfetivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<ServidorEfetivo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Page<ServidorEfetivo> buscarPorUnidade(Long unidadeId, Pageable pageable) {
        return repository.findByLotacao_Unidade_Id(unidadeId, pageable);
    }

    public Page<ServidorEfetivo> buscarPorNome(String nome, Pageable pageable) {
        return repository.findByNomeContainingIgnoreCase(nome, pageable);
    }
}


package br.gov.mt.seplag.servidores.service;

import  br.gov.mt.seplag.servidores.entity.Unidade;
import  br.gov.mt.seplag.servidores.repository.UnidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnidadeService {

    private final UnidadeRepository repository;

    public Page<Unidade> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Unidade salvar(Unidade unidade) {
        return repository.save(unidade);
    }

    public Optional<Unidade> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}


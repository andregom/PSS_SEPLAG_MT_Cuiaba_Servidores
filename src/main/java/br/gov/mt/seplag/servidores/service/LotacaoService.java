package br.gov.mt.seplag.servidores.service;

import  br.gov.mt.seplag.servidores.entity.Lotacao;
import  br.gov.mt.seplag.servidores.repository.LotacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LotacaoService {

    private final LotacaoRepository repository;

    public Page<Lotacao> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Lotacao salvar(Lotacao lotacao) {
        return repository.save(lotacao);
    }

    public Optional<Lotacao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}

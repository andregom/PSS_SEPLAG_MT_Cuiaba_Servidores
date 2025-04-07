package br.gov.mt.seplag.servidores.service;

import  br.gov.mt.seplag.servidores.entity.ServidorTemporario;
import  br.gov.mt.seplag.servidores.repository.ServidorTemporarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServidorTemporarioService {

    private final ServidorTemporarioRepository repository;

    public Page<ServidorTemporario> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public ServidorTemporario salvar(ServidorTemporario servidor) {
        return repository.save(servidor);
    }

    public Optional<ServidorTemporario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}


package br.gov.mt.seplag.servidores.service;

import br.gov.mt.seplag.servidores.entity.ServidorTemporario;
import br.gov.mt.seplag.servidores.repository.ServidorTemporarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public ServidorTemporario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Servidor temporário não encontrado com ID: " + id));
    }

    public ServidorTemporario atualizar(Long id, ServidorTemporario atualizado) {
        ServidorTemporario existente = buscarPorId(id);

        existente.setNome(atualizado.getNome());
        existente.setNascimento(atualizado.getNascimento());
        existente.setUnidade(atualizado.getUnidade());
        existente.setFoto(atualizado.getFoto());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Servidor temporário não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }
}

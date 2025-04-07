package br.gov.mt.seplag.servidores.controller;

import  br.gov.mt.seplag.servidores.controller.generic.AbstractCrudController;
import  br.gov.mt.seplag.servidores.entity.ServidorEfetivo;
import  br.gov.mt.seplag.servidores.service.ServidorEfetivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/servidores-efetivos")
@RequiredArgsConstructor
public class ServidorEfetivoController extends AbstractCrudController<ServidorEfetivo, Long> {

    private final ServidorEfetivoService service;

    @Override
    protected Page<ServidorEfetivo> findAll(Pageable pageable) {
        return service.listarTodos(pageable);
    }

    @Override
    protected Optional<ServidorEfetivo> findById(Long id) {
        return service.buscarPorId(id);
    }

    @Override
    protected ServidorEfetivo save(ServidorEfetivo entity) {
        return service.salvar(entity);
    }

    @Override
    protected void deleteById(Long id) {
        service.deletar(id);
    }

    // Extra 1: Listar por unidade (GET com filtro unid_id)
    @GetMapping("/por-unidade/{unidadeId}")
    public Page<ServidorEfetivo> buscarPorUnidade(@PathVariable Long unidadeId, Pageable pageable) {
        return service.buscarPorUnidade(unidadeId, pageable);
    }

    // Extra 2: Buscar endereço da unidade via nome
    @GetMapping("/endereco-por-nome")
    public Page<String> buscarEnderecoPorNome(@RequestParam String nome, Pageable pageable) {
        return service.buscarPorNome(nome, pageable)
                .map(servidor -> servidor.getLotacao().getUnidade().getEndereco());
    }
}

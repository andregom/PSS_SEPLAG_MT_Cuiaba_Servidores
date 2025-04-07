package br.gov.mt.seplag.servidores.controller;

import  br.gov.mt.seplag.servidores.controller.generic.AbstractCrudController;
import  br.gov.mt.seplag.servidores.entity.Lotacao;
import  br.gov.mt.seplag.servidores.service.LotacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/lotacoes")
@RequiredArgsConstructor
public class LotacaoController extends AbstractCrudController<Lotacao, Long> {

    private final LotacaoService service;

    @Override
    protected Page<Lotacao> findAll(Pageable pageable) {
        return service.listarTodos(pageable);
    }

    @Override
    protected Optional<Lotacao> findById(Long id) {
        return service.buscarPorId(id);
    }

    @Override
    protected Lotacao save(Lotacao entity) {
        return service.salvar(entity);
    }

    @Override
    protected void deleteById(Long id) {
        service.deletar(id);
    }
}

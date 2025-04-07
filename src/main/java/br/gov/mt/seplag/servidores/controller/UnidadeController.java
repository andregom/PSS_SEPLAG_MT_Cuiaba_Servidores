package br.gov.mt.seplag.servidores.controller;

import br.gov.mt.seplag.servidores.controller.generic.AbstractCrudController;
import br.gov.mt.seplag.servidores.entity.Unidade;
import br.gov.mt.seplag.servidores.service.UnidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/unidades")
@RequiredArgsConstructor
public class UnidadeController extends AbstractCrudController<Unidade, Long> {

    private final UnidadeService service;

    @Override
    protected Page<Unidade> findAll(Pageable pageable) {
        return service.listarTodos(pageable);
    }

    @Override
    protected Optional<Unidade> findById(Long id) {
        return service.buscarPorId(id);
    }

    @Override
    protected Unidade save(Unidade entity) {
        return service.salvar(entity);
    }

    @Override
    protected void deleteById(Long id) {
        service.deletar(id);
    }
}

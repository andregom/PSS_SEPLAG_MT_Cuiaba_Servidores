package br.gov.mt.seplag.servidores.controller;

import  br.gov.mt.seplag.servidores.controller.generic.AbstractCrudController;
import  br.gov.mt.seplag.servidores.entity.ServidorTemporario;
import  br.gov.mt.seplag.servidores.service.ServidorTemporarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/servidores-temporarios")
@RequiredArgsConstructor
public class ServidorTemporarioController extends AbstractCrudController<ServidorTemporario, Long> {

    private final ServidorTemporarioService service;

    @Override
    protected Page<ServidorTemporario> findAll(Pageable pageable) {
        return service.listarTodos(pageable);
    }

    @Override
    protected Optional<ServidorTemporario> findById(Long id) {
        return Optional.ofNullable(service.buscarPorId(id));
    }

    @Override
    protected ServidorTemporario save(ServidorTemporario entity) {
        return service.salvar(entity);
    }

    @Override
    protected void deleteById(Long id) {
        service.deletar(id);
    }
}

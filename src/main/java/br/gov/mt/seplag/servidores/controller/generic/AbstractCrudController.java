package br.gov.mt.seplag.servidores.controller.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public abstract class AbstractCrudController<T, ID> {

    protected abstract Page<T> findAll(Pageable pageable);
    protected abstract Optional<T> findById(ID id);
    protected abstract T save(T entity);
    protected abstract void deleteById(ID id);

    @GetMapping
    public ResponseEntity<Page<T>> listar(Pageable pageable) {
        return ResponseEntity.ok(findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> buscarPorId(@PathVariable ID id) {
        return findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<T> criar(@RequestBody T entity) {
        return ResponseEntity.ok(save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> atualizar(@PathVariable ID id, @RequestBody T entity) {
        return findById(id)
                .map(original -> ResponseEntity.ok(save(entity)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable ID id) {
        deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

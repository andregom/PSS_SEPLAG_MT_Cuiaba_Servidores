package br.gov.mt.seplag.servidores.repository;

import br.gov.mt.seplag.servidores.entity.ServidorEfetivo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, Long> {

    Page<ServidorEfetivo> findByLotacao_Unidade_Id(Long unidadeId, Pageable pageable);

    Page<ServidorEfetivo> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}

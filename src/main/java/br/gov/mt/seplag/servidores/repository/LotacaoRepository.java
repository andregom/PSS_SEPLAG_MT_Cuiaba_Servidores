package br.gov.mt.seplag.servidores.repository;

import br.gov.mt.seplag.servidores.entity.Lotacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotacaoRepository extends JpaRepository<Lotacao, Long> {
}

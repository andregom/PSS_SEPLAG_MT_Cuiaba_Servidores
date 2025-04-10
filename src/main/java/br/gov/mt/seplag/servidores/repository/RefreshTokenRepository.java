package br.gov.mt.seplag.servidores.repository;

import br.gov.mt.seplag.servidores.model.RefreshToken;
import br.gov.mt.seplag.servidores.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(User user);
}

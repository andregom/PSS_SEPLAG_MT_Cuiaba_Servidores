package br.gov.mt.seplag.servidores.config;

import br.gov.mt.seplag.servidores.entity.*;
import br.gov.mt.seplag.servidores.repository.*;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UnidadeRepository unidadeRepository;
    private final ServidorEfetivoRepository servidorEfetivoRepository;
    private final ServidorTemporarioRepository servidorTemporarioRepository;
    private final LotacaoRepository lotacaoRepository;

    public DatabaseSeeder(
            UnidadeRepository unidadeRepository,
            ServidorEfetivoRepository servidorEfetivoRepository,
            ServidorTemporarioRepository servidorTemporarioRepository,
            LotacaoRepository lotacaoRepository
    ) {
        this.unidadeRepository = unidadeRepository;
        this.servidorEfetivoRepository = servidorEfetivoRepository;
        this.servidorTemporarioRepository = servidorTemporarioRepository;
        this.lotacaoRepository = lotacaoRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (unidadeRepository.count() > 0) return; // Só executa se estiver vazio

        Faker faker = new Faker();
        Random random = new Random();

        // Criar Unidades
        Unidade unidade1 = unidadeRepository.save(new Unidade(null, "Secretaria de Educação", "Rua das Flores, 123"));
        Unidade unidade2 = unidadeRepository.save(new Unidade(null, "Secretaria de Saúde", "Av. Brasil, 456"));
        List<Unidade> unidades = List.of(unidade1, unidade2);

        // Criar 10 servidores efetivos e lotar
        for (int i = 0; i < 10; i++) {
            String nome = faker.name().fullName();
            LocalDate nascimento = LocalDate.of(
                    1960 + random.nextInt(30),
                    1 + random.nextInt(12),
                    1 + random.nextInt(28)
            );
            String fotoUrl = "https://fake-minio-bucket.com/images/" + faker.internet().uuid() + ".jpg";

            ServidorEfetivo efetivo = new ServidorEfetivo();
            efetivo.setNome(nome);
            efetivo.setDataNascimento(nascimento);
            efetivo.setFoto(fotoUrl);
            efetivo = servidorEfetivoRepository.save(efetivo);

            Unidade unidade = unidades.get(random.nextInt(unidades.size()));
            Lotacao lotacao = new Lotacao();
            lotacao.setServidorEfetivo(efetivo);
            lotacao.setUnidade(unidade);
            lotacaoRepository.save(lotacao);

            efetivo.setLotacao(lotacao);
            servidorEfetivoRepository.save(efetivo);
        }

        // Criar 10 servidores temporários
        for (int i = 0; i < 10; i++) {
            String nome = faker.name().fullName();
            LocalDate nascimento = LocalDate.of(
                    1960 + random.nextInt(30),
                    1 + random.nextInt(12),
                    1 + random.nextInt(28)
            );
            String fotoUrl = "https://fake-minio-bucket.com/images/" + faker.internet().uuid() + ".jpg";

            Unidade unidade = unidades.get(random.nextInt(unidades.size()));

            ServidorTemporario temporario = new ServidorTemporario();
            temporario.setNome(nome);
            temporario.setNascimento(nascimento);
            temporario.setUnidade(unidade);
            temporario.setFoto(fotoUrl);

            servidorTemporarioRepository.save(temporario);
        }

        System.out.println("🌱 Base de dados populada com sucesso!");
    }
}

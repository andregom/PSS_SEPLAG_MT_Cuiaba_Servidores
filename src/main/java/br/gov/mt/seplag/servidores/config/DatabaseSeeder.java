package br.gov.mt.seplag.servidores.config;

import br.gov.mt.seplag.servidores.entity.ServidorEfetivo;
import br.gov.mt.seplag.servidores.entity.Unidade;
import br.gov.mt.seplag.servidores.entity.Lotacao;
import br.gov.mt.seplag.servidores.repository.ServidorEfetivoRepository;
import br.gov.mt.seplag.servidores.repository.UnidadeRepository;
import br.gov.mt.seplag.servidores.repository.LotacaoRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UnidadeRepository unidadeRepository;
    private final ServidorEfetivoRepository servidorRepository;
    private final LotacaoRepository lotacaoRepository;

    public DatabaseSeeder(UnidadeRepository unidadeRepository, ServidorEfetivoRepository servidorRepository, LotacaoRepository lotacaoRepository) {
        this.unidadeRepository = unidadeRepository;
        this.servidorRepository = servidorRepository;
        this.lotacaoRepository = lotacaoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (unidadeRepository.count() > 0) return; // só popula se estiver vazio

        Faker faker = new Faker();
        Random random = new Random();

        // Criar Unidades
        Unidade unidade1 = unidadeRepository.save(new Unidade(null, "Secretaria de Educação", "Rua das Flores, 123"));
        Unidade unidade2 = unidadeRepository.save(new Unidade(null, "Secretaria de Saúde", "Av. Brasil, 456"));

        List<Unidade> unidades = List.of(unidade1, unidade2);

        // Criar 20 Servidores Efetivos e lotá-los
        for (int i = 0; i < 20; i++) {
            String nome = faker.name().fullName();
            LocalDate nascimento = LocalDate.of(
                    1960 + random.nextInt(30),  // ano entre 1960 e 1990
                    1 + random.nextInt(12),
                    1 + random.nextInt(28)
            );
            String fotoUrl = "https://fake-minio-bucket.com/images/" + faker.internet().uuid() + ".jpg";

            ServidorEfetivo servidor = servidorRepository.save(
                    new ServidorEfetivo(null, nome, nascimento, fotoUrl)
            );

            Unidade unidadeEscolhida = unidades.get(random.nextInt(unidades.size()));

            lotacaoRepository.save(new Lotacao(null, servidor, unidadeEscolhida));
        }

        System.out.println("🌱 Base de dados populada com 2 unidades e 20 servidores efetivos!");
    }
}

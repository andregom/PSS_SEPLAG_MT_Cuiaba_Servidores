package br.gov.mt.seplag.servidores.config;

import br.gov.mt.seplag.servidores.entity.*;
import br.gov.mt.seplag.servidores.repository.*;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final UnidadeRepository unidadeRepository;
    private final ServidorEfetivoRepository servidorEfetivoRepository;
    private final ServidorTemporarioRepository servidorTemporarioRepository;
    private final LotacaoRepository lotacaoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (unidadeRepository.count() > 0) return;

        Faker faker = new Faker();
        Random random = new Random();

        // Criar Unidades
        Unidade unidade1 = unidadeRepository.save(
                Unidade.builder().nome("Secretaria de Educação").endereco("Rua das Flores, 123").build()
        );
        Unidade unidade2 = unidadeRepository.save(
                Unidade.builder().nome("Secretaria de Saúde").endereco("Av. Brasil, 456").build()
        );

        List<Unidade> unidades = List.of(unidade1, unidade2);

        // Criar 20 Servidores Efetivos com Lotação
        for (int i = 0; i < 20; i++) {
            String nome = faker.name().fullName();
            LocalDate nascimento = LocalDate.of(
                    1960 + random.nextInt(30),
                    1 + random.nextInt(12),
                    1 + random.nextInt(28)
            );
            String fotoUrl = "https://fake-minio.com/" + faker.internet().uuid() + ".jpg";

            ServidorEfetivo efetivo = servidorEfetivoRepository.save(
                    ServidorEfetivo.builder()
                            .nome(nome)
                            .dataNascimento(nascimento)
                            .foto(fotoUrl)
                            .build()
            );

            Lotacao lotacao = Lotacao.builder()
                    .servidor(efetivo)
                    .unidade(unidades.get(random.nextInt(unidades.size())))
                    .build();

            lotacaoRepository.save(lotacao);

            efetivo.setLotacao(lotacao); // atualizar referência
            servidorEfetivoRepository.save(efetivo);
        }

        // Criar 10 Servidores Temporários
        for (int i = 0; i < 10; i++) {
            ServidorTemporario temp = new ServidorTemporario();
            temp.setNome(faker.name().fullName());
            temp.setNascimento(LocalDate.of(
                    1970 + random.nextInt(30),
                    1 + random.nextInt(12),
                    1 + random.nextInt(28)
            ));
            temp.setFoto("https://fake-minio.com/" + faker.internet().uuid() + ".jpg");
            temp.setUnidade(unidades.get(random.nextInt(unidades.size())));

            servidorTemporarioRepository.save(temp);
        }

        System.out.println("✅ Base populada com:");
        System.out.println(" → 2 unidades");
        System.out.println(" → 20 servidores efetivos com lotação");
        System.out.println(" → 10 servidores temporários");
    }
}

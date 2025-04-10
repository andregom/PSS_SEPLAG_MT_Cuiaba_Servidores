package br.gov.mt.seplag.servidores.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServidorEfetivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDate dataNascimento;

    private String foto; // nome do arquivo, path ou UUID salvo no MinIO

    @ManyToOne
    @JoinColumn(name = "lotacao_id")
    private Lotacao lotacao;

    // 👇 Construtor simplificado para uso no Seeder
    public ServidorEfetivo(Long id, String nome, LocalDate dataNascimento, String foto) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.foto = foto;
    }

    public int getIdade() {
        return LocalDate.now().getYear() - dataNascimento.getYear();
    }
}

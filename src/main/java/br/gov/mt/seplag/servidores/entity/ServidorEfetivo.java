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

    public int getIdade() {
        return LocalDate.now().getYear() - dataNascimento.getYear(); // simplificado
    }
}

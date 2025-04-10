package br.gov.mt.seplag.servidores.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServidorTemporario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDate nascimento;

    private String foto;

    @ManyToOne
    @JoinColumn(name = "unidade_id")
    private Unidade unidade;

    private LocalDate dataFimContrato;

    public ServidorTemporario(String nome, LocalDate nascimento, Unidade unidade) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.unidade = unidade;
    }
}
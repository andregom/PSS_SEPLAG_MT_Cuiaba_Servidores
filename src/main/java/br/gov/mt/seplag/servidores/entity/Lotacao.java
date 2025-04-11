package br.gov.mt.seplag.servidores.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "servidor_id")
    private ServidorEfetivo servidorEfetivo;

    @ManyToOne
    @JoinColumn(name = "unidade_id")
    private Unidade unidade;
}

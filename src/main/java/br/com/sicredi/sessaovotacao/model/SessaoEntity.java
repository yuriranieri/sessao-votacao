package br.com.sicredi.sessaovotacao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Sessao")
public class SessaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFinal;

    @OneToOne
    @JoinColumn(name = "id_pauta", referencedColumnName = "id")
    private PautaEntity pauta;

}

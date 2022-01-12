package br.com.sicredi.sessaovotacao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    private int tempoExpiracao;

    @OneToMany(mappedBy = "sessao")
    private List<AssociadoSessaoEntity> associadoSessaoEntities;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pauta", referencedColumnName = "id")
    private PautaEntity pauta;

}

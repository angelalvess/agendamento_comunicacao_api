package com.angel.agendamento_comunicacao_api.infrastructure.entities;


import com.angel.agendamento_comunicacao_api.infrastructure.enums.StatusAgendamentoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "agendamento")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Agendamento {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String emailDestinatario;
    private String telefoneDestinatario;
    private String mensagem;
    private LocalDateTime dataHoraAgendamento;
    private LocalDateTime dataHoraEnvio;
    private LocalDateTime dataHoraModificado;
    private StatusAgendamentoEnum statusAgendamentoEnum;

    @PrePersist
    private void prePersist () {
        dataHoraAgendamento = LocalDateTime.now();
        statusAgendamentoEnum = StatusAgendamentoEnum.AGENDADO;
    }
}

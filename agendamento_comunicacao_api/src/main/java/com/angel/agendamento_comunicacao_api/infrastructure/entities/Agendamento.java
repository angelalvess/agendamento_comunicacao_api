package com.angel.agendamento_comunicacao_api.infrastructure.entities;

import com.angel.agendamento_comunicacao_api.infrastructure.enums.StatusAgendamentoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Getter
@Entity
@Table(name = "agendamento")
public class Agendamento {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emailDestinatario;
    private String telefoneDestinatario;
    private String mensagem;
    private LocalDateTime dataHoraAgendamento;
    private LocalDateTime dataHoraEnvio;
    private LocalDateTime dataHoraModificado;
    private StatusAgendamentoEnum statusAgendamento;

    @PrePersist
    private void prePersist () {
        dataHoraAgendamento = LocalDateTime.now();
        statusAgendamento = StatusAgendamentoEnum.AGENDADO;
    }
}

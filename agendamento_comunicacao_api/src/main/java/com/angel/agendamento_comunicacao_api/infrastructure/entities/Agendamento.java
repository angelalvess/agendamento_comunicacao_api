package com.angel.agendamento_comunicacao_api.infrastructure.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "agendamentos")
public class Agendamento {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    String email;
    String telefone;
    LocalDateTime dataHoraAgendamento;
    LocalDateTime dataHoraEnvio;
    LocalDateTime dataHoraModificado;


}

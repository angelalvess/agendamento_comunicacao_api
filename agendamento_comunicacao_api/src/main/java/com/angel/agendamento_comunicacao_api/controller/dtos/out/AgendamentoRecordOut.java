package com.angel.agendamento_comunicacao_api.controller.dtos.out;

import com.angel.agendamento_comunicacao_api.infrastructure.enums.StatusAgendamentoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record AgendamentoRecordOut(Long id, String emailDestinatario, String telefoneDestinatario, String mensagem,
                                   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                                   LocalDateTime dataHoraEnvio,
                                   StatusAgendamentoEnum statusAgendamento) {
}
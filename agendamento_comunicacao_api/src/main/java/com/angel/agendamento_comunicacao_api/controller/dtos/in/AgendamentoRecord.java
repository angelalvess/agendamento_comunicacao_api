package com.angel.agendamento_comunicacao_api.controller.dtos.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;


public record AgendamentoRecord(@NotNull String emailDestinatario, @NotNull String telefoneDestinatario,
                                @NotNull String mensagem,
                                @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                                LocalDateTime dataHoraEnvio) {
}

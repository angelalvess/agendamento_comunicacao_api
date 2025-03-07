package com.angel.agendamento_comunicacao_api.infrastructure.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException (String message) {
        super(message);
    }
}

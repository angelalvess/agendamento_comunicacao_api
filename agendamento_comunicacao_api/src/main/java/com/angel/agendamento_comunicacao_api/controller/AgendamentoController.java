package com.angel.agendamento_comunicacao_api.controller;


import com.angel.agendamento_comunicacao_api.business.AgendamentoService;
import com.angel.agendamento_comunicacao_api.controller.dtos.in.AgendamentoRecord;
import com.angel.agendamento_comunicacao_api.controller.dtos.out.AgendamentoRecordOut;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {

    private AgendamentoService agendamentoService;


    @PostMapping
    ResponseEntity<AgendamentoRecordOut> criarAgendamento (AgendamentoRecord agendamento) {

        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoService.criarAgendamento(agendamento));

    }
}


package com.angel.agendamento_comunicacao_api.controller;


import com.angel.agendamento_comunicacao_api.business.AgendamentoService;
import com.angel.agendamento_comunicacao_api.controller.dtos.in.AgendamentoRecord;
import com.angel.agendamento_comunicacao_api.controller.dtos.out.AgendamentoRecordOut;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;


    @PostMapping("/agendamento")
    public ResponseEntity<AgendamentoRecordOut> criarAgendamento (@RequestBody AgendamentoRecord agendamento) {

        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoService.criarAgendamento(agendamento));

    }



}


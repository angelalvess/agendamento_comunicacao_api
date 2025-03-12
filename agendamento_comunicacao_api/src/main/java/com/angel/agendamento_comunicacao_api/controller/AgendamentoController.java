package com.angel.agendamento_comunicacao_api.controller;


import com.angel.agendamento_comunicacao_api.business.AgendamentoService;
import com.angel.agendamento_comunicacao_api.controller.dtos.in.AgendamentoRecord;
import com.angel.agendamento_comunicacao_api.controller.dtos.out.AgendamentoRecordOut;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class AgendamentoController {


    private final AgendamentoService service;

    @PostMapping
    public ResponseEntity<AgendamentoRecordOut> criarAgendamento (@RequestBody AgendamentoRecord agendamento) {

        return ResponseEntity.ok(service.criarAgendamento(agendamento));

    }


}





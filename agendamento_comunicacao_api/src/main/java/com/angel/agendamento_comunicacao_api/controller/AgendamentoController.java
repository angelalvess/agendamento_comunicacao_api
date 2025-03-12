package com.angel.agendamento_comunicacao_api.controller;


import com.angel.agendamento_comunicacao_api.business.AgendamentoService;
import com.angel.agendamento_comunicacao_api.controller.dtos.in.AgendamentoRecord;
import com.angel.agendamento_comunicacao_api.controller.dtos.out.AgendamentoRecordOut;
import com.angel.agendamento_comunicacao_api.infrastructure.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AgendamentoController {


    private final AgendamentoService agendamentoService;

    @PostMapping("/agendamento")
    public ResponseEntity<AgendamentoRecordOut> criarAgendamento (@Valid @RequestBody AgendamentoRecord agendamento) {

        return ResponseEntity.ok(agendamentoService.criarAgendamento(agendamento));

    }

    @GetMapping("/agendamento/{id}")
    public ResponseEntity<AgendamentoRecordOut> buscaAgendamento (@PathVariable("id") Long id) {

        AgendamentoRecordOut out = agendamentoService.buscaAgendamento(id);
        return ResponseEntity.ok(out);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelaAgendamento (@PathVariable("id") Long id) {
        agendamentoService.cancelaAgendamento(id);
        return ResponseEntity.accepted().build();

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(NotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


}





package com.angel.agendamento_comunicacao_api.controller;


import com.angel.agendamento_comunicacao_api.business.AgendamentoService;
import com.angel.agendamento_comunicacao_api.controller.dtos.in.AgendamentoRecord;
import com.angel.agendamento_comunicacao_api.controller.dtos.out.AgendamentoRecordOut;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AgendamentoController {


    private final AgendamentoService agendamentoService;

    @PostMapping("/agendamento")
    public ResponseEntity<AgendamentoRecordOut> criarAgendamento (@RequestBody AgendamentoRecord agendamento) {

        return ResponseEntity.ok(agendamentoService.criarAgendamento(agendamento));

    }

    @GetMapping("/agendamento/{id}")
    public ResponseEntity<AgendamentoRecordOut> buscaAgendamento (@PathVariable("id") Long id) {

        return ResponseEntity.ok(agendamentoService.buscaAgendamento(id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelaAgendamento (@PathVariable("id") Long id) {
        agendamentoService.cancelaAgendamento(id);
        return ResponseEntity.accepted().build();

    }


}





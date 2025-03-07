package com.angel.agendamento_comunicacao_api.business;


import com.angel.agendamento_comunicacao_api.business.mapper.AgendamentoMapper;
import com.angel.agendamento_comunicacao_api.controller.dtos.in.AgendamentoRecord;
import com.angel.agendamento_comunicacao_api.controller.dtos.out.AgendamentoRecordOut;
import com.angel.agendamento_comunicacao_api.infrastructure.exceptions.NotFoundException;
import com.angel.agendamento_comunicacao_api.infrastructure.repositories.AgendamentoRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final AgendamentoMapper agendamentoMapper;


    public AgendamentoRecordOut criarAgendamento (AgendamentoRecord agendamento) {

        return agendamentoMapper.paraOut(agendamentoRepository.
                save(agendamentoMapper.paraAgendamento(agendamento)));
    }


}

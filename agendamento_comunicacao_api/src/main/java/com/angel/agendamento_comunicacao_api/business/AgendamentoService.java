package com.angel.agendamento_comunicacao_api.business;


import com.angel.agendamento_comunicacao_api.business.mapper.IAgendamentoMapper;
import com.angel.agendamento_comunicacao_api.controller.dtos.in.AgendamentoRecord;
import com.angel.agendamento_comunicacao_api.controller.dtos.out.AgendamentoRecordOut;
import com.angel.agendamento_comunicacao_api.infrastructure.repositories.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository repository;
    private final IAgendamentoMapper mapper;


    public AgendamentoRecordOut criarAgendamento (AgendamentoRecord agendamento) {

        return mapper.paraOut(repository.
                save(mapper.paraEntity(agendamento)));
    }


}

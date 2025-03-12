package com.angel.agendamento_comunicacao_api.business;


import com.angel.agendamento_comunicacao_api.business.mapper.IAgendamentoMapper;
import com.angel.agendamento_comunicacao_api.controller.dtos.in.AgendamentoRecord;
import com.angel.agendamento_comunicacao_api.controller.dtos.out.AgendamentoRecordOut;
import com.angel.agendamento_comunicacao_api.infrastructure.entities.Agendamento;
import com.angel.agendamento_comunicacao_api.infrastructure.exceptions.NotFoundException;
import com.angel.agendamento_comunicacao_api.infrastructure.repositories.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final IAgendamentoMapper agendamentoMapper;


    public AgendamentoRecordOut criarAgendamento (AgendamentoRecord agendamento) {

        return agendamentoMapper.paraOut(agendamentoRepository.
                save(agendamentoMapper.paraEntity(agendamento)));
    }

    public AgendamentoRecordOut buscaAgendamento (Long id) {

        return agendamentoMapper.paraOut(agendamentoRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Agendamento nao encontrado.")));
    }


    public void cancelaAgendamento (Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id não encontrador"));
        agendamentoRepository.save(
                agendamentoMapper.paraEntityCancelamento(agendamento)
        );
    }

}



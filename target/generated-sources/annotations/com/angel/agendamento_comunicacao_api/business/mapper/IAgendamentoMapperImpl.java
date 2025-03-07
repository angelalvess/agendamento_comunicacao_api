package com.angel.agendamento_comunicacao_api.business.mapper;

import com.angel.agendamento_comunicacao_api.controller.dtos.in.AgendamentoRecord;
import com.angel.agendamento_comunicacao_api.controller.dtos.out.AgendamentoRecordOut;
import com.angel.agendamento_comunicacao_api.infrastructure.entities.Agendamento;
import com.angel.agendamento_comunicacao_api.infrastructure.enums.StatusAgendamentoEnum;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-07T17:55:22-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Azul Systems, Inc.)"
)
@Component
public class IAgendamentoMapperImpl implements IAgendamentoMapper {

    @Override
    public Agendamento paraEntity(AgendamentoRecord agendamento) {
        if ( agendamento == null ) {
            return null;
        }

        Agendamento.AgendamentoBuilder agendamento1 = Agendamento.builder();

        agendamento1.emailDestinatario( agendamento.emailDestinatario() );
        agendamento1.telefoneDestinatario( agendamento.telefoneDestinatario() );
        agendamento1.mensagem( agendamento.mensagem() );
        agendamento1.dataHoraEnvio( agendamento.dataHoraEnvio() );

        return agendamento1.build();
    }

    @Override
    public AgendamentoRecordOut paraOut(Agendamento agendamento) {
        if ( agendamento == null ) {
            return null;
        }

        String emailDestinatario = null;
        String telefoneDestinatario = null;
        String mensagem = null;
        LocalDateTime dataHoraEnvio = null;
        StatusAgendamentoEnum statusAgendamento = null;

        emailDestinatario = agendamento.getEmailDestinatario();
        telefoneDestinatario = agendamento.getTelefoneDestinatario();
        mensagem = agendamento.getMensagem();
        dataHoraEnvio = agendamento.getDataHoraEnvio();
        statusAgendamento = agendamento.getStatusAgendamento();

        AgendamentoRecordOut agendamentoRecordOut = new AgendamentoRecordOut( emailDestinatario, telefoneDestinatario, mensagem, dataHoraEnvio, statusAgendamento );

        return agendamentoRecordOut;
    }
}

package com.angel.agendamento_comunicacao_api.business.mapper;


import com.angel.agendamento_comunicacao_api.controller.dtos.in.AgendamentoRecord;
import com.angel.agendamento_comunicacao_api.controller.dtos.out.AgendamentoRecordOut;
import com.angel.agendamento_comunicacao_api.infrastructure.entities.Agendamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper
public interface AgendamentoMapper {


    Agendamento paraAgendamento (AgendamentoRecord agendamento);
    AgendamentoRecordOut paraOut (Agendamento agendamento);

//    @Mapping(target = "dataHoraModificado", expression = "java(LocalDateTime.now())")
//    @Mapping(target = "statusAgendamentoEnum", expression = "java(StatusAgendamentoEnum.CANCELADO)")
//    Agendamento AgendamentoCancelado (Agendamento agendamento);

}

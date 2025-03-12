package com.angel.agendamento_comunicacao_api.business.mapper;

import com.angel.agendamento_comunicacao_api.controller.dtos.in.AgendamentoRecord;
import com.angel.agendamento_comunicacao_api.controller.dtos.out.AgendamentoRecordOut;
import com.angel.agendamento_comunicacao_api.infrastructure.entities.Agendamento;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;


@Mapper(componentModel = SPRING)
public interface IAgendamentoMapper {

    Agendamento paraEntity (AgendamentoRecord agendamento);

    AgendamentoRecordOut paraOut (Agendamento agendamento);

    @Mapping(target = "dataHoraModificado", expression = "java(LocalDateTime.now())")
    @Mapping(target = "statusAgendamento", expression = "java(StatusAgendamentoEnum.CANCELADO)")
    Agendamento paraEntityCancelamento (Agendamento agendamento);

}

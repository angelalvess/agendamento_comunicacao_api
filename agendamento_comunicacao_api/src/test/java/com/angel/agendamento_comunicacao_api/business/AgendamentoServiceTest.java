package com.angel.agendamento_comunicacao_api.business;

import com.angel.agendamento_comunicacao_api.business.mapper.IAgendamentoMapper;
import com.angel.agendamento_comunicacao_api.controller.AgendamentoController;
import com.angel.agendamento_comunicacao_api.controller.dtos.in.AgendamentoRecord;
import com.angel.agendamento_comunicacao_api.controller.dtos.out.AgendamentoRecordOut;
import com.angel.agendamento_comunicacao_api.infrastructure.entities.Agendamento;
import com.angel.agendamento_comunicacao_api.infrastructure.enums.StatusAgendamentoEnum;
import com.angel.agendamento_comunicacao_api.infrastructure.exceptions.NotFoundException;
import com.angel.agendamento_comunicacao_api.infrastructure.repositories.AgendamentoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.validation.constraints.NotNull;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class AgendamentoServiceTest {

    @InjectMocks
    private AgendamentoService service;

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @Mock
    private IAgendamentoMapper agendamentoMapper;


    private AgendamentoRecord agendamentoRecord;
    private Agendamento agendamento;
    private AgendamentoRecordOut agendamentoRecordOut;


    @BeforeEach
    void setup () throws JsonProcessingException {

        agendamento = new Agendamento(3L, "angelalves@gmail.com", "111111", "teste", LocalDateTime.now(), LocalDateTime.of(2000, 10, 7, 20, 10, 44), null, StatusAgendamentoEnum.AGENDADO);
        agendamentoRecord = new AgendamentoRecord("angelalves@gmail.com", "111111", "teste", LocalDateTime.of(2000, 10, 7, 20, 10, 44));
        agendamentoRecordOut = new AgendamentoRecordOut(3L, "angelalves@gmail.com", "111111", "teste", LocalDateTime.of(2000, 10, 7, 20, 10, 44), StatusAgendamentoEnum.AGENDADO);

    }

    @Test
    void deveSalvarAgendamentoComSucesso () throws Exception {

        when(agendamentoMapper.paraEntity(agendamentoRecord)).thenReturn(agendamento);
        when(agendamentoRepository.save(agendamento)).thenReturn(agendamento);
        when(agendamentoMapper.paraOut(agendamento)).thenReturn(agendamentoRecordOut);

        AgendamentoRecordOut out = service.criarAgendamento(agendamentoRecord);

        verify(agendamentoMapper, times(1)).paraEntity(agendamentoRecord);
        verify(agendamentoRepository, times(1)).save(agendamento);
        verify(agendamentoMapper, times(1)).paraOut(agendamento);

        assertThat(out).usingRecursiveComparison().isEqualTo(agendamentoRecordOut);

    }

    @Test
    void deveBuscarAgendamentoComSucesso () throws Exception {

        when(agendamentoRepository.findById(1L)).
                thenReturn(Optional.of(agendamento));
        when(agendamentoMapper.paraOut(agendamento)).thenReturn(agendamentoRecordOut);

        AgendamentoRecordOut out = service.buscaAgendamento(1L);

        verify(agendamentoRepository, times(1)).findById(1L);
        verify(agendamentoMapper, times(1)).paraOut(agendamento);

        assertThat(out).usingRecursiveComparison().isEqualTo(agendamentoRecordOut);
    }


    @Test
    void deveCancelarAgendamentoComSucesso () throws Exception {
        when(agendamentoRepository.findById(3L)).
                thenReturn(Optional.of(agendamento));
        when(agendamentoMapper.paraEntityCancelamento(agendamento)).thenReturn(agendamento);
        when(agendamentoRepository.save(agendamento)).thenAnswer(invocation -> {
            Agendamento savedAgendamento = invocation.getArgument(0);
            savedAgendamento.setStatusAgendamento(StatusAgendamentoEnum.CANCELADO);
            savedAgendamento.setDataHoraModificado(LocalDateTime.of(2025, 3, 12, 14, 0, 0));
            return savedAgendamento;
        });

        service.cancelaAgendamento(3L);

        assertThat(agendamento.getStatusAgendamento()).isEqualTo(StatusAgendamentoEnum.CANCELADO);
        assertThat(agendamento.getDataHoraModificado()).isEqualTo(LocalDateTime.of(2025, 3, 12, 14, 0, 0));

        verify(agendamentoRepository, times(1)).findById(3L);
        verify(agendamentoMapper, times(1)).paraEntityCancelamento(agendamento);
        verify(agendamentoRepository, times(1)).save(agendamento);
    }

    @Test
    void deveRetornarNotFoundParaCancelarAgendamentoInexistente () {
        when(agendamentoRepository.findById(1L)).thenThrow(new NotFoundException("Erro ao atualizar, agendamento nao encontrado."));

        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.cancelaAgendamento(1L));

        assertEquals("Erro ao atualizar, agendamento nao encontrado.", exception.getMessage());

        verify(agendamentoRepository, times(1)).findById(1L);
    }

//    @Test
//    void deveBuscarAgendamentosComSucesso () {
//        when()
//    }


}


//Teste de Falha ao Salvar Agendamento.
//
//
//
//Teste de Listagem de Agendamentos.
//
//Teste de Deleção de Agendamento.
//
//Teste de Deleção de Agendamento Não Encontrado.
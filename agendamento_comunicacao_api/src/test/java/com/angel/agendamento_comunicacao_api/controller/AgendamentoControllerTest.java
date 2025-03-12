package com.angel.agendamento_comunicacao_api.controller;

import com.angel.agendamento_comunicacao_api.business.AgendamentoService;
import com.angel.agendamento_comunicacao_api.controller.dtos.in.AgendamentoRecord;
import com.angel.agendamento_comunicacao_api.controller.dtos.out.AgendamentoRecordOut;
import com.angel.agendamento_comunicacao_api.infrastructure.enums.StatusAgendamentoEnum;
import com.angel.agendamento_comunicacao_api.infrastructure.exceptions.NotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AgendamentoControllerTest {

    @InjectMocks
    private AgendamentoController controller;

    @Mock
    private AgendamentoService agendamentoService;


    private MockMvc mockMvc;
    private AgendamentoRecord agendamentoRecord;
    private AgendamentoRecordOut agendamentoRecordOut;
    private final ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    void setup () throws JsonProcessingException {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        agendamentoRecord = new AgendamentoRecord("angelalves@gmail.com", "111111", "teste", LocalDateTime.of(2000, 10, 7, 20, 10, 44));
        agendamentoRecordOut = new AgendamentoRecordOut(1L, "angelalves@gmail.com", "111111", "teste", LocalDateTime.of(2000, 10, 7, 20, 10, 44), StatusAgendamentoEnum.AGENDADO);

        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void deveCriarAgendamentoComSucesso () throws Exception {
        when(agendamentoService.criarAgendamento(agendamentoRecord)).thenReturn(agendamentoRecordOut);

        mockMvc.perform(post("/api/agendamento").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(agendamentoRecord))).andExpect(status()
                .isOk()).andExpect(jsonPath("$.id").value(1L));

        verify(agendamentoService, times(1)).criarAgendamento(agendamentoRecord);
    }


    @Test
    void deveBuscarAgendamentoComSucesso () throws Exception {
        when(agendamentoService.buscaAgendamento(1L)).thenReturn(agendamentoRecordOut);

        mockMvc.perform(get("/api/agendamento/1"))
                .andExpect(status().isOk()).
                andExpect(jsonPath("$.id").value(1L));

        verify(agendamentoService, times(1)).buscaAgendamento(1L);
    }

    @Test
    void deveRetornarNotFoundParaAgendamentoInexistente () throws Exception {
        when(agendamentoService.buscaAgendamento(1L)).thenThrow(new NotFoundException("Agendamento nao encontrado."));

        mockMvc.perform(get("/api/agendamento/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Agendamento nao encontrado."));

        verify(agendamentoService, times(1)).buscaAgendamento(1L);
    }

    @Test
    void deveCancelarAgendamentoComSucesso () throws Exception {
        doNothing().when(agendamentoService).cancelaAgendamento(1L);

        mockMvc.perform(delete("/api/1")).andExpect(status().isAccepted());

        verify(agendamentoService, times(1)).cancelaAgendamento(1L);
    }

    @Test
    void deveRetornarNotFoundParaCancelarAgendamentoInexistente () throws Exception {
        doThrow(new NotFoundException("Agendamento nao encontrado.")).when(agendamentoService).cancelaAgendamento(1L);

        mockMvc.perform(delete("/api/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Agendamento nao encontrado."));

        verify(agendamentoService, times(1)).cancelaAgendamento(1L);
    }

    @Test
    void deveRetornarBadRequestParaAgendamentoComCamposNulos() throws Exception {
        AgendamentoRecord agendamentoInvalido = new AgendamentoRecord(null, null, null,null);

        mockMvc.perform(post("/api/agendamento").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(agendamentoInvalido))).andExpect(status().isBadRequest());
    }
}



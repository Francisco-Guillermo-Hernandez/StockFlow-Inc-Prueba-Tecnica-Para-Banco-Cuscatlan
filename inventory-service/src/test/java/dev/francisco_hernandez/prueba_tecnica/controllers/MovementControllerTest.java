package dev.francisco_hernandez.prueba_tecnica.controllers;

import dev.francisco_hernandez.prueba_tecnica.dto.MovementDto;
import dev.francisco_hernandez.prueba_tecnica.entities.Movement;
import dev.francisco_hernandez.prueba_tecnica.mapper.MovementMapper;
import dev.francisco_hernandez.prueba_tecnica.service.MovementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovementController.class)
@AutoConfigureMockMvc(addFilters = false)
public class MovementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovementService movementService;

    @MockBean
    private MovementMapper movementMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    void shouldRegisterMovement() throws Exception {
        MovementDto dto = new MovementDto(1L, 10, "Restock product", "in");
        Movement movement = new Movement(1L, 1L, 10, "Restock product", "in", LocalDateTime.now());

        when(movementMapper.toEntity(any(MovementDto.class))).thenReturn(movement);
        when(movementService.createAMovement(any(Movement.class))).thenReturn(movement);

        mockMvc.perform(post("/movements")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1))
                .andExpect(jsonPath("$.quantity").value(10))
                .andExpect(jsonPath("$.reason").value("Restock product"))
                .andExpect(jsonPath("$.operationName").value("in"));
    }

    @Test
    @WithMockUser
    void shouldGetHistory() throws Exception {
        Movement movement = new Movement(1L, 1L, 10, "Restock product", "in", LocalDateTime.now());
        List<Movement> history = Arrays.asList(movement);

        when(movementService.listAllByProductId(1L)).thenReturn(history);

        mockMvc.perform(get("/movements/1/history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productId").value(1))
                .andExpect(jsonPath("$[0].quantity").value(10));
    }

    @Test
    @WithMockUser
    void shouldListMovements() throws Exception {
        Movement movement = new Movement(1L, 1L, 10, "Restock product", "in", LocalDateTime.now());
        List<Movement> movements = Arrays.asList(movement);

        when(movementService.listAll()).thenReturn(movements);

        mockMvc.perform(get("/movements"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productId").value(1));
    }
}

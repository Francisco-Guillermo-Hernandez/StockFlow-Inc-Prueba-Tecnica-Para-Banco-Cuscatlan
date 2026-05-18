package dev.francisco_hernandez.prueba_tecnica.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.mock.mockito.MockBean;
import dev.francisco_hernandez.prueba_tecnica.service.AlertService;
import java.util.Collections;
import static org.mockito.Mockito.when;

@WebMvcTest(AlertController.class)
@AutoConfigureMockMvc(addFilters = false) // Disable security for unit tests
public class AlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlertService alertService;

    @Test
    @WithMockUser
    void shouldReturnHelloWorldFromListAlerts() throws Exception {
        when(alertService.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/alerts/"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @WithMockUser
    void shouldReturnHelloWorldFromEvents() throws Exception {
        mockMvc.perform(get("/alerts/events"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello world"));
    }
}

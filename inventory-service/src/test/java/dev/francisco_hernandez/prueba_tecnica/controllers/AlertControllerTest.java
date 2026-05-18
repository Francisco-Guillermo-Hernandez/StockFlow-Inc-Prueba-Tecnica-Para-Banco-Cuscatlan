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

@WebMvcTest(AlertController.class)
@AutoConfigureMockMvc(addFilters = false) // Disable security for unit tests
public class AlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void shouldReturnHelloWorldFromListAlerts() throws Exception {
        mockMvc.perform(get("/alerts/"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello world"));
    }

    @Test
    @WithMockUser
    void shouldReturnHelloWorldFromEvents() throws Exception {
        mockMvc.perform(get("/alerts/events"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello world"));
    }
}

package com.torneos.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(EquipoController.class)
class EquipoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRenderNewTeamForm() throws Exception {
        mockMvc.perform(get("/equipos/nuevo"))
                .andExpect(status().isOk())
                .andExpect(view().name("equipo/form-equipo"))
                .andExpect(model().attributeExists("equipo"))
                .andExpect(model().attributeExists("torneoId"))
                .andExpect(model().attributeExists("torneoNombre"));
    }
}

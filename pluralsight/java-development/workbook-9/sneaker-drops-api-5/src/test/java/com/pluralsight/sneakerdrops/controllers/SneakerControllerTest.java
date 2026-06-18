package com.pluralsight.sneakerdrops.controllers;

import com.pluralsight.sneakerdrops.service.SneakerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SneakerController.class)
class SneakerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SneakerService sneakerService;

    @Test
    void getById_MissingId_Returns404() throws Exception {
        // Arrange
        when(sneakerService.byId(99L)).thenReturn(null);

        // Act + Assert
        mockMvc.perform(get("/api/sneakers/99"))
                .andExpect(status().isNotFound());
    }
}

package com.chauhan.movie_service.api;

import com.chauhan.movie_service.model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenMovie_whenCreateMovie_thenReturnSavedMovie() throws Exception {

        Movie movie = new Movie();
        movie.setName("RRR");
        movie.setDirector("S. S. Rajamouli");
        movie.setActors(List.of("NTR", "Ram Charan", "Alia Bhatt"));

        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movie)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name").value("RRR"))
                .andExpect(jsonPath("$.director").value("S. S. Rajamouli"))
                .andExpect(jsonPath("$.actors[0]").value("NTR"))
                .andExpect(jsonPath("$.actors[1]").value("Ram Charan"))
                .andExpect(jsonPath("$.actors[2]").value("Alia Bhatt"));
    }
}
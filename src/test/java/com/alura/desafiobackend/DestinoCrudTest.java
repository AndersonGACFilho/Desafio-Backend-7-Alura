package com.alura.desafiobackend;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.alura.desafiobackend.Entities.Destino;
import com.alura.desafiobackend.Repositories.DestinosRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
public class DestinoCrudTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private DestinosRepository destinoRepository;

        List<Destino> lastDestinos = new ArrayList<Destino>();

        @BeforeEach
        public void setUp() {
        

        }


        @AfterEach
        public void tearDown() throws Exception {
                if (lastDestinos != null) {
                        for (Destino destino : lastDestinos) {
                                destinoRepository.deleteById(destino.getId());
                        }
                }
        }
        //Testar todos os endpoints de CRUD

        @Test
        public void testGetDestino() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/destinos/todos"))
                        .andExpect(status().isOk());
        }

        @Test
        public void testPostDestino() throws Exception {
                Destino destino = new Destino("Foto1", "Foto2", "Nome", 10000,"Meta", "Texto Descritivo");

                ResultActions postResultActions = mockMvc.perform(MockMvcRequestBuilders.post("/destinos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(destino)))
                        .andExpect(status().isOk());

                MvcResult postResult = postResultActions.andReturn();

                lastDestinos.add(objectMapper.readValue(postResult.getResponse().getContentAsString(), Destino.class));

        }


        @Test
        public void testPutDepoimento() throws Exception {
                // Primeiro, insira um depoimento para atualizá-lo posteriormente.
                Destino destino = new Destino("Foto1", "Foto2", "Nome", 10000,"Meta", "Texto Descritivo");
                MvcResult postResult = mockMvc.perform(MockMvcRequestBuilders.post("/destinos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(destino)))
                        .andReturn();

                Destino savedDestino = objectMapper.readValue(postResult.getResponse().getContentAsString(), Destino.class);

                lastDestinos.add(savedDestino);

                // Atualize o destino.
                savedDestino.setNome("Novo Nome");
                savedDestino.setPreco(1000000);
                savedDestino.setFoto1("Foto1");
                savedDestino.setFoto2("Foto4");

                mockMvc.perform(MockMvcRequestBuilders.put("/destinos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(savedDestino)))
                        .andExpect(status().isOk());

        }

        @Test
        public void testDeleteDepoimento() throws Exception {
                // Primeiro, insira um depoimento para excluí-lo posteriormente.
                Destino destino = new Destino("Foto1", "Foto2", "Nome", 10000,"Meta", "Texto Descritivo");
                MvcResult postResult = mockMvc.perform(MockMvcRequestBuilders.post("/destinos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(destino)))
                        .andReturn();

                Destino savedDestino = objectMapper.readValue(postResult.getResponse().getContentAsString(), Destino.class);

                mockMvc.perform(MockMvcRequestBuilders.delete("/destinos?id=" + savedDestino.getId()))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        }
}

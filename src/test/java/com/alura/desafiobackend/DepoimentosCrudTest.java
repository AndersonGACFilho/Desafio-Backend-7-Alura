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

import com.alura.desafiobackend.Entities.Depoimento;
import com.alura.desafiobackend.Repositories.DepoimentosRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
public class DepoimentosCrudTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        List<Depoimento> lastDepoimentos = new ArrayList<Depoimento>();

        @Autowired
        private DepoimentosRepository depoimentoRepository;



        @BeforeEach
        public void setUp() {
        

        }


        @AfterEach
        public void tearDown() throws Exception {
                if (lastDepoimentos != null) {
                        for (Depoimento depoimento : lastDepoimentos) {
                                depoimentoRepository.deleteById(depoimento.getId());
                        }
                }
        }
        //Testar todos os endpoints de CRUD

        @Test
        public void testGetDepoimentos() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/depoimentos"))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        }

        @Test
        public void testPostDepoimento() throws Exception {
                Depoimento depoimento = new Depoimento("Nome", "Depoimento", "Foto");

                ResultActions postResultActions = mockMvc.perform(MockMvcRequestBuilders.post("/depoimentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(depoimento)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON));

                MvcResult postResult = postResultActions.andReturn();

                lastDepoimentos.add(objectMapper.readValue(postResult.getResponse().getContentAsString(), Depoimento.class));

        }


        @Test
        public void testPutDepoimento() throws Exception {
                // Primeiro, insira um depoimento para atualizá-lo posteriormente.
                Depoimento depoimento = new Depoimento("Nome", "Depoimento", "Foto");
                MvcResult postResult = mockMvc.perform(MockMvcRequestBuilders.post("/depoimentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(depoimento)))
                        .andReturn();

                Depoimento savedDepoimento = objectMapper.readValue(postResult.getResponse().getContentAsString(), Depoimento.class);

                lastDepoimentos.add(savedDepoimento);

                // Atualize o depoimento.
                savedDepoimento.setNome("Novo Nome");
                savedDepoimento.setDepoimento("Novo Depoimento");
                savedDepoimento.setFoto("Nova Foto");

                mockMvc.perform(MockMvcRequestBuilders.put("/depoimentos?"+
                                "id=" + savedDepoimento.getId() + 
                                "&nome=" + savedDepoimento.getNome() + 
                                "&depoimento_text=" + savedDepoimento.getDepoimento() + 
                                "&foto=" + savedDepoimento.getFoto())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(savedDepoimento)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        }

        @Test
        public void testDeleteDepoimento() throws Exception {
                // Primeiro, insira um depoimento para excluí-lo posteriormente.
                Depoimento depoimento = new Depoimento("Nome", "Depoimento", "Foto");
                MvcResult postResult = mockMvc.perform(MockMvcRequestBuilders.post("/depoimentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(depoimento)))
                        .andReturn();

                Depoimento savedDepoimento = objectMapper.readValue(postResult.getResponse().getContentAsString(), Depoimento.class);

                mockMvc.perform(MockMvcRequestBuilders.delete("/depoimentos?id=" + savedDepoimento.getId()))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        }
}

package com.test.restfullbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.restfullbackend.service.JsonServiceImpl;
import com.test.restfullbackend.model.JsonObj;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebMvcTest(JObjectsController.class)
@AutoConfigureMockMvc
public class JObjectsControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JsonServiceImpl service;

    @Test
    public void whenGetAllJsonObjectsTheyReturn() throws Exception {
        List<JsonObj> list = List.of(new JsonObj("13", "{\"id\": \"13\", \"name\": \"Иванов\"}"),
                            new JsonObj("13", "{\"id\": \"13\", \"name\": \"Петров\"}"),
                            new JsonObj("55", "{\"id\": \"55\", \"name\": \"Сидоров\"}"));
        ObjectMapper mapper = new ObjectMapper();
        given(this.service.getAll()).willReturn(list);
        this.mockMvc.perform(get("/").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(mapper.writeValueAsString(list)));
    }

    @Test
    public void whenGetJsonObjectsByIdTheyReturn() throws Exception {
        List<JsonObj> list = List.of(new JsonObj("13", "{\"id\": \"13\", \"name\": \"Иванов\"}"),
                new JsonObj("13", "{\"id\": \"13\", \"name\": \"Петров\"}"));
        ObjectMapper mapper = new ObjectMapper();
        given(this.service.getById("13")).willReturn(list);
        this.mockMvc.perform(get("/13").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(mapper.writeValueAsString(list))
                );
    }


    @Test
    public void whenCreateItsCreated() throws Exception {
        String jSrting =  "{\"id\": \"13\", \"name\": \"Иванов\"}";
        JsonObj joCreated = new JsonObj("13", jSrting);
        joCreated.setId(1);
        given(this.service.create(joCreated)).willReturn(joCreated);
        this.mockMvc.perform(post("/")
                .content(jSrting))
                .andDo(print())
                .andExpect(status().isCreated()
                );
    }

    @Test
    public void whenNotCreatedItsBadRequest() throws Exception {
        String jSrting =  "{\"id\": \"13\", \"name\": \"Иванов\"}";
        JsonObj joCreated = new JsonObj("13", jSrting);
        given(this.service.create(joCreated)).willReturn(joCreated);
        this.mockMvc.perform(post("/")
                .content(jSrting))
                .andDo(print())
                .andExpect(status().isBadRequest()
                );
    }

}

package com.test.restfullbackend.service;


import com.test.restfullbackend.model.JsonObj;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JsonServiceTest {

    @Autowired
    private JsonService service;

    @Test
    public void whenCreateThenSaveInDb() {
        JsonObj jo = new JsonObj("999", "{\"id\": \"999\", \"name\": \"Пржевальский\"}");
        JsonObj joCreated = this.service.create(jo);
        List<JsonObj> result = this.service.getAll();
        assertNotNull(joCreated.getId());
        assertTrue(result.contains(joCreated));
    }


    @Test
    public void whenGetAllTheyHaveGotten() {
        JsonObj jo = new JsonObj("13", "{\"id\": \"13\", \"name\": \"Петров\"}");
        JsonObj jo1 = new JsonObj("55", "{\"id\": \"55\", \"name\": \"Сидоров\"}");
        JsonObj jo2 = new JsonObj("59", "{\"id\": \"59\", \"name\": \"Иванов\"}");
        this.service.create(jo);
        this.service.create(jo1);
        this.service.create(jo2);
        List<JsonObj> result = this.service.getAll();
        assertTrue(result.contains(jo));
        assertThat(result.size(), is(3));
    }

    @Test
    public void whenGetByIdItsFound() {
        JsonObj jo = new JsonObj("13", "{\"id\": \"13\", \"name\": \"Петров\"}");
        JsonObj jo1 = new JsonObj("13", "{\"id\": \"13\", \"name\": \"Сидоров\"}");
        this.service.create(jo);
        this.service.create(jo1);
        List<JsonObj> result = this.service.getById("13");
        assertTrue(result.contains(jo));
        assertThat(result.size(), is(2));
    }

}

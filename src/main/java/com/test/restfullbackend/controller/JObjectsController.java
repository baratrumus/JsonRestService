package com.test.restfullbackend.controller;

import com.test.restfullbackend.Service.JsonServiceImpl;
import com.test.restfullbackend.model.JsonObj;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class JObjectsController {

    private final JsonServiceImpl service;

    public JObjectsController(JsonServiceImpl jsonService) {
        this.service = jsonService;
    }

    /**
     * контроллер возвращает все объекты json из бд;
     */
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<JsonObj>> getAll() {
        List<JsonObj> list = service.getAll();
        if (list.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * контроллер возвращает json по id;
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<JsonObj>> getById(@PathVariable("id") String id) {
        List<JsonObj> list = service.getById(id);
        if (list.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * контроллер сохраняет объект json в бд;
     */
    @PostMapping("/")
    public ResponseEntity create(@RequestBody String jsonString) {
        if ((jsonString != null) && !(jsonString.isEmpty())) {
            JsonObj jo = getJOfromString(jsonString);
            JsonObj createdJo = service.create(jo);
            if (createdJo.getId() != null) {
                return new ResponseEntity(HttpStatus.CREATED);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    private JsonObj getJOfromString(String jsonString) {
        JSONObject jo = new JSONObject();
        try {
            jo = (JSONObject) new  JSONParser().parse(jsonString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String id = (String) jo.get("id");
        return new JsonObj(id, jsonString);
    }

    /**
     * контроллер возвращает строку, найденную в JsonObj с {id} по ключу fieldToFind

    @GetMapping(value = "/search/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getById(@PathVariable("id") String id, @RequestParam String fieldToFind) {
        String value = service.findValueWithInId(id, fieldToFind);
        if (value == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(value, HttpStatus.OK);
    }
     */



}

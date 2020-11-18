package com.test.restfullbackend.service;

import com.test.restfullbackend.model.JsonObj;
import java.util.List;

public interface JsonService {

    JsonObj create(final JsonObj jo);

    List<JsonObj> getAll();

    List<JsonObj> getById(String id);

    //String findValueWithInId(String id, String fieldToFind);

}

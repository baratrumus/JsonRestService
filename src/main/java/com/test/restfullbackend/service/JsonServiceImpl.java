package com.test.restfullbackend.service;

import com.test.restfullbackend.model.JsonObj;
import com.test.restfullbackend.repository.JObjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JsonServiceImpl implements JsonService {

    private final JObjectsRepository repository;

    @Autowired
    public JsonServiceImpl(JObjectsRepository jObjectsRepository) {
        this.repository = jObjectsRepository;
    }

    /**
     * метод возвращает json по id
     */
    @Override
    public JsonObj create(final JsonObj jo) {
        return repository.save(jo);
    }

    /**
     * метод возвращает все объекты json из бд
     */
    @Override
    public List<JsonObj> getAll() {
        return this.repository.findAll();
    }

    /**
     * метод возвращает список json с заданным id
     */
    @Override
    public List<JsonObj> getById(String id) {
        return repository.findByInnerId(id);
    }

}

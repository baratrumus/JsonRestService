package com.test.restfullbackend.repository;

import com.test.restfullbackend.model.JsonObj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JObjectsRepository extends JpaRepository<JsonObj, Integer> {
    @Query(value = "SELECT * FROM json_objects WHERE json_id = :id", nativeQuery = true)
    List<JsonObj> findByInnerId(@Param("id") String id);
}



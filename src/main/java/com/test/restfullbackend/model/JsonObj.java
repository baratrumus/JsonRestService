package com.test.restfullbackend.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

@TypeDef(name = "jsonb",
        typeClass = JsonBinaryType.class)
       // defaultForType = JsonNode.class)
@Entity
@Table(name = "json_objects")
public class JsonObj {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@NaturalId
    private String json_id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String json_node;

    public JsonObj() {
    }

    public JsonObj(String json_id, String json_node) {
        this.json_id = json_id;
        this.json_node = json_node;
    }

    public JsonObj(String json_id) {
        this.json_id = json_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJson_id() {
        return json_id;
    }

    public void setJson_id(String json_id) {
        this.json_id = json_id;
    }

    public String getJson_node() {
        return json_node;
    }

    public void setJson_node(String json_node) {
        this.json_node = json_node;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonObj jsonObj = (JsonObj) o;
        return json_node.equals(jsonObj.json_node);
    }

    @Override
    public int hashCode() {
        return Objects.hash(json_node);
    }

    @Override
    public String toString() {
        return json_node;
    }
}

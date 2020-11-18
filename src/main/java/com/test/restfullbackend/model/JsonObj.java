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
    @JoinColumn (name = "json_id")
    private String jsonId;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @JoinColumn (name = "json_node")
    private String jsonNode;

    public JsonObj() {
    }

    public JsonObj(String jsonId, String jsonNode) {
        this.jsonId = jsonId;
        this.jsonNode = jsonNode;
    }

    public JsonObj(String jsonId) {
        this.jsonId = jsonId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJsonId() {
        return jsonId;
    }

    public void setJsonId(String jsonId) {
        this.jsonId = jsonId;
    }

    public String getJsonNode() {
        return jsonNode;
    }

    public void setJsonNode(String jsonNode) {
        this.jsonNode = jsonNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JsonObj jsonObj = (JsonObj) o;
        return jsonNode.equals(jsonObj.jsonNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jsonNode);
    }

    @Override
    public String toString() {
        return jsonNode;
    }
}

package io.apitest.example.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by prasantabiswas on 26/06/18.
 */
@Entity
public class SubView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    @ManyToMany(targetEntity=Field.class)
    private List<Field> fields;

    public SubView() {
        super();
    }

    public SubView(long id, String name, String description, List<Field> fields, View view) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fields = fields;
    }

    public SubView(long id, String name, String description, List<Field> fields) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fields = fields;
    }

    public SubView(String name, String description, List<Field> fields, View view) {
        this.name = name;
        this.description = description;
        this.fields = fields;
    }

    public SubView(String name, String description, List<Field> fields) {
        this.name = name;
        this.description = description;
        this.fields = fields;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "SubView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", fields=" + fields +
                '}';
    }
}

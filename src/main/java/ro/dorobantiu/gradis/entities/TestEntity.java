package ro.dorobantiu.gradis.entities;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class TestEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(nullable = false, unique = true, length = 36)
    private String id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    private String description;

    protected TestEntity() {
    }

    public TestEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}

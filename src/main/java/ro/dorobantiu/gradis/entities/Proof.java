package ro.dorobantiu.gradis.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "PROOFS")
public class Proof implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true, length = 36)
    private String id;

    @ManyToOne
    private Paper paper;

    public Proof() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }
}


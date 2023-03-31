package ro.dorobantiu.gradis.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "JOURNALRANKS")
public class JournalRank implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true, length = 36)
    private String id;

    @OneToMany(mappedBy = "journalRank")
    private Collection<Journal> journals = new ArrayList<>();

    public JournalRank() {
    }

    public JournalRank(String id, Collection<Journal> journals) {
        this.id = id;
        this.journals = journals;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Collection<Journal> getJournals() {
        return journals;
    }

    public void setJournals(Collection<Journal> journals) {
        this.journals = journals;
    }
}
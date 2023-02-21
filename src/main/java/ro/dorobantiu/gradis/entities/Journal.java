package ro.dorobantiu.gradis.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "JOURNALS")
public class Journal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true, length = 36)
    private String id;

    @ManyToOne
    private JournalRank journalRank;

    @OneToMany(mappedBy = "journal")
    private Collection<Paper> papers = new ArrayList<>();

    public Journal() {
    }

    public Journal(String id, JournalRank journalRank, Collection<Paper> papers) {
        this.id = id;
        this.journalRank = journalRank;
        this.papers = papers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JournalRank getJournalRank() {
        return journalRank;
    }

    public void setJournalRank(JournalRank journalRank) {
        this.journalRank = journalRank;
    }

    public Collection<Paper> getPapers() {
        return papers;
    }

    public void setPapers(Collection<Paper> papers) {
        this.papers = papers;
    }
}


package ro.dorobantiu.gradis.entities;


import jakarta.persistence.*;
import ro.dorobantiu.gradis.DTOs.AuthorDTO;
import ro.dorobantiu.gradis.DTOs.AuthorWithIdAndNameDTO;
import ro.dorobantiu.gradis.DTOs.PaperDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "PAPERS")
public class Paper implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true, length = 36)
    private String id;

    @Column
    private String title;
    @ManyToOne
    private Journal journal;

    @Column
    private String rawAuthorList;
    @Column
    private String rawJournalTitle;
    @ManyToMany
    private Collection<Author> authors = new ArrayList<>();

    @OneToMany(mappedBy = "paper")
    private Collection<Proof> proofs = new ArrayList<>();

    public Paper() {
    }

    public Paper(String title, String rawAuthorList, String rawJournalTitle) {
        this.title = title;
        this.rawAuthorList = rawAuthorList;
        this.rawJournalTitle = rawJournalTitle;
    }

    public String getRawJournalTitle() {
        return rawJournalTitle;
    }

    public void setRawJournalTitle(String rawJournalTitle) {
        this.rawJournalTitle = rawJournalTitle;
    }

    public String getRawAuthorList() {
        return rawAuthorList;
    }

    public void setRawAuthorList(String rawAuthorList) {
        this.rawAuthorList = rawAuthorList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public Collection<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Collection<Author> authors) {
        this.authors = authors;
    }

    public Collection<Proof> getProofs() {
        return proofs;
    }

    public void setProofs(Collection<Proof> proofs) {
        this.proofs = proofs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

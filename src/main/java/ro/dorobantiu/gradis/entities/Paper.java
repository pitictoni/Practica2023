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

    @ManyToMany
    private Collection<Author> authors = new ArrayList<>();

    @OneToMany(mappedBy = "paper")
    private Collection<Proof> proofs = new ArrayList<>();

    public Paper() {
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

    @Override
    public String toString() {
        return "Paper{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", journal=" + journal +
                ", authors=" + authors +
                ", proofs=" + proofs +
                '}';
    }

    public PaperDTO toPaperDTO() {
        List<AuthorWithIdAndNameDTO> authorDTOs = new ArrayList<>();
        for (Author a : authors) {
            authorDTOs.add(new AuthorWithIdAndNameDTO(id, a.getName()));
        }
        return new PaperDTO(id, title, authorDTOs);
    }
}

package ro.dorobantiu.gradis.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "JOURNALS")
public class Journal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true, length = 36)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private float impactFactor;

    @Column
    private String indexing;

    @Column
    private String WoSCathegory;

    @Column
    private String quartil;
    //    @ManyToOne
    //    private JournalRank journalRank;
    @Column(nullable = true)
//    @Pattern(regexp = "[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9xX]%",
//            message = "xxxx-xxxx")
    private String ISSN;

    @Column(nullable = true)
//    @Pattern(regexp = "[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9xX]%",
//            message = "xxxx-xxxx")
    private String eISSN;



    @OneToMany(mappedBy = "journal")
    private Collection<Paper> papers = new ArrayList<>();

    public Journal() {
    }

    public Journal(String title, float impactFactor, String indexing, String woSCathegory, String quartil, String ISSN, String eISSN) {
        this.title = title;
        this.impactFactor = impactFactor;
        this.indexing = indexing;
        WoSCathegory = woSCathegory;
        this.quartil = quartil;
        this.ISSN = ISSN;
        this.eISSN = eISSN;
    }

    //<editor-fold desc="Setter/Getters">
    public String getISSN() {
        return ISSN;
    }

    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }

    public String geteISSN() {
        return eISSN;
    }

    public void seteISSN(String eISSN) {
        this.eISSN = eISSN;
    }

    public String getIndexing() {
        return indexing;
    }

    public void setIndexing(String indexing) {
        this.indexing = indexing;
    }

    public String getWoSCathegory() {
        return WoSCathegory;
    }

    public void setWoSCathegory(String woSCathegory) {
        WoSCathegory = woSCathegory;
    }

    public String getQuartil() {
        return quartil;
    }

    public void setQuartil(String quartil) {
        this.quartil = quartil;
    }

    public float getImpactFactor() {
        return impactFactor;
    }

    public void setImpactFactor(float impactFactor) {
        this.impactFactor = impactFactor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<Paper> getPapers() {
        return papers;
    }

    public void setPapers(Collection<Paper> papers) {
        this.papers = papers;
    }
    //</editor-fold>


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journal journal = (Journal) o;
        return title.equals(journal.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}


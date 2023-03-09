package ro.dorobantiu.gradis.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "FACULTIES")
public class Faculty implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true, length = 36)
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "faculty")
    private Collection<Department> departments = new ArrayList<>();

    public Faculty() {
    }

    public Faculty(String name) {
        this.name = name;
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

    public Collection<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Collection<Department> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", departments=" + departments +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Faculty))
            return false;

         return name.equals(((Faculty) obj).name);
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }
}

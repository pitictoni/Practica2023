package ro.dorobantiu.gradis.entities;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "GRADIS_USERS")
public class User implements Serializable {

    @Id
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 100)
    private String password;

    @Column
    private boolean enabled;

    @OneToMany(mappedBy = "email")
    private Collection<Authority> authorities = new ArrayList<>();

    public User() {
    }

    public User(String email, String password, boolean enabled) {
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<Authority> authorities) {
        this.authorities = authorities;
    }
}

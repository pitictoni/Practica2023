package ro.dorobantiu.gradis.entities;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@IdClass(AuthorityId.class)
@Table(name="GRADIS_AUTHORITIES")
public class Authority implements Serializable {

    @Id
    @Column(nullable = false, length = 100)
    private String email;

    @Id
    @Column(nullable = false, length = 100)
    private String authority;

    protected Authority() {
    }

    public Authority(String email, String authority) {
        this.email = email;
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

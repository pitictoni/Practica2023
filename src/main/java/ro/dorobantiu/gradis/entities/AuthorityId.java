package ro.dorobantiu.gradis.entities;

import java.io.Serializable;
import java.util.Objects;

public class AuthorityId implements Serializable {
    String email;
    String authority;

    public AuthorityId() {
    }

    public AuthorityId(String email, String authority) {
        this.email = email;
        this.authority = authority;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorityId that = (AuthorityId) o;
        return Objects.equals(email, that.email) && Objects.equals(authority, that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, authority);
    }
}

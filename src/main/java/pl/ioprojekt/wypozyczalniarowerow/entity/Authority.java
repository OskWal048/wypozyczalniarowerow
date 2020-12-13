package pl.ioprojekt.wypozyczalniarowerow.entity;

import org.springframework.security.core.GrantedAuthority;
import pl.ioprojekt.wypozyczalniarowerow.helperclasses.AuthorityId;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@IdClass(AuthorityId.class)
public class Authority implements GrantedAuthority {

    @Id
    @Column(name = "username")
    private String username;

    @Id
    @Column(name = "authority")
    private String authority;

    public String getUsername() {
        return username;
    }

    public Authority setUsername(String username) {
        this.username = username;
        return this;
    }

    public Authority setAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}

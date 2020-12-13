package pl.ioprojekt.wypozyczalniarowerow.helperclasses;

import java.io.Serializable;

public class AuthorityId implements Serializable {

    private String username;

    private String authority;

    public AuthorityId() {
    }

    public AuthorityId(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }
}

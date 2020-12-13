package pl.ioprojekt.wypozyczalniarowerow.entity;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msg_id")
    private int id;

    @Column()
    private String email;

    @Column(name = "message")
    private String messageText;

    public ContactMessage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}

package pl.ioprojekt.wypozyczalniarowerow.service;

import pl.ioprojekt.wypozyczalniarowerow.entity.ContactMessage;

import java.util.List;

public interface ContactMessageService {

    List<ContactMessage> findAll();

    void save(ContactMessage contactMessage);

    void deleteById(int id);
}

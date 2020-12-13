package pl.ioprojekt.wypozyczalniarowerow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ioprojekt.wypozyczalniarowerow.dao.ContactMessageRepository;
import pl.ioprojekt.wypozyczalniarowerow.entity.ContactMessage;

import java.util.List;

@Service
public class ContactMessageServiceImpl implements ContactMessageService {

    ContactMessageRepository contactMessageRepository;

    @Autowired
    public ContactMessageServiceImpl(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    @Override
    public List<ContactMessage> findAll() {
        return contactMessageRepository.findAll();
    }

    @Override
    public void save(ContactMessage contactMessage) {
        contactMessageRepository.save(contactMessage);
    }

    @Override
    public void deleteById(int id) {
        contactMessageRepository.deleteById(id);
    }
}

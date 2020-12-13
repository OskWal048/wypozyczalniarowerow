package pl.ioprojekt.wypozyczalniarowerow.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ioprojekt.wypozyczalniarowerow.entity.ContactMessage;
import pl.ioprojekt.wypozyczalniarowerow.service.ContactMessageService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/mails")
public class ContactMessageRestController {

    ContactMessageService contactMessageService;

    @Autowired
    public ContactMessageRestController(ContactMessageService contactMessageService) {
        this.contactMessageService = contactMessageService;
    }

    @GetMapping()
    public List<ContactMessage> findAll() {
        return contactMessageService.findAll();
    }

    @PostMapping()
    public ContactMessage addMessage(@RequestBody ContactMessage contactMessage) {
        contactMessage.setId(0);
        contactMessageService.save(contactMessage);
        return contactMessage;
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable int id) {
        contactMessageService.deleteById(id);
    }


}

package pl.ioprojekt.wypozyczalniarowerow.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ioprojekt.wypozyczalniarowerow.entity.ContactMessage;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Integer> {
}

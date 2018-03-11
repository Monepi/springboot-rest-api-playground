package za.co.techgallery.restservicedemo.service;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.techgallery.restservicedemo.entity.Person;

public interface PersonService extends JpaRepository<Person, Long> {
}

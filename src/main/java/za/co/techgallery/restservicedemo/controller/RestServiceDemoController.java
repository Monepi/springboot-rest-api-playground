package za.co.techgallery.restservicedemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.techgallery.restservicedemo.entity.Person;
import za.co.techgallery.restservicedemo.service.PersonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class RestServiceDemoController {

    private final PersonService personService;

    public RestServiceDemoController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("/all")
    public ResponseEntity<List> getPersons() {

        List<Person> people = personService.findAll();

        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<?> getPerson(@PathVariable("id") Long id) {
        Optional<Person> person = personService.findById(id);

        if (!person.isPresent()) {
            return new ResponseEntity<>("Requested person could not be found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(person.get(), HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable("id") Long id) {

        try {
            personService.deleteById(id);
            return new ResponseEntity<>("Person deleted.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePerson(@PathVariable("id") Long id, @RequestBody Person person) {
            Optional<Person> person1 = personService.findById(id);

            if (person1.isPresent()) {
                person1.get().setFirstName(person.getFirstName());
                person1.get().setLastName(person.getLastName());
                person1.get().setAge(person.getAge());
                personService.save(person1.get());
                return new ResponseEntity<>("Person updated.", HttpStatus.OK);
            }
            return new ResponseEntity<>("Person to be updated could not be found.", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPerson(@RequestBody Person person) {

        try {
            personService.save(person);
            return new ResponseEntity<>("Person created.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

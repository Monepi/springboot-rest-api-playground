package za.co.techgallery.restservicedemo.helper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import za.co.techgallery.restservicedemo.entity.Person;
import za.co.techgallery.restservicedemo.service.PersonService;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationStartUpRunner implements CommandLineRunner {

    private final PersonService personService;

    public ApplicationStartUpRunner(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Person> people = new ArrayList<>();

        Person p = new Person();

        p.setFirstName("Simon");
        p.setLastName("Mohoalali");
        p.setAge(28);

        Person p1 = new Person();

        p1.setFirstName("Mike");
        p1.setLastName("Mofokeng");
        p1.setAge(12);

        Person p2 = new Person();

        p2.setFirstName("Tsekiso");
        p2.setLastName("Motsamai");
        p2.setAge(17);

        Person p3 = new Person();

        p3.setFirstName("Motsamai");
        p3.setLastName("Mohoalali");
        p3.setAge(39);

        Person p4 = new Person();

        p4.setFirstName("Ntesi");
        p4.setLastName("William");
        p4.setAge(38);

        people.add(p);
        people.add(p1);
        people.add(p2);
        people.add(p3);
        people.add(p4);

        personService.saveAll(people);
    }
}

package za.co.techgallery.restservicedemo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "FirstName")
    @NotNull
    private String firstName;
    @Column(name = "LastName")
    @NotNull
    private String lastName;
    @Column(name = "DOB")
    @NotNull
    private int age;

}

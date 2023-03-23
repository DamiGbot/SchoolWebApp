package com.ravenschool.web_example_1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "class")
public class EazyClass extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @SequenceGenerator(name = "native", sequenceName = "native")
    private int classId;

    @NotBlank(message="Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;

    @OneToMany(mappedBy = "eazyClass", cascade = {CascadeType.PERSIST})
    private Set<Person> persons;
}

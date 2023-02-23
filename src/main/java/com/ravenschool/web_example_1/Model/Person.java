package com.ravenschool.web_example_1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "person")
public class Person extends BasePerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int person_id;

    @NotBlank
    private String confirmEmail;

    @NotBlank
    private String pwd;

    @NotBlank
    private String confirmPwd;
}

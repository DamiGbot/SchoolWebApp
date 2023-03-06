package com.ravenschool.web_example_1.Model;

import com.ravenschool.web_example_1.Annotation.FieldsValueMatch;
import com.ravenschool.web_example_1.Annotation.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "person")
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "pwd",
                fieldMatch = "confirmPwd",
                message = "Passwords do not match"
        ),
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "Emails do not match"
        )
})
public class Person extends BasePerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int personId;

    @NotBlank(message="Confirm Email must not be blank")
    @Email(message = "Please provide a valid Confirm email address" )
    @Transient
    private String confirmEmail;

    @NotBlank(message="Password must not be blank")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    @PasswordValidator
    private String pwd;

    @NotBlank(message="Confirm password must not be blank")
    @Size(min = 5, message = "Confirm Password must be at least 5 characters long")
    @Transient
    private String confirmPwd;

    // region foreignKey
    @OneToOne(cascade = {CascadeType.ALL}, targetEntity = Address.class)
    @JoinColumn(name = "addressId", referencedColumnName = "addressId")
    private Address address;

    @OneToOne(cascade = CascadeType.PERSIST) // targetEntity is an optional Attribute
    @JoinColumn(name = "roleId", referencedColumnName = "roleId", nullable = false)
    private Role role;
    // endregion
}

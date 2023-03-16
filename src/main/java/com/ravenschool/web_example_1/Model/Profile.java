package com.ravenschool.web_example_1.Model;

import com.ravenschool.web_example_1.Model.DTO.BasePersonDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Profile extends BasePersonDTO {

    @NotBlank(message="Address1 must not be blank")
    @Size(min=5, message="Address1 must be at least 5 characters long")
    private String address1;

    private String address2;

    @NotBlank(message="City must not be blank")
    @Size(min=5, message="City must be at least 5 characters long")
    private String city;

    @NotBlank(message="State must not be blank")
    @Size(min=5, message="State must be at least 5 characters long")
    private String state;

    @NotBlank(message="Zip Code must not be blank")
    @Pattern(regexp="(^$|[0-9]{5})",message = "Zip Code must be 5 digits")
    private String zipCode;
}

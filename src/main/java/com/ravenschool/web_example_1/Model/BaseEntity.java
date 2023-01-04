package com.ravenschool.web_example_1.Model;

import lombok.Data;

import java.time.LocalTime;

@Data
public class BaseEntity {

    private String created_by;
    private LocalTime created_at;
    private String updated_by;
    private LocalTime updated_at;

}

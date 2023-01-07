package com.ravenschool.web_example_1.Model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {

    private String created_by;
    private LocalDateTime created_at;
    private String updated_by;
    private LocalDateTime updated_at;

}

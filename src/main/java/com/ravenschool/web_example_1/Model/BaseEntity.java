package com.ravenschool.web_example_1.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"updated_at", "updated_by", "created_at", "created_by"})
public class BaseEntity {

    @CreatedBy
    @Column(updatable = false)
    private String created_by; // while inserting only

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created_at; // while inserting only

    @LastModifiedBy
    @Column(insertable = false)
    private String updated_by; // while updating only

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updated_at; // while updating only

}

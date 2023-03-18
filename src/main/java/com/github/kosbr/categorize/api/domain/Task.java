package com.github.kosbr.categorize.api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Data;

@Entity
@Table(name = "tasks")
@Data
public class Task {

    @Id
    private UUID id;

    private String url;

    private Integer status;

    private String categories;

    private String error;

    private OffsetDateTime created;

    private OffsetDateTime updated;

}

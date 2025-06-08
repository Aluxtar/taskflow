package com.taskflow.taskflow.model;

import jakarta.persistence.*;
import lombok.*;

@Entity //Defines that data will be stored in tables
@Data //Defines getters and setters
@NoArgsConstructor //Creates the empty constructor
@AllArgsConstructor //Creates constructor with all arguments

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private boolean completed;
}

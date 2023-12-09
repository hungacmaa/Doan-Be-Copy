package com.be_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    private String type;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDate date = LocalDate.now();
    @ManyToOne
    private Account sender;
    @ManyToOne
    private Account receiver;
}
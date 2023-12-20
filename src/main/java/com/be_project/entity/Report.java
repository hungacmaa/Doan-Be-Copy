package com.be_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Report {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "TEXT")
    private String reason;
    private String status = "Chờ phê duyệt";
    private LocalDate createdAt = LocalDate.now();
    @ManyToOne
    private Account account;
    @ManyToOne
    private Post post;
}
package com.be_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Report {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    private String content;
    @ManyToOne
    private Account account;
    @ManyToOne
    private Post post;
}
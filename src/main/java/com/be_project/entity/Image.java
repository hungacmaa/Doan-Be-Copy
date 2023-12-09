package com.be_project.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "TEXT")
    private String url;
    @ManyToOne
    private Post post;
}

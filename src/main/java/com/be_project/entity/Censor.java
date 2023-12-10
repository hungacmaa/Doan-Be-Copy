package com.be_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Censor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    Post post;

    private LocalDate createdAt;
    private LocalDate modifiedAt;
    private String status;
    private String result;

    @ManyToOne
    private Account reviewer;

    @Column(columnDefinition = "TEXT")
    private String reason;


}

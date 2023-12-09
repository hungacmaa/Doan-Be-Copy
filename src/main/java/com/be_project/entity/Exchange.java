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
public class Exchange {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    private String status = "Chờ xác nhận";
    private String reason;
    @ManyToOne
    private Post postSell;
    @ManyToOne
    private Post postBuy;
    private LocalDate createdAt = LocalDate.now();
}
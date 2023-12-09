package com.be_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSchedule {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    private Date date;
    @Column(columnDefinition = "TEXT")
    private String address;
    @OneToOne
    private Exchange exchange;
}


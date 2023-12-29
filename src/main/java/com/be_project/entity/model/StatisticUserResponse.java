package com.be_project.entity.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticUserResponse {

    private String label;
    private Long amount;
}

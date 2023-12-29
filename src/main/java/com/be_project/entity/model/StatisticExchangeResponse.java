package com.be_project.entity.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticExchangeResponse {
    private String status;
    private Long amount;
}

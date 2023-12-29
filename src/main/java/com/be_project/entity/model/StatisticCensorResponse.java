package com.be_project.entity.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticCensorResponse {
    private long acceptByAI;
    private long rejectByAI;
    private long acceptByAdmin;
    private long rejectByAdmin;
    private long total;
    private long censored;

}

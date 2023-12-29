package com.be_project.entity.model;

import com.be_project.entity.CategoryProduct;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticPostResponse {
    private CategoryProduct categoryProduct;
    private Long amount;
}

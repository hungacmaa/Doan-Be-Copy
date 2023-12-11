package com.be_project.entity.model;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PredictResponse {
    private List<Predict> data;
    private String message;
    private int status;
}

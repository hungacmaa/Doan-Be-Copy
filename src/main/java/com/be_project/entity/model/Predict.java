package com.be_project.entity.model;

import lombok.*;

import java.util.List;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Predict {
    private List<Detection> boxes;
    private String url;
}

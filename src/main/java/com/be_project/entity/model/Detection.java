package com.be_project.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Detection {
    private Box box;
    @JsonProperty("class")
    private int lop;
    private float confidence;
    private String name;

}

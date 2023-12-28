package com.be_project.entity.dto;

import com.be_project.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CensorDto {
    private String reason;
    private Account account;
}

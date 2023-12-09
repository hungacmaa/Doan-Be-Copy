package com.be_project.entity.dto;

import com.be_project.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountAndMessageDto {
    private Account account;
    private long countUnreadMessage;
}

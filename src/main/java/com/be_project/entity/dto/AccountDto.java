package com.be_project.entity.dto;

import com.be_project.entity.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDto {
    private long id;
    private String username;
    private String avatar;
    private double distance;
    public AccountDto(Account account){
        this.id = account.getId();
        this.username = account.getUsername();
        this.avatar = account.getAvatar();
    }
}

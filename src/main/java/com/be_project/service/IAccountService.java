package com.be_project.service;

import com.be_project.entity.Account;
import com.be_project.entity.dto.AccountAndMessageDto;
import com.be_project.entity.dto.AccountDto;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public interface IAccountService extends UserDetailsService {
    Account getAccountLogin(String username, String password);

    boolean register(Account account);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    Page<Account> findAllByStatusContainingAndUsernameContaining(String status, String username, int page, int size);

    void blockAccount(Long accountId);

    void unBlockAccount(Long accountId);

    List<AccountAndMessageDto> listUserAndUnreadMessage(long userId);

    List<Account> findAllByUsernameContainsAndNotAccountLogin(String username, long accountId);

    Account getById(long accountId);

    Account editAccount(long accountId, Account accountEdit);

    void changePassword(long accountId, String password);

    boolean checkPassword(long accountId, String password);
    Account getAccountByUsername(String username);
    Account changeLocation(long accountId, Account account);
    List<AccountDto> getAccountsAroundHere(Account account);
    boolean checkUsername(String username);
}

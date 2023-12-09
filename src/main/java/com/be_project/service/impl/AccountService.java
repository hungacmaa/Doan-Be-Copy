package com.be_project.service.impl;

import com.be_project.entity.Account;
import com.be_project.entity.Role;
import com.be_project.entity.dto.AccountAndMessageDto;
import com.be_project.entity.dto.AccountDto;
import com.be_project.repository.*;
import com.be_project.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepo accountRepo;
    @Autowired
    private IMessageRepo messageRepo;
    @Autowired
    private IPostRepo postRepo;
    @Autowired
    private IExchangeRepo exchangeRepo;
    @Autowired
    private IRoleRepo roleRepo;

    @Override
    public Account getAccountLogin(String username, String password) {
        return accountRepo.getAccountLogin(username, password);
    }

    @Override
    public boolean register(Account account) {
        Account account1 = accountRepo.findByUsername(account.getUsername());
        if (account1 != null){
            return false;
        }
        Role role = roleRepo.findByName("ROLE_USER");
        account.setRole(role);
        account.setStatus("Đang hoạt động");
        account.setAvatar("https://cdn-icons-png.flaticon.com/512/3177/3177440.png");
        accountRepo.save(account);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.getAccountByUsername(username);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add((GrantedAuthority) account.getRole());
        return new User(account.getUsername(), account.getPassword(), roles);
    }

    @Override
    public Page<Account> findAllByStatusContainingAndUsernameContaining(String status, String username, int page, int size) {
        return accountRepo.findAllByStatusContainingAndUsernameContaining(status, username, PageRequest.of(page, size));
    }

    @Override
    public void blockAccount(Long accountId) {
        Account account = accountRepo.findById(accountId).get();
        if (account != null && account.getStatus().equals("Đang hoạt động")){
            exchangeRepo.removeAllByAccountId(accountId);
            account.setStatus("Bị khóa");
            accountRepo.save(account);
            postRepo.changeStatusPostByAccountId(accountId, "Vô hiệu hóa");
        }
    }

    @Override
    public void unBlockAccount(Long accountId) {
        Account account = accountRepo.findById(accountId).get();
        if (account != null && account.getStatus().equals("Bị khóa")){
            account.setStatus("Đang hoạt động");
            accountRepo.save(account);
            postRepo.changeStatusPostByAccountId(accountId, "Chưa trao đổi");
        }
    }

    @Override
    public List<AccountAndMessageDto> listUserAndUnreadMessage(long userId) {
        List<Account> accounts = accountRepo.listUserMessage(userId);
        List<AccountAndMessageDto> list = new ArrayList<>();
        for (Account account : accounts){
            long count = messageRepo.countUnreadMessagesByAccountLoginIdAndSenderId(userId, account.getId());
            list.add(new AccountAndMessageDto(account, count));
        }
        return list;
    }

    @Override
    public List<Account> findAllByUsernameContainsAndNotAccountLogin(String username, long accountId) {
        return accountRepo.findAllByUsernameContainsAndNotAccountLogin(username, accountId);
    }

    @Override
    public Account getById(long accountId) {
        Account account = accountRepo.findById(accountId).get();
        account.setPassword(null);
        return account;
    }
    @Override
    public Account getAccountByUsername(String username) {
        return accountRepo.findByUsername(username);
    }

    @Override
    public Account changeLocation(long accountId, Account account) {
        Account accountDB = accountRepo.findById(accountId).get();
        accountDB.setLatitude(account.getLatitude());
        accountDB.setLongitude(account.getLongitude());
        accountRepo.save(accountDB);
        accountDB.setPassword(null);
        return accountDB;
    }

    @Override
    public List<AccountDto> getAccountsAroundHere(Account account) {
        List<Account> accounts = accountRepo.findAll();
        List<AccountDto> accountDtoList = new ArrayList<>();
        for (Account a : accounts){
            if (a.getId() != account.getId() && !a.getLatitude().equals("0") && !a.getLongitude().equals("0")){
                AccountDto accountDto = new AccountDto(a);
                accountDto.setDistance(calculateDistance(Double.parseDouble(account.getLatitude()), Double.parseDouble(account.getLongitude()), Double.parseDouble(a.getLatitude()), Double.parseDouble(a.getLongitude())));
                accountDtoList.add(accountDto);
            }
        }
        return accountDtoList;
    }

    @Override
    public boolean checkUsername(String username) {
        return accountRepo.findByUsername(username) != null;
    }

    @Override
    public Account editAccount(long accountId, Account accountEdit) {
        Account account = accountRepo.findById(accountId).get();
        account.setName(accountEdit.getName());
        account.setPhone(accountEdit.getPhone());
        account.setAddress(accountEdit.getAddress());
        account.setAvatar(accountEdit.getAvatar());
        return accountRepo.save(account);
    }

    @Override
    public void changePassword(long accountId, String password) {
        Account account = accountRepo.findById(accountId).get();
        account.setPassword(password);
        accountRepo.save(account);
    }

    @Override
    public boolean checkPassword(long accountId, String password) {
        Account account = accountRepo.findById(accountId).get();
        return account.getPassword().equals(password);
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Bán kính trái đất ở đơn vị kilômét

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = R * c; // Khoảng cách ở đơn vị kilômét
        return Math.round(distance * 100) / 100.00;
    }
}

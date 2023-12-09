package com.be_project.controller;

import com.be_project.entity.dto.AccountToken;
import com.be_project.service.JwtService;
import com.be_project.service.IAccountService;
import com.be_project.entity.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class Login_Register_Controller {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    IAccountService accountService;


    @PostMapping("/login")
    public ResponseEntity<?> getLogin(@RequestBody Account account) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            account = accountService.getAccountLogin(account.getUsername(), account.getPassword());
            String token = jwtService.createToken(authentication);
            if (account.getStatus().equals("Bị khóa")) {
                String errorMessage = "Tài khoản đã bị khóa!";
                return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
            }
            AccountToken accountToken = new AccountToken(account.getId(), account.getUsername(), null, account.getName(), account.getStatus(),
                    account.getAvatar(), account.getPhone(), account.getAddress(), account.getRole(), account.getLatitude(), account.getLongitude(), token);
            return new ResponseEntity<>(accountToken, HttpStatus.OK);
        } catch (AuthenticationException e) {
            String errorMessage = "Tài khoản hoặc mật khẩu không chính xác!";
            return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account) {
        if (accountService.register(account)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Tài khoản đã tồn tại!", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register/check-username")
    public boolean checkUsername(@RequestBody Account account){
        try {
            return accountService.checkUsername(account.getUsername());
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}

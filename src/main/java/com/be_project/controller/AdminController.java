package com.be_project.controller;

import com.be_project.entity.Account;
import com.be_project.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private IAccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<?> getAll(@RequestParam(name = "status", defaultValue = "") String status,
                                    @RequestParam(name = "username", defaultValue = "") String username,
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            return ResponseEntity.ok(accountService.findAllByStatusContainingAndUsernameContaining(status, username, page, size));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/accounts/block/{accountId}")
    public ResponseEntity<?> blockAccount(@PathVariable Long accountId) {
        try {
            accountService.blockAccount(accountId);
            return ResponseEntity.ok("Block account successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/accounts/unblock/{accountId}")
    public ResponseEntity<?> unBlockAccount(@PathVariable Long accountId) {
        try {
            accountService.unBlockAccount(accountId);
            return ResponseEntity.ok("Unblock account successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

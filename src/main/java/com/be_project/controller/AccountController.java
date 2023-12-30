package com.be_project.controller;

import com.be_project.entity.Account;
import com.be_project.entity.Image;
import com.be_project.entity.Post;
import com.be_project.entity.Report;
import com.be_project.entity.dto.FilterDto;
import com.be_project.entity.dto.ListURLDto;
import com.be_project.entity.dto.PostDto;
import com.be_project.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IPostService postService;
    @Autowired
    private IExchangeService exchangeService;
    @Autowired
    private IPostPinService postPinService;
    @Autowired
    private ICensorService censorService;
    @Autowired
    private IReportService reportService;


    @GetMapping("/{accountId}")
    public ResponseEntity<?> getById(@PathVariable long accountId) {
        try {
            return ResponseEntity.ok(accountService.getById(accountId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/{accountId}/posts")
    public ResponseEntity<?> getAllPostsByAccountId(@PathVariable Long accountId,
                                                    @RequestBody FilterDto filterDto,
                                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                                    @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            return ResponseEntity.ok(postService.getAllByAccountId(accountId, filterDto, page, size));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{accountId}/messages")
    public ResponseEntity<?> listUserAndUnreadMessage(@PathVariable long accountId) {
        try {
            return ResponseEntity.ok(accountService.listUserAndUnreadMessage(accountId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{accountId}/messages/search")
    public ResponseEntity<?> findAllByUsernameContainsAndNotAccountLogin(@PathVariable long accountId,
                                                                         @RequestParam("username") String username) {
        try {
            return ResponseEntity.ok(accountService.findAllByUsernameContainsAndNotAccountLogin(username, accountId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<?> editAccount(@PathVariable int accountId, @RequestBody Account accountEdit) {
        try {
            return ResponseEntity.ok(accountService.editAccount(accountId, accountEdit));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{accountId}/change-password")
    public ResponseEntity<?> changePassword(@RequestBody Account accountEdit, @PathVariable long accountId) {
        try {
            accountService.changePassword(accountId, accountEdit.getPassword());
            return ResponseEntity.ok("Change password successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/{accountId}/check-password")
    public boolean checkPassword(@PathVariable long accountId, @RequestBody Account accountEdit) {
        return accountService.checkPassword(accountId, accountEdit.getPassword());
    }

    @PostMapping("{accountId}/exchanges")
    public ResponseEntity<?> getAllExchangesByAccount(@PathVariable long accountId,
                                                      @RequestBody FilterDto filterDto,
                                                      @RequestParam(name = "page", defaultValue = "0") int page,
                                                      @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            return ResponseEntity.ok(exchangeService.getAllByAccountId(accountId, filterDto.getStatus(), filterDto.getPostSell(), filterDto.getPostBuy(), filterDto.getStartDate(), filterDto.getEndDate(), page, size));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("{accountSell}/{accountBuy}/post-pin")
    public ResponseEntity<?> getPostPin(@PathVariable long accountSell,
                                        @PathVariable long accountBuy) {
        try {
            return ResponseEntity.ok(postPinService.findByAccountSellAndAccountBuy(accountSell, accountBuy));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody PostDto postDto) {

        try {
            Post post = postService.createPost(postDto);
            ListURLDto listURLDto = new ListURLDto(new ArrayList<String>());
            listURLDto.getImg_urls().add(postDto.getAvatar());
            for(Image image:postDto.getImages()){
                listURLDto.getImg_urls().add(image.getUrl());
            }
            // chạy bất đồng bộ tạo lượt kiểm duyệt
            censorService.createCensor(post, listURLDto);

            return ResponseEntity.ok(post);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PutMapping("/posts/{postId}")
    public ResponseEntity<?> editPost(@RequestBody PostDto postDto, @PathVariable long postId) {
        try {
            return ResponseEntity.ok(postService.editPost(postId, postDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{accountId}/location")
    public ResponseEntity<?> changeLocation(@RequestBody Account account, @PathVariable long accountId) {
        try {
            return ResponseEntity.ok(accountService.changeLocation(accountId, account));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/search-around-here")
    public ResponseEntity<?> searchAroundHere(@RequestBody Account account) {
        try {
            return ResponseEntity.ok(accountService.getAccountsAroundHere(account));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/report-post")
    public ResponseEntity<?> reportPost(@RequestBody Report report) {
        try {
            return ResponseEntity.ok(reportService.saveReport(report));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/report-post/{accountId}")
    public ResponseEntity<?> getAllReportsByAccountId(@RequestBody FilterDto filterDto,
                                                      @PathVariable long accountId,
                                                      @RequestParam(name = "page", defaultValue = "0") int page,
                                                      @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            return ResponseEntity.ok(reportService.getAllReportsByAccountId(accountId, filterDto, page, size));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/report-post/{postId}")
    public ResponseEntity<?> getAllReportsByAccountId(@PathVariable long postId) {
        try {
            return ResponseEntity.ok(reportService.getReportByPostId(postId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

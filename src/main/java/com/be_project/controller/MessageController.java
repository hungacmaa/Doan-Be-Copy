package com.be_project.controller;

import com.be_project.entity.Message;
import com.be_project.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private IMessageService messageService;

    @GetMapping("/{senderId}/{receiverId}")
    public ResponseEntity<?> getAllMessagesBySenderIdAndReceiverId(@PathVariable long senderId,
                                                                   @PathVariable long receiverId) {
        try {
            return ResponseEntity.ok(messageService.findAllBySenderIdAndReceiverId(senderId, receiverId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> saveMessage(@RequestBody Message message) {
        try {
            return ResponseEntity.ok(messageService.save(message));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/count/{receiverId}")
    public long countUnreadMessagesByReceiverId(@PathVariable long receiverId) {
        try {
            return messageService.countUnreadMessagesByReceiverId(receiverId);
        } catch (Exception e) {
            return 0;
        }
    }

    @GetMapping("/count/{accountLoginId}/{senderId}")
    public long countUnreadMessagesByAccountLoginIdAndSenderId(@PathVariable long accountLoginId,
                                                              @PathVariable long senderId) {
        try {
            return messageService.countUnreadMessagesByAccountLoginIdAndSenderId(accountLoginId, senderId);
        } catch (Exception e) {
            return 0;
        }
    }

    @PutMapping("/change-status/{accountId}/{senderId}")
    public ResponseEntity<?> changeStatusMessage(@PathVariable long accountId, @PathVariable long senderId) {
        try {
            messageService.changeStatusMessage(accountId, senderId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

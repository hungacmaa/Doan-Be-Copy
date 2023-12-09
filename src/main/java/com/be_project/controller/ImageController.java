package com.be_project.controller;

import com.be_project.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    @Autowired
    private IImageService imageService;

    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> getAll(@PathVariable Long postId) {
        try {
            return ResponseEntity.ok(imageService.findAllByPostId(postId));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

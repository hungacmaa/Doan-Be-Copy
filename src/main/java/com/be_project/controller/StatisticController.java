package com.be_project.controller;

import com.be_project.service.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/statistics")
public class StatisticController {
    @Autowired
    private IStatisticService statisticService;

    @GetMapping("/total-post")
    public ResponseEntity<?> getTotalPost() {
        try {
            return ResponseEntity.ok(statisticService.getTotalPost());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/post-by-status")
    public ResponseEntity<?> getTotalPostByStatus(@RequestParam("postStatus") String postStatus) {
        try {
            return ResponseEntity.ok(statisticService.getTotalPostByStatus(postStatus));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/total-censor")
    public ResponseEntity<?> getTotalCensor() {
        try {
            return ResponseEntity.ok(statisticService.getTotalCensor());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/censor-by-status")
    public ResponseEntity<?> getTotalCensorByStatus(@RequestParam("censorStatus") String censorStatus) {
        try {
            return ResponseEntity.ok(statisticService.getTotalCensorByStatus(censorStatus));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/censor-by-AI")
    public ResponseEntity<?> getTotalCensorByAI() {
        try {
            return ResponseEntity.ok(statisticService.getTotalCensorByAI());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/censor-by-admin")
    public ResponseEntity<?> getTotalCensorByAdmin() {
        try {
            return ResponseEntity.ok(statisticService.getTotalCensorByAdmin());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/post-by-category-product") ResponseEntity<?> getStatisticPostByCategoryProduct(){
        try{
            return ResponseEntity.ok(statisticService.getStatisticPost());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/censor")
    public ResponseEntity<?> getStatisticCensor(){
        try{
            return ResponseEntity.ok(statisticService.getStatisticCensor());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/exchange")
    public ResponseEntity<?> getStatisticExchange(){
        try{
            return ResponseEntity.ok(statisticService.getStatisticExchange());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/total-exchange")
    public ResponseEntity<?> getTotalExchange(){
        try{
            return ResponseEntity.ok(statisticService.getTotalExchange());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getStatisticUser(){
        try{
            return ResponseEntity.ok(statisticService.getStatisticUser());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

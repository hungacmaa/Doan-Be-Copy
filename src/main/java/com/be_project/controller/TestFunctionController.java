package com.be_project.controller;

import com.be_project.entity.dto.ListURLDto;
import com.be_project.service.impl.MyAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/test")
public class TestFunctionController {

    @Autowired
    private MyAsyncService myAsyncService;

    @GetMapping("/hello")
    public ResponseEntity<?> helloWorld() {
        try {
            myAsyncService.performAsyncTask();
            return ResponseEntity.ok("Async task triggered!");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/helloFlask")
    public ResponseEntity<?> helloFlask() {
        try {

            RestTemplate restTemplate = new RestTemplate();
            String s = restTemplate.getForObject("http://localhost:5000/hello", String.class);

            System.out.println("hehe");

            return ResponseEntity.ok(s);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/predict")
    public ResponseEntity<?> predict(@RequestBody ListURLDto requestBody) {
        try {
            return ResponseEntity.ok(requestBody);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

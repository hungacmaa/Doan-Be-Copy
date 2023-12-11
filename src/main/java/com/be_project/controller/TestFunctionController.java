package com.be_project.controller;

import com.be_project.entity.dto.ListURLDto;
import com.be_project.entity.dto.PostDto;
import com.be_project.service.impl.MyAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

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
            System.out.println("1");
            myAsyncService.performAsyncTask();
            System.out.println("2");
            return ResponseEntity.ok("Async task triggered!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/helloFlask")
    public ResponseEntity<?> helloFlask() {
        try {

            RestTemplate restTemplate = new RestTemplate();
            String s = restTemplate.getForObject("http://localhost:5000/hello", String.class);
//            System.out.println(s);
            System.out.println("hehe");

            return ResponseEntity.ok(s);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/predict")
    public ResponseEntity<?> predict(@RequestBody ListURLDto listURLDto) {
        try {
            // call api
            myAsyncService.fetchPredictData(listURLDto);
            System.out.println("hehe");
            // return result
            return ResponseEntity.ok(listURLDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

//    @PostMapping("/create-post")
//    public ResponseEntity<?> createPost(@RequestBody PostDto postDto) {
//        try {
//            return ResponseEntity.ok("created post");
//        } catch (Exception e) {
//            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }

}

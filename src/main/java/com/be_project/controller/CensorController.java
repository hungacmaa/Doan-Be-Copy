package com.be_project.controller;

import com.be_project.entity.Censor;
import com.be_project.entity.dto.CensorDto;
import com.be_project.service.ICensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/censors")
public class CensorController {

    @Autowired
    private ICensorService censorService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(censorService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{censorId}")
    public ResponseEntity<?> getCensor(@PathVariable long censorId) {
        try {
            return ResponseEntity.ok(censorService.getCensorById(censorId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createCensor(@RequestBody CensorDto censorDto){
        try {
            return ResponseEntity.ok("created censor");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/accept/{censorId}")
    public ResponseEntity<?> acceptCensor(@RequestBody CensorDto censorDto, @PathVariable long censorId){
        try {
            return ResponseEntity.ok(censorService.acceptCensor(censorId, censorDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/reject/{censorId}")
    public ResponseEntity<?> rejectCensor(@RequestBody CensorDto censorDto, @PathVariable long censorId){
        try {
            return ResponseEntity.ok(censorService.rejectCensor(censorId, censorDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}

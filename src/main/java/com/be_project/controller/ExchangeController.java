package com.be_project.controller;

import com.be_project.entity.Exchange;
import com.be_project.service.IExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exchanges")
public class ExchangeController {
    @Autowired
    private IExchangeService exchangeService;

    @PostMapping
    public ResponseEntity<?> createExchange(@RequestBody Exchange exchange) {
        try {
            return ResponseEntity.ok(exchangeService.saveExchange(exchange));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/success")
    public ResponseEntity<?> confirmExchange(@RequestBody Exchange exchange) {
        try {
            return ResponseEntity.ok(exchangeService.confirmExchange(exchange));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/fail")
    public ResponseEntity<?> denyExchange(@RequestBody Exchange exchange) {
        try {
            return ResponseEntity.ok(exchangeService.denyExchange(exchange));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

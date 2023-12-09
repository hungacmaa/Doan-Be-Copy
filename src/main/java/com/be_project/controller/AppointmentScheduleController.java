package com.be_project.controller;

import com.be_project.entity.AppointmentSchedule;
import com.be_project.service.IAppointmentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
public class AppointmentScheduleController {
    @Autowired
    private IAppointmentScheduleService appointmentScheduleService;

    @GetMapping("/exchanges/{exchangeId}")
    public ResponseEntity<?> getByExchangeId(@PathVariable long exchangeId) {
        try {
            return ResponseEntity.ok(appointmentScheduleService.getByExchangeId(exchangeId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createSchedule(@RequestBody AppointmentSchedule appointmentSchedule) {
        try {
            return ResponseEntity.ok(appointmentScheduleService.createSchedule(appointmentSchedule));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<?> getAllSchedulesByAccountId(@PathVariable long accountId) {
        try {
            return ResponseEntity.ok(appointmentScheduleService.getByAccountId(accountId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

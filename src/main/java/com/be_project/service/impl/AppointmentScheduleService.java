package com.be_project.service.impl;

import com.be_project.entity.AppointmentSchedule;
import com.be_project.entity.Exchange;
import com.be_project.entity.Post;
import com.be_project.entity.PostPin;
import com.be_project.repository.IAppointmentScheduleRepo;
import com.be_project.repository.IExchangeRepo;
import com.be_project.repository.IPostPinRepo;
import com.be_project.repository.IPostRepo;
import com.be_project.service.IAppointmentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentScheduleService implements IAppointmentScheduleService {
    @Autowired
    private IAppointmentScheduleRepo appointmentScheduleRepo;
    @Autowired
    private IExchangeRepo exchangeRepo;
    @Autowired
    private IPostRepo postRepo;
    @Autowired
    private IPostPinRepo postPinRepo;
    @Override
    public AppointmentSchedule getByExchangeId(long exchangeId) {
        return appointmentScheduleRepo.findByExchangeId(exchangeId);
    }

    @Override
    public AppointmentSchedule createSchedule(AppointmentSchedule appointmentSchedule) {
        Exchange exchange = exchangeRepo.findById(appointmentSchedule.getExchange().getId()).get();
        exchange.setStatus("Chờ trao đổi");
        Post postSell = exchange.getPostSell();
        postSell.setStatus("Chờ trao đổi");
        Post postBuy = exchange.getPostBuy();
        postBuy.setStatus("Chờ trao đổi");
        postPinRepo.save(new PostPin(exchange));
        postRepo.save(postSell);
        postRepo.save(postBuy);
        exchangeRepo.save(exchange);
        return appointmentScheduleRepo.save(appointmentSchedule);
    }

    @Override
    public List<AppointmentSchedule> getByAccountId(long accountId) {
        return appointmentScheduleRepo.getByAccountId(accountId);
    }
}

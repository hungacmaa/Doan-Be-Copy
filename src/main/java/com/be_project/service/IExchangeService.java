package com.be_project.service;

import com.be_project.entity.Exchange;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface IExchangeService {
    Exchange saveExchange(Exchange exchange);
    Exchange confirmExchange(Exchange exchange);
    Exchange denyExchange(Exchange exchange);
    Page<Exchange> getAllByAccountId(long accountId, String status, String postSell, String postBuy, LocalDate startDate, LocalDate endDate, int page, int size);
}

package com.be_project.service.impl;

import com.be_project.entity.PostPin;
import com.be_project.repository.IPostPinRepo;
import com.be_project.service.IPostPinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostPinService implements IPostPinService {
    @Autowired
    private IPostPinRepo postPinRepo;
    @Override
    public PostPin findByAccountSellAndAccountBuy(long accountSell, long accountBuy) {
        return postPinRepo.findByAccountSellAndAccountBuy(accountSell, accountBuy).get(0);
    }
}

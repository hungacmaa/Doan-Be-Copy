package com.be_project.service;

import com.be_project.entity.PostPin;

public interface IPostPinService {
    PostPin findByAccountSellAndAccountBuy(long accountSell, long accountBuy);
}

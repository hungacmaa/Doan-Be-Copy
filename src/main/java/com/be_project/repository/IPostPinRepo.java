package com.be_project.repository;

import com.be_project.entity.PostPin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPostPinRepo extends JpaRepository<PostPin, Long> {
    @Query("SELECT p FROM PostPin p WHERE (p.exchange.postSell.account.id = :accountSell " +
            "AND p.exchange.postBuy.account.id = :accountBuy) " +
            "OR (p.exchange.postSell.account.id = :accountBuy " +
            "AND p.exchange.postBuy.account.id = :accountSell) " +
            "ORDER BY p.id DESC")
    List<PostPin> findByAccountSellAndAccountBuy(@Param("accountSell") long accountSell,
                                                 @Param("accountBuy") long accountBuy);
}

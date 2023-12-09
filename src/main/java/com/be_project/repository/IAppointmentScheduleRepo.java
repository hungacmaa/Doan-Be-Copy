package com.be_project.repository;

import com.be_project.entity.AppointmentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAppointmentScheduleRepo extends JpaRepository<AppointmentSchedule, Long> {
    AppointmentSchedule findByExchangeId(long exchangeId);
    @Query("SELECT a FROM AppointmentSchedule a WHERE a.exchange.postSell.account.id = :accountId " +
            "OR a.exchange.postBuy.account.id = :accountId " +
            "AND a.date > NOW()")
    List<AppointmentSchedule> getByAccountId(@Param("accountId") long accountId);
}

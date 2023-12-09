package com.be_project.service;

import com.be_project.entity.AppointmentSchedule;

import java.util.List;

public interface IAppointmentScheduleService {
    AppointmentSchedule getByExchangeId(long exchangeId);
    AppointmentSchedule createSchedule(AppointmentSchedule appointmentSchedule);
    List<AppointmentSchedule> getByAccountId(long accountId);
}

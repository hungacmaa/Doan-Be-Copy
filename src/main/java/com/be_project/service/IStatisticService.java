package com.be_project.service;

import com.be_project.entity.model.StatisticCensorResponse;
import com.be_project.entity.model.StatisticExchangeResponse;
import com.be_project.entity.model.StatisticPostResponse;
import com.be_project.entity.model.StatisticUserResponse;

import java.util.List;

public interface IStatisticService {
    long getTotalPost();

    long getTotalCensor();

    Long getTotalCensorByStatus(String censorStatus);

    Long getTotalCensorByAI();

    Long getTotalCensorByAdmin();

    Long getTotalPostByStatus(String postStatus);

    List<StatisticPostResponse> getStatisticPost();

    StatisticCensorResponse getStatisticCensor();

    List<StatisticExchangeResponse> getStatisticExchange();

    Long getTotalExchange();

    List<StatisticUserResponse> getStatisticUser();
}

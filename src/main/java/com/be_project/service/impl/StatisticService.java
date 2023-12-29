package com.be_project.service.impl;

import com.be_project.entity.Account;
import com.be_project.entity.CategoryProduct;
import com.be_project.entity.model.StatisticCensorResponse;
import com.be_project.entity.model.StatisticExchangeResponse;
import com.be_project.entity.model.StatisticPostResponse;
import com.be_project.entity.model.StatisticUserResponse;
import com.be_project.repository.*;
import com.be_project.service.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticService implements IStatisticService {
    @Autowired
    private IPostRepo postRepo;
    @Autowired
    private ICensorRepo censorRepo;
    @Autowired
    private IAccountRepo accountRepo;
    @Autowired
    private ICategoryProductRepo categoryProductRepo;
    @Autowired
    private IExchangeRepo exchangeRepo;

    @Override
    public long getTotalPost() {
        return postRepo.count();
    }

    @Override
    public long getTotalCensor() {
        return censorRepo.count();
    }

    @Override
    public Long getTotalCensorByStatus(String censorStatus) {
        return censorRepo.countByStatus(censorStatus);
    }

    @Override
    public Long getTotalCensorByAI() {
        return censorRepo.countAISystem();
    }

    @Override
    public Long getTotalCensorByAdmin() {
        return censorRepo.countAdmin();
    }

    @Override
    public Long getTotalPostByStatus(String postStatus) {
        return postRepo.countByStatus(postStatus);
    }

    @Override
    public List<StatisticPostResponse> getStatisticPost() {
        List<CategoryProduct> listCategoryProducts = categoryProductRepo.findAll();
        List<StatisticPostResponse> result = new ArrayList<>();
        for (CategoryProduct cp : listCategoryProducts) {
            long count = 0;
            count = postRepo.countByCategoryProduct(cp);
            StatisticPostResponse statisticPostModel = new StatisticPostResponse(cp, count);
            result.add(statisticPostModel);
        }
        return result;
    }

    @Override
    public StatisticCensorResponse getStatisticCensor() {
        StatisticCensorResponse response = new StatisticCensorResponse();
        long acceptByAI = censorRepo.countAcceptByAI();
        long rejectByAI = censorRepo.countRejectByAI();
        response.setAcceptByAI(acceptByAI);
        response.setRejectByAI(rejectByAI);

        long acceptByAdmin = censorRepo.countAcceptByAdmin();
        long rejectByAdmin = censorRepo.countRejectByAdmin();
        response.setAcceptByAdmin(acceptByAdmin);
        response.setRejectByAdmin(rejectByAdmin);

        long totalCensor = censorRepo.count();
        long censored = censorRepo.countByStatus("Đã kiểm duyệt");
        response.setTotal(totalCensor);
        response.setCensored(censored);

        return response;
    }

    @Override
    public List<StatisticExchangeResponse> getStatisticExchange() {
        List<StatisticExchangeResponse> response = new ArrayList<>();

        long count = exchangeRepo.countByStatus("Chờ xác nhận");
        response.add(new StatisticExchangeResponse("Chờ xác nhận", count));

        count = exchangeRepo.countByStatus("Chờ trao đổi");
        response.add(new StatisticExchangeResponse("Chờ trao đổi", count));

        count = exchangeRepo.countByStatus("Đã hủy");
        response.add(new StatisticExchangeResponse("Đã hủy", count));

        count = exchangeRepo.countByStatus("Đã trao đổi");
        response.add(new StatisticExchangeResponse("Đã trao đổi", count));

        return response;
    }

    @Override
    public Long getTotalExchange() {
        return exchangeRepo.count();
    }

    @Override
    public List<StatisticUserResponse> getStatisticUser() {
        List<StatisticUserResponse> responses = new ArrayList<>();

        long cnt = 0;

        cnt = accountRepo.countAdmin();
        responses.add(new StatisticUserResponse("Admin", cnt));

        cnt = accountRepo.countUser();
        responses.add(new StatisticUserResponse("User", cnt));

        cnt = accountRepo.countBanedUser();
        responses.add(new StatisticUserResponse("Bị vô hiệu hóa", cnt));

        cnt = accountRepo.count();
        responses.add(new StatisticUserResponse("Tổng tài khoản", cnt));

        return responses;
    }
}

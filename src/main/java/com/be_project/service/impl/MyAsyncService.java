package com.be_project.service.impl;

import com.be_project.entity.dto.ListURLDto;
import com.be_project.entity.model.PredictResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyAsyncService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public MyAsyncService() {
        this.webClient = WebClient.create("http://localhost:5000");
        this.objectMapper = new ObjectMapper();
    }

    @Async("myTaskExecutor")
    public void performAsyncTask() {
        // Tác vụ bất đồng bộ sẽ được thực hiện ở đây
        String responseBody = webClient.get()
                .uri("/hello")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        try{
            String gido = objectMapper.readValue(responseBody, String.class);
            System.out.println(gido);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Async("myTaskExecutor")
    public void fetchPredictData(ListURLDto listURLDto) {
        // Tác vụ bất đồng bộ sẽ được thực hiện ở đây
        String responseBody = webClient.post()
                .uri("/predict")
                .body(Mono.just(listURLDto), ListURLDto.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(responseBody);
        try{
            PredictResponse gido = objectMapper.readValue(responseBody, PredictResponse.class);
            System.out.println(gido.getData().get(0).getUrl());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public String fetchData(){
        String responseBody = webClient.get()
                .uri("/hello")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return responseBody;
    }
}

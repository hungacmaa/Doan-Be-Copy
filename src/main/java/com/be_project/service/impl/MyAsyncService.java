package com.be_project.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MyAsyncService {

    @Async
    public void performAsyncTask() {
        // Tác vụ bất đồng bộ sẽ được thực hiện ở đây
        System.out.println("Async task is being performed...");
    }
}

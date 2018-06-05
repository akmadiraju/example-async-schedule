package com.example.myasyncschedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Component
public class ExampleScheduler {

    @Autowired
    private LookUpService lookUpService;

    @Autowired
    private  ThreadPoolTaskScheduler threadPoolTaskScheduler;


    @Scheduled(cron = "0 0 * * * *")
    public void onStartUp(){
        String[] names = {"test1.txt", "test2.txt", "test3.txt" };
        List<String> namesAsList = Arrays.asList(names);
        for (String name: namesAsList) {
            try {
                lookUpService.userFunction(name);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

}

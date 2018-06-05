package com.example.myasyncschedule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;

@Configuration
public class ExampleAsyncConfig {

    @Bean
    public Executor threadExecutor(){
        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        threadPoolExecutor.setCorePoolSize(3);
        threadPoolExecutor.setMaxPoolSize(4);
        threadPoolExecutor.setQueueCapacity(200);
        threadPoolExecutor.setThreadNamePrefix("ExampleAsync-");
        threadPoolExecutor.initialize();
        return threadPoolExecutor;
    }

}

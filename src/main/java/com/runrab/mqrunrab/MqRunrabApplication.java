package com.runrab.mqrunrab;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //开启定时功能的注解
public class MqRunrabApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqRunrabApplication.class, args);
    }

}

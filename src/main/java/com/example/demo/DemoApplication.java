package com.example.demo;

import com.example.demo.deal.DealService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.demo.quotation.dao"})
public class DemoApplication implements CommandLineRunner {

    @Autowired
    DealService dealService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        dealService.createDealInfo();
        dealService.createDealList();
        dealService.createquotation();
    }
}

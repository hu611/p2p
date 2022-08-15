package com.example.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.Service.Loan.*;

@Component
public class TimerManager {
    @Autowired
    IncomeRecordService incomeRecordService;

    public void generateIncomePlan() {
        try {
            System.out.println("===========生成受益计划开始======");
            incomeRecordService.generateIncomePlan();
            System.out.println("===========生成受益计划结束======");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "* 0/12 * * * *")
    public void generateIncomeBack() throws Exception {
        System.out.println("======开始返还收益====");
        incomeRecordService.generateIncomeBack();
        System.out.println("======结束返还收益====");
    }
}

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
            System.out.println("===========Starting generate income plan======");
            incomeRecordService.generateIncomePlan();
            System.out.println("===========Ending generate income plan======");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "* 0/12 * * * *")
    public void generateIncomeBack() throws Exception {
        //System.out.println("======Starting generate income back====");
        incomeRecordService.generateIncomeBack();
        //System.out.println("=====Ending generate income back=====");
    }
}

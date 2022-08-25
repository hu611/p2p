package com.example.Service.User;

import com.example.pojo.RechargeRecord;
import com.example.pojo.User;

import java.util.List;

public interface RechargeService {
    public int addCredit(double credit, User user) throws Exception;

    public List<RechargeRecord> getRechargeRecords(int user_id);
}

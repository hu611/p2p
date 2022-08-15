package com.example.Service.User;

import com.example.pojo.User;

public interface RechargeService {
    public int addCredit(double credit, User user) throws Exception;
}

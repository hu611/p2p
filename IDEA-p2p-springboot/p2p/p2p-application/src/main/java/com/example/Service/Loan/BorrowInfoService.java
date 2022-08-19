package com.example.Service.Loan;

import com.example.pojo.BorrowInfo;

import java.util.Map;

public interface BorrowInfoService {
    public int borrow(Map<String,Object> map) throws Exception;
}

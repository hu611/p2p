package com.example.Impl.loan;

import com.example.Service.Loan.BorrowInfoService;
import com.example.mapper.BorrowInfoMapper;
import com.example.mapper.LoanInfoMapper;
import com.example.pojo.BorrowInfo;
import com.example.pojo.LoanInfo;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class BorrowInfoServiceImpl implements BorrowInfoService {
    @Autowired
    BorrowInfoMapper borrowInfoMapper;

    @Autowired
    LoanInfoMapper loanInfoMapper;

    @Override
    public int borrow(Map<String,Object> map) throws Exception {
        SimpleDateFormat productno_formatter = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat releaseDate_formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String projectName = (String) map.get("projectName");
        String productName = (String) map.get("productName");
        String productMoney = (String) map.get("productMoney");
        Double rate = (Double) map.get("rate");
        double product_money = Double.parseDouble(productMoney);
        String productDesc = (String) map.get("productDesc");
        User user = (User) map.get("user");

        int product_type = 0;
        int cycle = 7;
        if(productName.equals("新手宝")) {
            product_type = 0;
            cycle = 7;
        } else if(productName.equals("优选类产品")) {
            product_type = 1;
            cycle = 1;
        } else {
            product_type = 2;
            cycle = 6;
        }


        LoanInfo loanInfo = new LoanInfo();
        loanInfo.setCycle(cycle);
        loanInfo.setProductName(projectName);
        loanInfo.setProductDesc(productDesc);
        loanInfo.setProductStatus(0);
        loanInfo.setProductType(product_type);
        loanInfo.setBidMaxLimit(Math.max(1000.00,product_money));
        loanInfo.setBidMinLimit(Math.min(100.00,product_money));
        loanInfo.setRate(rate);
        loanInfo.setProductMoney(product_money);
        loanInfo.setLeftProductMoney(product_money);
        loanInfo.setVersion(0);
        loanInfo.setProductNo(productno_formatter.format(date));
        loanInfo.setReleaseTime(date);
        int success = loanInfoMapper.insert(loanInfo);
        if(success <= 0) {
            throw new Exception("loan info failed to insert into the table");
        }
        success = borrowInfoMapper.insert(new BorrowInfo(loanInfo.getId(),user.getId()));
        if(success <= 0) {
            throw new Exception("borrow info failed to insert into the table");
        }
        return success;



    }
}

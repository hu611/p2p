package com.example.Impl.User;

import com.example.Service.User.RechargeService;
import com.example.mapper.FinanceAccountMapper;
import com.example.mapper.RechargeRecordMapper;
import com.example.pojo.FinanceAccount;
import com.example.pojo.RechargeRecord;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class RechargeServiceImpl implements RechargeService {
    @Autowired
    FinanceAccountMapper financeAccountMapper;

    @Autowired
    RechargeRecordMapper rechargeRecordMapper;

    @Override
    public int addCredit(double credit, User user) throws Exception{
        //add credit into finance account
        FinanceAccount financeAccount = financeAccountMapper.selectByUserId(user.getId());
        financeAccount.setAvailableMoney(financeAccount.getAvailableMoney() + credit);
        int success = financeAccountMapper.updateByPrimaryKeySelective(financeAccount);
        if(success == 0) {
            throw new Exception("更新用户余额失败");
        }
        //add records into recharge record
        RechargeRecord rechargeRecord = new RechargeRecord();
        rechargeRecord.setRechargeDesc("用户充值");
        rechargeRecord.setRechargeNo(getRandomRechargeNo());
        rechargeRecord.setRechargeTime(new Date());
        rechargeRecord.setRechargeMoney(credit);
        rechargeRecord.setUid(user.getId());
        rechargeRecord.setRechargeStatus("1");
        success = rechargeRecordMapper.insert(rechargeRecord);
        if(success == 0) {
            throw new Exception("更新用户余额成功，添加用户充值记录失败");
        }
        return 0;
    }

    //generate 6 digit random number
    public String getRandomRechargeNo() {
        int upper_limit = 10;
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder("");
        for(int i = 0; i < 6; i++) {
            stringBuilder.append(random.nextInt(upper_limit));
        }
        return stringBuilder.toString();
    }

}

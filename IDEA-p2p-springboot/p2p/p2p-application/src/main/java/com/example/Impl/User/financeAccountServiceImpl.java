package com.example.Impl.User;

import com.example.Service.User.financeAccountService;
import com.example.mapper.FinanceAccountMapper;
import com.example.pojo.FinanceAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class financeAccountServiceImpl implements financeAccountService {
    @Autowired
    FinanceAccountMapper financeAccountMapper;

    @Override
    public FinanceAccount queryFinanceAccountByUid(int uid) {
        return financeAccountMapper.selectByUserId(uid);
    }
}

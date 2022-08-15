package com.example.Service.User;

import com.example.pojo.FinanceAccount;

public interface financeAccountService {
    /**
     * get finance account by user id
     * @param id
     * @return
     */
    FinanceAccount queryFinanceAccountByUid(int uid);
}

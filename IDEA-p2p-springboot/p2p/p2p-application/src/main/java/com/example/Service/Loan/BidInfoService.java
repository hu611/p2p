package com.example.Service.Loan;

import com.example.CustomObject.PhoneAndBidAmount;
import com.example.pojo.BidInfo;

import java.util.List;
import java.util.Map;

public interface BidInfoService {
    double queryTotalBidMoney();

    /**
     * Top 10 recently bid information based on loan product
     * @param params
     * @return
     */
    List<BidInfo> queryRecentlyBidInfoByLoanId(Map<String, Object> params);

    List<PhoneAndBidAmount> queryBidUserTop();

    void initializeBidUserTop();
}

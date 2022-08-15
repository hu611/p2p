package com.example.Service.Loan;

import com.example.pojo.LoanInfo;

import java.util.List;
import java.util.Map;

public interface LoanInfoService {
    /**
     * get average rate for the product
     * @return
     */
    Double queryHistoryAvgRate();

    /**
     * get loan information based on the product type
     * @param map
     * @return
     */
    List<LoanInfo> queryLoanInfoListByProductType(Map<String, Object> map);

    int queryTotalNumOfRows(Map<String, Object> map);

    LoanInfo queryLoanInfoById(int id);

    public void invest(Map<String, Object> params) throws Exception;
}

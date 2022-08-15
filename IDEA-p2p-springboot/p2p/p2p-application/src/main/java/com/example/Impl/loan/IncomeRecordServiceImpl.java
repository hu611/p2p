package com.example.Impl.loan;

import com.example.Service.Loan.IncomeRecordService;
import com.example.mapper.BidInfoMapper;
import com.example.mapper.FinanceAccountMapper;
import com.example.mapper.IncomeRecordMapper;
import com.example.mapper.LoanInfoMapper;
import com.example.pojo.BidInfo;
import com.example.pojo.IncomeRecord;
import com.example.pojo.LoanInfo;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class IncomeRecordServiceImpl implements IncomeRecordService {
    @Autowired
    IncomeRecordMapper incomeRecordMapper;

    @Autowired
    BidInfoMapper bidInfoMapper;

    @Autowired
    LoanInfoMapper loanInfoMapper;

    @Autowired
    FinanceAccountMapper financeAccountMapper;

    @Transactional
    @Override
    public void generateIncomePlan() throws Exception{
        //find loan information that has product status 1
        List<LoanInfo> loanInfoList = loanInfoMapper.selectLoanInfoListByProductStatus(1);
        for(LoanInfo loanInfo: loanInfoList) {
            List<BidInfo> bidInfoList = bidInfoMapper.selectBidInfoByLoanId(loanInfo.getId());
            for (BidInfo bidInfo : bidInfoList) {
                IncomeRecord incomeRecord = new IncomeRecord();
                incomeRecord.setUid(bidInfo.getUid());
                incomeRecord.setLoanId(bidInfo.getLoanId());
                incomeRecord.setBidId(bidInfo.getId());
                incomeRecord.setBidMoney(bidInfo.getBidMoney());
                incomeRecord.setIncomeStatus(0);
                Date incomeDate = null;
                Double incomeMoney = null;
                if(loanInfo.getProductType() == 0) {
                    incomeDate = DateUtils.addDays(loanInfo.getProductFullTime(), loanInfo.getCycle());
                    incomeMoney = bidInfo.getBidMoney() * (loanInfo.getRate()/100/365) * loanInfo.getCycle();
                } else {
                    incomeDate = DateUtils.addMonths(loanInfo.getProductFullTime(),loanInfo.getCycle());
                    incomeMoney = bidInfo.getBidMoney() * (loanInfo.getRate()/100/365) * loanInfo.getCycle() * 30;
                }
                incomeMoney = Math.round(incomeMoney * Math.pow(10,2))/Math.pow(10,2);
                incomeRecord.setIncomeDate(incomeDate);
                incomeRecord.setIncomeMoney(incomeMoney);
                int success = incomeRecordMapper.insertSelective(incomeRecord);
                if(success == 0) {
                    throw new Exception("生成收益记录失败");
                }
            }
            loanInfo.setProductStatus(2);
            int success = loanInfoMapper.updateByPrimaryKey(loanInfo);
            if(success == 0) {
                throw new Exception("改变状态产品失败");
            }
        }
    }

    @Transactional
    @Override
    public void generateIncomeBack() throws Exception {
        List<IncomeRecord> incomeRecordList = incomeRecordMapper.selectIncomeRecordListByIncomeStatusAndDate(0);
        for (IncomeRecord incomeRecord : incomeRecordList) {
            HashMap<String, Object> params = new HashMap<>();
            params.put("uid", incomeRecord.getUid());
            params.put("bidMoney", incomeRecord.getBidMoney());
            params.put("incomeMoney", incomeRecord.getIncomeMoney());
            int success = financeAccountMapper.updateFinanceAccountByIncome(params);
            if(success == 0) {
                throw new Exception("更新账户表出错");
            }
            incomeRecord.setIncomeStatus(1);
            success = incomeRecordMapper.updateByPrimaryKeySelective(incomeRecord);
            if(success == 0) {
                throw new Exception("更新收益表失败");
            }
        }
    }
}

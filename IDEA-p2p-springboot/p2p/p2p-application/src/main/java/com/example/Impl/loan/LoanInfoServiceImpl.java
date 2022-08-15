package com.example.Impl.loan;
import com.example.Service.Loan.LoanInfoService;
import com.example.mapper.BidInfoMapper;
import com.example.mapper.FinanceAccountMapper;
import com.example.mapper.LoanInfoMapper;
import com.example.pojo.BidInfo;
import com.example.pojo.LoanInfo;
import com.example.pojo.User;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import com.example.Utils.Constants;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LoanInfoServiceImpl implements LoanInfoService {

    @Autowired
    private LoanInfoMapper loanInfoMapper;

    @Autowired
    private FinanceAccountMapper financeAccountMapper;

    @Autowired
    private BidInfoMapper bidInfoMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Double queryHistoryAvgRate() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Double avgRate = (Double) redisTemplate.opsForValue().get(Constants.HISTORY_AVG_RATE);
        if(!ObjectUtils.allNotNull(avgRate)) {
            //double verification, in case the high number of concurrency happens
            synchronized (this) {

                avgRate = (Double) redisTemplate.opsForValue().get(Constants.HISTORY_AVG_RATE);
                if(!ObjectUtils.allNotNull(avgRate)) {
                    System.out.println("===从数据库获取===");
                    //redis does not have corresponding data
                    avgRate = loanInfoMapper.selectHistoryAvgRate();
                    redisTemplate.opsForValue().set(Constants.HISTORY_AVG_RATE, avgRate,7, TimeUnit.DAYS);
                } else {
                    System.out.println("===从Redis中获取数据2===");
                }
            }
        } else {
            System.out.println("===从Redis中获取数据===");
        }
        return avgRate;
    }

    @Override
    public List<LoanInfo> queryLoanInfoListByProductType(Map<String, Object> map) {
        return loanInfoMapper.selectLoanInfoListByProductType(map);
    }

    /**
     * Get total number of rows in the loan_info database
     * @return integer: Total number of rows
     */
    @Override
    public int queryTotalNumOfRows(Map<String, Object> map) {
        return loanInfoMapper.totalNumOfRows(map);
    }

    @Override
    public LoanInfo queryLoanInfoById(int id) {
        return loanInfoMapper.selectLoanInfoById(id);
    }

    @Transactional
    @Override
    public void invest(Map<String, Object> params) throws Exception{
        //update left product money for loan
        Integer loanId = (Integer) params.get("loanId");
        User user = (User) params.get("user");
        String phone = user.getPhone();
        Double bidMoney = (Double) params.get("bidMoney");
        LoanInfo loanInfo = loanInfoMapper.selectByPrimaryKey(loanId);
        params.put("version", loanInfo.getVersion());
        params.put("uid", user.getId());
        int success = loanInfoMapper.updateLeftProductMoney(params);
        if(success == 0) {
            throw new Exception("投资失败");
        }

        //update remaining bid money for user
        success = financeAccountMapper.updateByBidMoney(params);
        if(success == 0) {
            throw new Exception("投资失败");
        }

        //add a new bid record
        BidInfo bidInfo = new BidInfo();
        bidInfo.setLoanId(loanId);
        bidInfo.setUid(user.getId());
        bidInfo.setBidMoney(bidMoney);
        bidInfo.setBidTime(new Date());
        bidInfo.setBidStatus(1);
        success = bidInfoMapper.insertSelective(bidInfo);

        //check if loan left money is 0
        loanInfo = loanInfoMapper.selectByPrimaryKey(loanId);
        if(loanInfo.getLeftProductMoney() == 0) {
            loanInfo.setProductStatus(1);
            loanInfo.setProductFullTime(new Date());
            success = loanInfoMapper.updateByPrimaryKeySelective(loanInfo);
            if(success == 0) {
                throw new Exception("投资失败");
            }
        }

        //store user bid price into Redis
        redisTemplate.opsForZSet().incrementScore(Constants.TOP_INVESTOR, phone, bidMoney);
    }
}

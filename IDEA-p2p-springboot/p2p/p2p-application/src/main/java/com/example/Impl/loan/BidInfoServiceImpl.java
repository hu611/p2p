package com.example.Impl.loan;

import com.example.CustomObject.PhoneAndBidAmount;
import com.example.Service.Loan.BidInfoService;
import com.example.Utils.Constants;
import com.example.mapper.BidInfoMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.BidInfo;
import com.example.pojo.User;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class BidInfoServiceImpl implements BidInfoService {
    @Autowired
    private BidInfoMapper bidInfoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public double queryTotalBidMoney() {
        Double totalBidMoney = (Double) redisTemplate.opsForValue().get(Constants.ALL_BID_MONEY);
        if(!ObjectUtils.allNotNull(totalBidMoney)) {
            synchronized (this) {
                totalBidMoney = (Double) redisTemplate.opsForValue().get(Constants.ALL_BID_MONEY);
                if(!ObjectUtils.allNotNull(totalBidMoney)) {
                    //visit database
                    totalBidMoney = bidInfoMapper.selectTotalBidMoney();
                    //store into redis
                    redisTemplate.opsForValue().set(Constants.ALL_BID_MONEY,totalBidMoney,7, TimeUnit.DAYS);
                }
            }
        }
        return totalBidMoney;
    }

    @Override
    public List<BidInfo> queryRecentlyBidInfoByLoanId(Map<String, Object> params) {
        return bidInfoMapper.selectRecentlyBidInfoByLoanId(params);
    }

    /**
     * get top 5 bidder who put the most money into bid. As a way to reward their dedication to this platform
     * @return list of top players in our platform
     */
    @Override
    public List<PhoneAndBidAmount> queryBidUserTop() {
        ArrayList<PhoneAndBidAmount> phoneAndBidAmountArrayList = new ArrayList<>();
        Set<ZSetOperations.TypedTuple<Object>> set = redisTemplate.opsForZSet().reverseRangeWithScores(Constants.TOP_INVESTOR, 0 ,5);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = set.iterator();
        PhoneAndBidAmount phoneAndBidAmount;
        while(iterator.hasNext()) {
            phoneAndBidAmount = new PhoneAndBidAmount();
            ZSetOperations.TypedTuple<Object> next = iterator.next();
            String phone = (String) next.getValue();
            phoneAndBidAmount.setPhone(phone);
            Double bidAmount = next.getScore();
            phoneAndBidAmount.setBidMoney(bidAmount);
            phoneAndBidAmountArrayList.add(phoneAndBidAmount);
        }
        return phoneAndBidAmountArrayList;

    }

    /**
     * put all of our current bid information to Redis
     */
    @Override
    public void initializeBidUserTop() {
        List<PhoneAndBidAmount> phoneAndBidAmountList = bidInfoMapper.queryAllUsersAndBidAmounts();
        for (PhoneAndBidAmount phoneAndBidAmount : phoneAndBidAmountList) {
            redisTemplate.opsForZSet().incrementScore(Constants.TOP_INVESTOR, phoneAndBidAmount.getPhone(), phoneAndBidAmount.getBidMoney());
        }
    }
}

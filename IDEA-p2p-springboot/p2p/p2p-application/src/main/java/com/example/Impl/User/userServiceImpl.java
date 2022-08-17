package com.example.Impl.User;

import com.example.Service.User.userService;
import com.example.Utils.Constants;
import com.example.mapper.FinanceAccountMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.FinanceAccount;
import com.example.pojo.User;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class userServiceImpl implements userService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FinanceAccountMapper financeAccountMapper;

    //@Autowired
    //private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public long queryAllUserCount() {
        /*
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Long userCount = (Long) redisTemplate.opsForValue().get(Constants.USER_COUNT);
        if(!ObjectUtils.allNotNull(userCount)) {
            synchronized (this) {
                userCount = (Long) redisTemplate.opsForValue().get(Constants.USER_COUNT);
                if(!ObjectUtils.allNotNull(userCount)) {
                    userCount = userMapper.selectAllUserCount();
                    redisTemplate.opsForValue().set(Constants.USER_COUNT, userCount,7, TimeUnit.DAYS);
                }
            }
        }

         */
        return userMapper.selectAllUserCount();
    }

    @Override
    public User queryUserByPhone(String phone) {
        return userMapper.selectUserByPhone(phone);
    }

    @Override
    public User register(String phone, String password) throws Exception{
        User user = new User();
        user.setPhone(phone);
        user.setLoginPassword(password);
        user.setAddTime(new Date());
        user.setLastLoginTime(new Date());
        int user_id = userMapper.insertSelective(user);
        if(user_id == 0) {
            throw new Exception("Registration failed");
        }
        //user = userMapper.selectUserByPhone(phone);

        //initialize finance account for new user
        FinanceAccount financeAccount = new FinanceAccount();
        financeAccount.setUid(user.getId());
        financeAccount.setAvailableMoney(888.0);
        int finance_account_id = financeAccountMapper.insertSelective(financeAccount);
        if(finance_account_id == 0) {
            throw new Exception("Registration failed");
        }
        return user;
    }

    @Override
    public int modifyUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User queryUserByPhoneAndPwd(String phone, String password) {
        return userMapper.queryUserByPhoneAndPwd(phone, password);
    }

    @Override
    public User updateUserLoginTime(User user) throws Exception{
        user.setLastLoginTime(new Date());
        int success = userMapper.updateByPrimaryKey(user);
        if(success == 0) {
            throw new Exception();
        }
        return user;
    }
}

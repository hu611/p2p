package com.example.Service.User;

import com.example.pojo.User;

import java.util.Date;

public interface userService {
    long queryAllUserCount();

    /**
     * Check user based on their phone
     * @param phone
     * @return User with phone
     *
     */
    User queryUserByPhone(String phone);

    /**
     * Register a user
     * @param phone
     * @param password
     * @return
     */
    User register(String phone, String password) throws Exception;

    /**
     * 实名认证方法
     * @param user
     * @return
     */
    int modifyUser(User user);

    /**
     * Check if entered phone number and password matches
     * @param phone
     * @param password
     */
    User queryUserByPhoneAndPwd(String phone, String password);

    User updateUserLoginTime(User user) throws Exception;
}

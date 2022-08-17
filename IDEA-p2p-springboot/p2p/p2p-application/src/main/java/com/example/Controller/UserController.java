package com.example.Controller;

import com.example.Utils.Constants;
import com.example.Utils.Result;
import com.example.pojo.FinanceAccount;
import com.example.pojo.User;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.example.Service.User.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    userService userService;

    @Autowired
    financeAccountService financeAccountService;

    //@Autowired
    //RedisTemplate<Object, Object> redisTemplate;

    @RequestMapping("/loan/page/register")
    public String toRegister() {
        return "register";
    }

    /**
     * Verify if the username exists
     * @param phone
     * @return code = 1, success = true if username exists, code = 0, success = false otherwise
     *
     */
    @RequestMapping("/loan/checkPhone")
    @ResponseBody
    public Map<String, Object> checkPhone(@RequestParam("phone") String phone) {
        Map<String, Object> params = new HashMap<>();
        User user = userService.queryUserByPhone(phone);
        if(!ObjectUtils.allNotNull(user)) {
            params = Result.success("");
        } else {
            params = Result.fail("手机号已被人注册");
        }

        return params;
    }

    @RequestMapping("/loan/register")
    @ResponseBody
    public Map<String, Object> register(HttpServletRequest request,
                                        Model model,
                                        @RequestParam("phone") String phone,
                                        @RequestParam("password") String password) {
        try {
            User user = userService.register(phone, password);
            request.getSession().setAttribute(Constants.SESSION_USER, user);
        } catch (Exception e) {
             return Result.fail("注册失败");
        }
        return Result.success("注册成功");
    }

    @RequestMapping("/loan/realName")
    @ResponseBody
    public Map<String, Object> realName(HttpServletRequest request,
                           @RequestParam("phone") String phone,
                           @RequestParam("realName") String realName,
                           @RequestParam("idCard") String idCard,
                           @RequestParam("messageCode") String messageCode) {
        try {
            User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
            user.setName(realName);
            user.setIdCard(idCard);
            int success = userService.modifyUser(user);
            if(success == 0) {
                throw new Exception("实名认真失败");
            }
            return Result.success("");
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping("/page/realName")
    public String toRealName() {
        return "realName";
    }

    @RequestMapping("/loan/myFinanceAccount")
    @ResponseBody
    public String getFinanceAccountByUid(HttpServletRequest httpServletRequest) {
        User user = (User) httpServletRequest.getSession().getAttribute(Constants.SESSION_USER);
        if(user == null) {
            FinanceAccount financeAccount = financeAccountService.queryFinanceAccountByUid(user.getId());
            return Double.toString(financeAccount.getAvailableMoney());
        }
        return "";
    }

    @RequestMapping("/loan/myCenter")
    public String toMyCenter(HttpServletRequest request,
                             Model model) {
        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        FinanceAccount financeAccount = financeAccountService.queryFinanceAccountByUid(user.getId());
        model.addAttribute(Constants.AVAILABLE_MONEY, financeAccount.getAvailableMoney());
        return "myCenter";
    }

    @RequestMapping("/loan/logout")
    public String toLogOut(HttpServletRequest request) {
        request.getSession().removeAttribute(Constants.SESSION_USER);
        return "redirect:/";
    }

    @RequestMapping("/loan/page/login")
    public String toLogIn() {
        return "login";
    }

    @RequestMapping("/loan/login")
    @ResponseBody
    public Map<String, Object> checkLogin(@RequestParam("phone") String phone,
                                          @RequestParam("password") String password,
                                          @RequestParam("messageCode") String messageCode,
                                          HttpServletRequest request) {
        try {
            /*
            String redisMsgCode = (String) redisTemplate.opsForValue().get(phone);
            if(!ObjectUtils.allNotNull(redisMsgCode) || !redisMsgCode.equals(messageCode)) {
                return Result.wrongMessageCode("验证码错误");
            }*/
            User user = userService.queryUserByPhoneAndPwd(phone,password);
            if(ObjectUtils.allNotNull(user)) {
                user = userService.updateUserLoginTime(user);
                request.getSession().setAttribute(Constants.SESSION_USER, user);
            } else {
                throw new Exception("密码不对");
            }
            return Result.success("");
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping("/user/messageCode")
    @ResponseBody
    public Map<String, Object> generateMessageCode(@RequestParam("phone") String phone) {
        int upperbound = 10;
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0; i < 4; i++) {
            int rand = random.nextInt(upperbound);
            stringBuffer.append(rand);
        }
        String result = stringBuffer.toString();
        //redisTemplate.setKeySerializer(new StringRedisSerializer());
        //redisTemplate.opsForValue().set(phone, result);
        return Result.success(result);
    }

}

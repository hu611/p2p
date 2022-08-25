package com.example.Controller;

import com.example.Service.Loan.BorrowInfoService;
import com.example.Utils.Result;
import com.example.pojo.User;
import com.example.Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
public class BorrowInfoController {

    @Autowired
    BorrowInfoService borrowInfoService;

    @RequestMapping("/loan/borrow")
    public String borrow() {
        return "borrow";
    }

    @RequestMapping("/loan/_borrow")
    @ResponseBody
    public Map<String, Object> _borrow(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("rate") Double rate,
            @RequestParam("productName") String productName,
            @RequestParam("productMoney") String productMoney,
            @RequestParam("projectName") String projectName,
            @RequestParam("productDesc") String productDesc) {
        Map<String, Object> hm = new HashMap<>();
        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        if(user == null) {
            return Result.fail("Please Log in first");
        }

        hm.put("rate",rate);
        hm.put("productName", productName);
        hm.put("productMoney",productMoney);
        hm.put("projectName", projectName);
        hm.put("productDesc", productDesc);
        hm.put("user", user);
        try {
            borrowInfoService.borrow(hm);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(404);
        }
        return Result.success("借款信息成功");

    }
}

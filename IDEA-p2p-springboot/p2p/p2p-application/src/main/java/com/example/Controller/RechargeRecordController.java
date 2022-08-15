package com.example.Controller;

import com.example.Service.User.RechargeService;
import com.example.Utils.Constants;
import com.example.mapper.FinanceAccountMapper;
import com.example.pojo.FinanceAccount;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RechargeRecordController {
    @Autowired
    RechargeService rechargeService;


    @RequestMapping("/loan/page/toRecharge")
    public String toRecharge() {
        return "toRecharge";
    }

    @RequestMapping("/loan/toRecharge")
    public String addCredit(HttpServletRequest request,
                            Model model,
                            @RequestParam("rechargeMoney") double rechargeMoney) {
        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        try {
            rechargeService.addCredit(rechargeMoney, user);
            model.addAttribute("success",1);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("success",0);
        }
        return "kuaiQianForm";

    }
}

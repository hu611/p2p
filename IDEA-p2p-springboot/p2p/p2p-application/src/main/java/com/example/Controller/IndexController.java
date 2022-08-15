package com.example.Controller;

import com.example.Impl.User.userServiceImpl;
import com.example.Impl.loan.LoanInfoServiceImpl;
import com.example.Service.Loan.BidInfoService;
import com.example.Service.Loan.LoanInfoService;
import com.example.Service.User.userService;
import com.example.Timer.TimerManager;
import com.example.Utils.Constants;
import com.example.mapper.LoanInfoMapper;
import com.example.pojo.LoanInfo;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class IndexController {

    @Autowired
    TimerManager timerManager;

    @Autowired
    private LoanInfoService loanInfoService;

    @Autowired
    private userService userService;

    @Autowired
    private BidInfoService bidInfoService;

    @RequestMapping(value = {"/","/index"})
    public String toIndex(Model model) {
        timerManager.generateIncomePlan();
        Double historyAvgRate = loanInfoService.queryHistoryAvgRate();
        long userCount = userService.queryAllUserCount();
        double totalBidMoney = bidInfoService.queryTotalBidMoney();

        //get loan（新手宝）information
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("productType", Constants.PRODUCT_TYPE_X);
        params.put("currentPage",0);
        params.put("pageSize",1);
        List<LoanInfo> loanInfoListX = loanInfoService.queryLoanInfoListByProductType(params);

        //优选类产品
        params.put("productType",1);
        params.put("pageSize",4);
        List<LoanInfo> loanInfoListY = loanInfoService.queryLoanInfoListByProductType(params);

        //散标产品
        params.put("productType",2);
        params.put("pageSize",8);
        List<LoanInfo> loanInfoListS = loanInfoService.queryLoanInfoListByProductType(params);

        //add info into model
        model.addAttribute(Constants.USER_COUNT,userCount);
        model.addAttribute(Constants.HISTORY_AVG_RATE,historyAvgRate);
        model.addAttribute(Constants.ALL_BID_MONEY, totalBidMoney);
        model.addAttribute(Constants.LOAN_INFO_LIST_X, loanInfoListX);
        model.addAttribute(Constants.LOAN_INFO_LIST_Y, loanInfoListY);
        model.addAttribute(Constants.LOAN_INFO_LIST_S, loanInfoListS);
        return "index";
    }
}

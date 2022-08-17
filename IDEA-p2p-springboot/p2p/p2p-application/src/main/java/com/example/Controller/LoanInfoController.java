package com.example.Controller;

import com.example.CustomObject.PhoneAndBidAmount;
import com.example.Impl.User.financeAccountServiceImpl;
import com.example.Service.Loan.BidInfoService;
import com.example.Service.Loan.LoanInfoService;
import com.example.Service.User.financeAccountService;
import com.example.Utils.Constants;
import com.example.Utils.Result;
import com.example.pojo.BidInfo;
import com.example.pojo.FinanceAccount;
import com.example.pojo.LoanInfo;
import com.example.pojo.User;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoanInfoController {
    @Autowired
    LoanInfoService loanInfoService;

    @Autowired
    BidInfoService bidInfoService;

    @Autowired
    financeAccountService financeAccountService;

    @RequestMapping("/loan/loan")
    public String loan(HttpServletRequest httpServletRequest,
                       Model model,
                       @RequestParam(value = "ptype", required = false) Integer productType,
                       @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage) {

        ////pagination for loan information
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("productType", productType);
        hashMap.put("currentPage", (currentPage-1) * 9);
        hashMap.put("pageSize", Constants.LOAN_PAGESIZE);
        List<LoanInfo> loanInfoList = loanInfoService.queryLoanInfoListByProductType(hashMap);
        int totalNumOfRows = loanInfoService.queryTotalNumOfRows(hashMap);
        int totalPage = getTotalPage(totalNumOfRows, Constants.LOAN_PAGESIZE);

        //investor ranking
        List<PhoneAndBidAmount> phoneAndBidAmountList = bidInfoService.queryBidUserTop();

        //if investor ranking is empty, need to initialize it, and redo the previous steps
        if(phoneAndBidAmountList.isEmpty()) {
            bidInfoService.initializeBidUserTop();
            phoneAndBidAmountList = bidInfoService.queryBidUserTop();
        }

        //send required data to loan.html
        model.addAttribute(Constants.LOAN_INFO_LIST, loanInfoList);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("totalSize",totalNumOfRows);
        model.addAttribute(Constants.BID_USER_LISTS, phoneAndBidAmountList);

        if(ObjectUtils.allNotNull(productType)) {
            model.addAttribute("ptype",productType);
        }
        return "loan";
    }

    @RequestMapping("/loan/loanInfo")
    public String loanInfo(HttpServletRequest request,
                           Model model,
                           @RequestParam("id") Integer id) {
        LoanInfo loanInfo = loanInfoService.queryLoanInfoById(id);
        //add top 10 recently bid information
        Map<String, Object> params = new HashMap<>();
        params.put("loanId",id);
        params.put("currentPage",0);
        params.put("pageSize",10);
        List<BidInfo> bidInfoList = bidInfoService.queryRecentlyBidInfoByLoanId(params);
        model.addAttribute(Constants.BID_INFO_LIST, bidInfoList);
        model.addAttribute(Constants.LOAN_INFO, loanInfo);
        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);

        //if user is registered, then show available money for this specific user
        if(user != null) {
            FinanceAccount financeAccount = financeAccountService.queryFinanceAccountByUid(user.getId());
            model.addAttribute(Constants.AVAILABLE_MONEY,financeAccount.getAvailableMoney());
        }
        return Constants.LOAN_INFO;
    }

    @RequestMapping("/loan/invest")
    @ResponseBody
    public Map<String, Object> invest(HttpServletRequest request,
                                      @RequestParam("bidMoney") Double bidMoney,
                                      @RequestParam("loanId") Integer loanId) {
        try {
            User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
            Map<String, Object> params = new HashMap<>();
            params.put("bidMoney", bidMoney);
            params.put("loanId", loanId);
            params.put("user", user);
            loanInfoService.invest(params);
            return Result.success("投资成功");
        } catch (Exception e) {
            return Result.fail("投资失败");
        }
    }

    @RequestMapping("/loan/borrow")
    public String borrow() {
        return "borrow";
    }
    /**
     * get total page number based on page size and total number of records
     * @param totalNumOfRows
     * @param pageSize
     * @return
     */
    public int getTotalPage(int totalNumOfRows, int pageSize) {
        if(totalNumOfRows % pageSize == 0) {
            return totalNumOfRows/pageSize;
        }
        return totalNumOfRows/pageSize + 1;
    }


}

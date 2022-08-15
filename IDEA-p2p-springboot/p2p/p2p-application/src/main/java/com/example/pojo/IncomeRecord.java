package com.example.pojo;

import java.io.Serializable;
import java.util.Date;

public class IncomeRecord implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_income_record.id
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_income_record.uid
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private Integer uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_income_record.loan_id
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private Integer loanId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_income_record.bid_id
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private Integer bidId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_income_record.bid_money
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private Double bidMoney;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_income_record.income_date
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private Date incomeDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_income_record.income_money
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private Double incomeMoney;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_income_record.income_status
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private Integer incomeStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table b_income_record
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_income_record.id
     *
     * @return the value of b_income_record.id
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_income_record.id
     *
     * @param id the value for b_income_record.id
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_income_record.uid
     *
     * @return the value of b_income_record.uid
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_income_record.uid
     *
     * @param uid the value for b_income_record.uid
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_income_record.loan_id
     *
     * @return the value of b_income_record.loan_id
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public Integer getLoanId() {
        return loanId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_income_record.loan_id
     *
     * @param loanId the value for b_income_record.loan_id
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_income_record.bid_id
     *
     * @return the value of b_income_record.bid_id
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public Integer getBidId() {
        return bidId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_income_record.bid_id
     *
     * @param bidId the value for b_income_record.bid_id
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_income_record.bid_money
     *
     * @return the value of b_income_record.bid_money
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public Double getBidMoney() {
        return bidMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_income_record.bid_money
     *
     * @param bidMoney the value for b_income_record.bid_money
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setBidMoney(Double bidMoney) {
        this.bidMoney = bidMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_income_record.income_date
     *
     * @return the value of b_income_record.income_date
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public Date getIncomeDate() {
        return incomeDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_income_record.income_date
     *
     * @param incomeDate the value for b_income_record.income_date
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_income_record.income_money
     *
     * @return the value of b_income_record.income_money
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public Double getIncomeMoney() {
        return incomeMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_income_record.income_money
     *
     * @param incomeMoney the value for b_income_record.income_money
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setIncomeMoney(Double incomeMoney) {
        this.incomeMoney = incomeMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_income_record.income_status
     *
     * @return the value of b_income_record.income_status
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public Integer getIncomeStatus() {
        return incomeStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_income_record.income_status
     *
     * @param incomeStatus the value for b_income_record.income_status
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setIncomeStatus(Integer incomeStatus) {
        this.incomeStatus = incomeStatus;
    }
}
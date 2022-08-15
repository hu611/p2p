package com.example.pojo;

import java.io.Serializable;
import java.util.Date;

public class LoanInfo implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_loan_info.id
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_loan_info.product_name
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private String productName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_loan_info.rate
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private Double rate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_loan_info.cycle
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private Integer cycle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_loan_info.release_time
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private Date releaseTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_loan_info.product_type
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private Integer productType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_loan_info.product_no
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private String productNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_loan_info.product_money
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private Double productMoney;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_loan_info.left_product_money
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private Double leftProductMoney;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_loan_info.bid_min_limit
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private Double bidMinLimit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_loan_info.bid_max_limit
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private Double bidMaxLimit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_loan_info.product_status
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private Integer productStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_loan_info.product_full_time
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private Date productFullTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_loan_info.product_desc
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private String productDesc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_loan_info.version
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private Integer version;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table b_loan_info
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_loan_info.id
     *
     * @return the value of b_loan_info.id
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_loan_info.id
     *
     * @param id the value for b_loan_info.id
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_loan_info.product_name
     *
     * @return the value of b_loan_info.product_name
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public String getProductName() {
        return productName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_loan_info.product_name
     *
     * @param productName the value for b_loan_info.product_name
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_loan_info.rate
     *
     * @return the value of b_loan_info.rate
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public Double getRate() {
        return rate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_loan_info.rate
     *
     * @param rate the value for b_loan_info.rate
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setRate(Double rate) {
        this.rate = rate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_loan_info.cycle
     *
     * @return the value of b_loan_info.cycle
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public Integer getCycle() {
        return cycle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_loan_info.cycle
     *
     * @param cycle the value for b_loan_info.cycle
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_loan_info.release_time
     *
     * @return the value of b_loan_info.release_time
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public Date getReleaseTime() {
        return releaseTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_loan_info.release_time
     *
     * @param releaseTime the value for b_loan_info.release_time
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_loan_info.product_type
     *
     * @return the value of b_loan_info.product_type
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public Integer getProductType() {
        return productType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_loan_info.product_type
     *
     * @param productType the value for b_loan_info.product_type
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_loan_info.product_no
     *
     * @return the value of b_loan_info.product_no
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public String getProductNo() {
        return productNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_loan_info.product_no
     *
     * @param productNo the value for b_loan_info.product_no
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_loan_info.product_money
     *
     * @return the value of b_loan_info.product_money
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public Double getProductMoney() {
        return productMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_loan_info.product_money
     *
     * @param productMoney the value for b_loan_info.product_money
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setProductMoney(Double productMoney) {
        this.productMoney = productMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_loan_info.left_product_money
     *
     * @return the value of b_loan_info.left_product_money
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public Double getLeftProductMoney() {
        return leftProductMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_loan_info.left_product_money
     *
     * @param leftProductMoney the value for b_loan_info.left_product_money
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setLeftProductMoney(Double leftProductMoney) {
        this.leftProductMoney = leftProductMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_loan_info.bid_min_limit
     *
     * @return the value of b_loan_info.bid_min_limit
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public Double getBidMinLimit() {
        return bidMinLimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_loan_info.bid_min_limit
     *
     * @param bidMinLimit the value for b_loan_info.bid_min_limit
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setBidMinLimit(Double bidMinLimit) {
        this.bidMinLimit = bidMinLimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_loan_info.bid_max_limit
     *
     * @return the value of b_loan_info.bid_max_limit
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public Double getBidMaxLimit() {
        return bidMaxLimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_loan_info.bid_max_limit
     *
     * @param bidMaxLimit the value for b_loan_info.bid_max_limit
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setBidMaxLimit(Double bidMaxLimit) {
        this.bidMaxLimit = bidMaxLimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_loan_info.product_status
     *
     * @return the value of b_loan_info.product_status
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public Integer getProductStatus() {
        return productStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_loan_info.product_status
     *
     * @param productStatus the value for b_loan_info.product_status
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_loan_info.product_full_time
     *
     * @return the value of b_loan_info.product_full_time
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public Date getProductFullTime() {
        return productFullTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_loan_info.product_full_time
     *
     * @param productFullTime the value for b_loan_info.product_full_time
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setProductFullTime(Date productFullTime) {
        this.productFullTime = productFullTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_loan_info.product_desc
     *
     * @return the value of b_loan_info.product_desc
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public String getProductDesc() {
        return productDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_loan_info.product_desc
     *
     * @param productDesc the value for b_loan_info.product_desc
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_loan_info.version
     *
     * @return the value of b_loan_info.version
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_loan_info.version
     *
     * @param version the value for b_loan_info.version
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}
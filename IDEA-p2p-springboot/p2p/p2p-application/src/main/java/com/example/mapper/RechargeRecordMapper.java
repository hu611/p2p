package com.example.mapper;

import com.example.pojo.RechargeRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RechargeRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_recharge_record
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_recharge_record
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    int insert(RechargeRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_recharge_record
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    int insertSelective(RechargeRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_recharge_record
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    RechargeRecord selectByPrimaryKey(Integer id);

    List<RechargeRecord> selectByUserId(Integer uid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_recharge_record
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    int updateByPrimaryKeySelective(RechargeRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_recharge_record
     *
     * @mbg.generated Fri Jun 03 12:29:22 CST 2022
     */
    int updateByPrimaryKey(RechargeRecord record);
}
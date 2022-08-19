package com.example.mapper;

import com.example.pojo.BorrowInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BorrowInfoMapper {
    int insert(BorrowInfo borrowInfo);
}

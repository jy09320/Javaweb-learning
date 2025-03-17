package com.jy.mapper;

import com.jy.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    /**
     * 批量插入员工工作经历信息
     */

    public void insertBatch(List<EmpExpr> exprList);

    void deleteByEmpIds(List<Integer> ids);
}


package com.jy.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jy.mapper.EmpExprMapper;
import com.jy.mapper.EmpMapper;
import com.jy.pojo.Emp;
import com.jy.pojo.EmpExpr;
import com.jy.pojo.EmpQueryParam;
import com.jy.pojo.PageResult;
import com.jy.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
////        //1.调用mapper接口查询总记录数
////        long total = empMapper.count();
////        //2.调用mapper接口查询分页数据
////        int start = (page - 1) * pageSize;
////        List<Emp> rows = empMapper.list(start, pageSize);
////        //3.封装到PageResult对象中
////        return new PageResult<Emp>(total, rows);
//        //1.设置分页参数
//        PageHelper.startPage(page,pageSize);
//        //2.执行查询
//        List<Emp> empList = empMapper.list(name, gender, begin, end);
//        Page<Emp> p = (Page<Emp>) empList;
//        //3.封装到PageResult对象中
//        return new PageResult<>(p.getTotal(),p.getResult());
//    }
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {

        //1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        Page<Emp> p = (Page<Emp>) empList;
        //3.封装到PageResult对象中
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    @Override
    public void save(Emp emp) {
        //1.保存员工的基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        //2.保存员工工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(expr->{
                expr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }
}

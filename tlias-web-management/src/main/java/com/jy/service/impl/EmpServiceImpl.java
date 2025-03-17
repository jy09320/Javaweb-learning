package com.jy.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jy.mapper.EmpExprMapper;
import com.jy.mapper.EmpMapper;
import com.jy.pojo.*;
import com.jy.service.EmpLogService;
import com.jy.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

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
    @Transactional(rollbackFor = {Exception.class})//事务注解
    @Override
    public void save(Emp emp) {
        try {
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
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"新增员工信息："+emp);
            empLogService.insertLog(empLog);
        }
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void deleteById(List<Integer> ids) {
        try {
            //1.删除员工基本信息
            empMapper.deleteById(ids);
            //2.删除员工工作经历
            empExprMapper.deleteByEmpIds(ids);
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"删除员工信息："+ids);
            empLogService.insertLog(empLog);
        }
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getInfo(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        Integer emp_id= emp.getId();
        if(!CollectionUtils.isEmpty(emp.getExprList())){
            emp.getExprList().forEach(expr->{
                expr.setEmpId(emp_id);
            });
            empExprMapper.insertBatch(emp.getExprList());
        }
    }
}

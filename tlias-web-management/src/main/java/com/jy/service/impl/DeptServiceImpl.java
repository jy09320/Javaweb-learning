package com.jy.service.impl;
import com.itheima.pojo.Dept;
import com.jy.mapper.DeptMapper;
import com.jy.mapper.EmpMapper;
import com.jy.pojo.DeptHasEmployeesException;
import com.jy.pojo.Emp;
import com.jy.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void deleteById(Integer id) {
        List<Emp> emps = empMapper.getBydeptId(id);
        if (!emps.isEmpty()) {
            throw new DeptHasEmployeesException("对不起, 该部门下有员工, 不能直接删除");
        }
        deptMapper.deleteById(id);
    }
    @Override
    public void save(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.save(dept);
    }
    @Override
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}

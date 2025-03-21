package com.jy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jy.mapper.ClazzMapper;
import com.jy.mapper.StudentMapper;
import com.jy.pojo.*;
import com.jy.service.ClazzService;
import com.jy.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private EmpLogService empLogService;
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        List<Clazz> clazzList = clazzMapper.page(clazzQueryParam);
        for(Clazz clazz:clazzList){
            if(LocalDate.now().isAfter(clazz.getEndDate())){
                clazz.setStatus("已结课");
            }
            else if (LocalDate.now().isBefore(clazz.getBeginDate())){
                clazz.setStatus("未开班");
            }
            else {
                clazz.setStatus("在读中");
            }
        }
            Page<Clazz> page = (Page<Clazz>) clazzList;
            return new PageResult<>(page.getTotal(),page.getResult());
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void deleteById(Integer id) {
        List<Student> students = studentMapper.getStudentsByClassId(id);
        if (!students.isEmpty()) {
            throw new ClassHasStudentsException("对不起, 该班级下有学生, 不能直接删除");
        }
        try {
            clazzMapper.deleteById(id);
        }
        finally {
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(),"删除班级信息："+id);
            empLogService.insertLog(empLog);
        }
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.save(clazz);
    }

    @Override
    public Clazz getInfo(Integer id) {
        Clazz clazz = clazzMapper.getById(id);
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        return clazz;
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Override
    public List<Clazz> list() {
        return  clazzMapper.list();
    }
}

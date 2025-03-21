package com.jy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jy.mapper.StudentMapper;
import com.jy.pojo.EmpLog;
import com.jy.pojo.PageResult;
import com.jy.pojo.Student;
import com.jy.pojo.StudentQueryParam;
import com.jy.service.EmpLogService;
import com.jy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private EmpLogService empLogService;
    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        List<Student> studentlist = studentMapper.page(studentQueryParam);
        Page<Student> page = (Page<Student>) studentlist;
        return new PageResult<>(page.getTotal(),page.getResult());
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        try {
            studentMapper.delete(ids);
        }
        finally {
            empLogService.insertLog(new EmpLog(null, LocalDateTime.now(),"删除学生信息："+ids));
        }
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        student.setViolationCount((short) 0);
        student.setViolationScore((short) 0);
        try {
            studentMapper.save(student);
        }
        finally {
            empLogService.insertLog(new EmpLog(null, LocalDateTime.now(),"新增学生信息："+student));
        }

    }

    @Override
    public Student getInfo(Integer id) {
        return studentMapper.getById(id);
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Student student) {
        try {
            student.setUpdateTime(LocalDateTime.now());
            studentMapper.update(student);
        }
        finally {
            empLogService.insertLog(new EmpLog(null, LocalDateTime.now(), "修改学生信息：" + student));
        }
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void updateViolation(Integer id, Short score) {
        try {
            Student student = studentMapper.getById(id);
            short violationScore = (short) (student.getViolationScore()-(short)score);
            short violationCount = (short) (student.getViolationCount()+(short)1);
            student.setViolationScore(violationScore);
            student.setViolationCount(violationCount);
            student.setUpdateTime(LocalDateTime.now());
            studentMapper.updateviolation(student);
        }
        finally {
            empLogService.insertLog(new EmpLog(null, LocalDateTime.now(), "更新违纪：" + id));
        }
    }
}

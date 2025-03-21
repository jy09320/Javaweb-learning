package com.jy.service;

import com.jy.pojo.PageResult;
import com.jy.pojo.Student;
import com.jy.pojo.StudentQueryParam;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {

    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void delete(List<Integer> ids);

    void save(Student student);

    Student getInfo(Integer id);

    void update(Student student);

    void updateViolation(Integer id, Short score);
}

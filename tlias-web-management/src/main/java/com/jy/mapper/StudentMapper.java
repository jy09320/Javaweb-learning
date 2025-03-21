package com.jy.mapper;

import com.jy.pojo.Student;
import com.jy.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> getStudentsByClassId(Integer id);

    List<Student> page(StudentQueryParam studentQueryParam);

    void delete(List<Integer> ids);

    void save(Student student);

    Student getById(Integer id);

    void update(Student student);

    void updateviolation(Student student);
}

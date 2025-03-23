package com.jy.mapper;

import com.jy.pojo.Student;
import com.jy.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> getStudentsByClassId(Integer id);

    List<Student> page(StudentQueryParam studentQueryParam);

    void delete(List<Integer> ids);

    void save(Student student);

    Student getById(Integer id);

    void update(Student student);

    void updateviolation(Student student);
    @MapKey("degree")
    List<Map> countStudentDegreeData();
    @MapKey("clazz")
    List<Map<String, Object>> countStudentCountData();
}

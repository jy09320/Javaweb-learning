package com.jy.service;
import com.itheima.pojo.Dept;
import java.util.List;

public interface DeptService {
    //查询所有的部门数据
    List<Dept> findAll();
    //根据id删除部门
    void deleteById(Integer id);
    //保存部门数据
    void save(Dept dept);
    //根据id查询部门
    Dept findById(Integer id);

    void update(Dept dept);
}

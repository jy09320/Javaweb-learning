package com.jy.service;

import com.jy.pojo.Emp;
import com.jy.pojo.EmpQueryParam;
import com.jy.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

//    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void deleteById(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    List<Emp> list();
}


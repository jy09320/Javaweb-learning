package com.jy.service;

import com.jy.pojo.Emp;
import com.jy.pojo.PageResult;

import java.time.LocalDate;

public interface EmpService {

    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);
}

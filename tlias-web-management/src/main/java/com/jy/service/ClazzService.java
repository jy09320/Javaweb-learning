package com.jy.service;

import com.jy.pojo.Clazz;
import com.jy.pojo.ClazzQueryParam;
import com.jy.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    void deleteById(Integer id);

    void save(Clazz clazz);

    Clazz getInfo(Integer id);

    void update(Clazz clazz);

    List<Clazz> list();
}

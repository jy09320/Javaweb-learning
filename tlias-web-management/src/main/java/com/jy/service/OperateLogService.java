package com.jy.service;


import com.jy.pojo.OperateLog;
import com.jy.pojo.PageResult;

public interface OperateLogService {
    PageResult<OperateLog> page(Integer page, Integer pageSize);
}

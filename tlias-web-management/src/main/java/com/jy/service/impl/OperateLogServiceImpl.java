package com.jy.service.impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jy.mapper.OperateLogMapper;
import com.jy.pojo.OperateLog;
import com.jy.pojo.PageResult;
import com.jy.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateLogServiceImpl implements OperateLogService {
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Override
    public PageResult<OperateLog> page(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<OperateLog> operateLogs = operateLogMapper.page();
        Page<OperateLog> p=(Page<OperateLog>)operateLogs;
        return new PageResult<>(p.getTotal(),p.getResult());
    }
}

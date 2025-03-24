package com.jy.service.impl;

import com.jy.mapper.EmpMapper;
import com.jy.pojo.Emp;
import com.jy.pojo.LogInfo;
import com.jy.service.LoginService;
import com.jy.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public LogInfo login(Emp emp) {
        log.info("登录用户:{}",emp);
        Emp e=empMapper.getByUsernameAndPassword(emp);
        if (e!=null){
            Map<String, Object> map = new HashMap<>();
            map.put("username",e.getUsername());
            map.put("id",e.getId());
            String jwt = JwtUtils.generateJwt(map);
            return new LogInfo(e.getId(),e.getUsername(),e.getPassword(),jwt);
        }
        return null;
    }
}

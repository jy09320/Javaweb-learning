package com.jy;


import com.jy.mapper.EmpMapper;
import com.jy.pojo.Emp;
import org.junit.jupiter.api.Test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TliasWebManagementApplicationTests {
    @Autowired
    public EmpMapper empMapper;

}
class LogTest {
    private static final Logger log = LoggerFactory.getLogger(LogTest.class);
    @Test
    public void testLog() {
        log.debug("开始计算：");
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        log.info("计算结果为：{}", sum);
        log.debug("计算结束！");
    }
}

package com.itheima;

import com.example.TokenParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootWebTests {
    @Autowired
    private TokenParser tokenParser;
    @Test
    void contextLoads() {
        tokenParser.parse();
    }

}

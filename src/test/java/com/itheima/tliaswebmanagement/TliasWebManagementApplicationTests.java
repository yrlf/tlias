package com.itheima.tliaswebmanagement;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class TliasWebManagementApplicationTests {

    @Test
    void testGenJWT() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name", "tom");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "itheima") // 设置签名算法
                .setClaims(claims) // 自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) //有效期
                .compact(); //获得返回值
        System.out.println(jwt);

    }


}

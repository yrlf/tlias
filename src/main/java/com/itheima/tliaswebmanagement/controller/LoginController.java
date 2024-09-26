package com.itheima.tliaswebmanagement.controller;

import com.itheima.tliaswebmanagement.pojo.Emp;
import com.itheima.tliaswebmanagement.pojo.Result;
import com.itheima.tliaswebmanagement.service.EmpService;
import com.itheima.tliaswebmanagement.utils.JwtUtils;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping
    public Result login(@RequestBody Emp emp){
        log.info("员工登录 {}", emp);
        Emp e = empService.login(emp);
        // 如果登录成功, 生成 JWT 令牌并下发, 失败则返回错误信息
        if (e != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name",e.getName());
            claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        return Result.error("用户名或密码错误");

    }
}

package com.itheima.tliaswebmanagement.controller;

import com.itheima.tliaswebmanagement.pojo.Dept;
import com.itheima.tliaswebmanagement.pojo.Result;
import com.itheima.tliaswebmanagement.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:90")  // 允许前端来源的跨域请求
@RestController
@RequestMapping("/depts") // 公共部分可以抽取在 Class 上, 简化代码

public class DeptController {
    @Autowired
    private DeptService deptService;

    //private static Logger log = LoggerFactor.getLogger(DeptController.class);
    // @RequestMapping(value = "/depts", method = RequestMethod.GET)
    // 也可以写下面的
    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");

        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据 id 来删除部门: {}", id);
        deptService.delete(id);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Dept dept){ // 用实体类来接受 Json 格式, 通过@RequestBody 修饰来自动封装
        log.info("新增部门: {}", dept);
        deptService.add(dept);
        return Result.success();
    }



}

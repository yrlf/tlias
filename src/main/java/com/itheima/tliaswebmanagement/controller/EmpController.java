package com.itheima.tliaswebmanagement.controller;
import ch.qos.logback.classic.Logger;
import com.itheima.tliaswebmanagement.pojo.Emp;
import com.itheima.tliaswebmanagement.pojo.Result;
import com.itheima.tliaswebmanagement.pojo.PageBean;
import com.itheima.tliaswebmanagement.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:90")  // 允许前端来源的跨域请求
@RestController
@RequestMapping("/emps") // 公共部分可以抽取在 Class 上, 简化代码
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender, @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate begin, @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate end){

        log.info("分页查询, 参数 {}, {}, {}, {} {}, {}", name, gender, begin, end, page, pageSize);
        PageBean pageBean = empService.page(name, gender, begin, end, page, pageSize);

        return Result.success(pageBean);
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除员工操作, ids: {} ", ids);
        empService.delete(ids);
        return Result.success();

    }

    @PostMapping
    public Result save(@RequestBody Emp emp){

        log.info("新增员工操作: {} ", emp);
        empService.save(emp);

        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据员工 ID 查找员工信息: {}", id);

        Emp emp = empService.getById(id);

        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息 {}", emp);

        empService.update(emp);
        return Result.success();



    }

}

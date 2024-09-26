package com.itheima.tliaswebmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.tliaswebmanagement.mapper.EmpMapper;
import com.itheima.tliaswebmanagement.pojo.Emp;
import com.itheima.tliaswebmanagement.pojo.PageBean;
import com.itheima.tliaswebmanagement.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean page(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize){
//        // 1. 获取总记录数
//        Long total = empMapper.count();
//        // 2. 获取分页查询的列表
//        List<Emp> empList = empMapper.page((page-1)*pageSize, pageSize);

        // 使用 PageHelper 插件来简化
        // 1 设置分页参数
        PageHelper.startPage(page, pageSize);
        // 2. 正常查询 + 转成 PageList
        List<Emp> empList = empMapper.list(name,
                gender, begin,end);
        Page<Emp> pageList = (Page<Emp>) empList;

        // 3. 封装 pageBean
        PageBean pageBean = new PageBean(pageList.getTotal(), pageList.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp){
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.save(emp);
    }

    @Override
    public Emp getById(Integer id){
        Emp emp = empMapper.getById(id);
        return emp;
    }

    @Override
    public void update(Emp emp){
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
        return;
    }
    @Override
    public Emp login(Emp emp){
        Emp e = empMapper.getByUsernameAndPassword(emp);
        return e;
    }
}

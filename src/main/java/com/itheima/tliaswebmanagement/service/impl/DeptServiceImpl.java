package com.itheima.tliaswebmanagement.service.impl;

import com.itheima.tliaswebmanagement.mapper.DeptMapper;
import com.itheima.tliaswebmanagement.pojo.Dept;
import com.itheima.tliaswebmanagement.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> list(){
        return deptMapper.list();
    }

    @Override
    public void delete(Integer id){
        deptMapper.deleteById(id);
        return;
    }

    @Override
    public void add(Dept dept){
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
        return;
    }

}

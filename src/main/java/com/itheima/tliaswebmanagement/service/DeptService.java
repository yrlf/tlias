package com.itheima.tliaswebmanagement.service;

import com.itheima.tliaswebmanagement.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> list();
    void delete(Integer id);

    void add(Dept dept);
}

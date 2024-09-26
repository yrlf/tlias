package com.itheima.tliaswebmanagement.service;

import com.itheima.tliaswebmanagement.pojo.PageBean;
import com.itheima.tliaswebmanagement.pojo.Emp;
import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    public PageBean page(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize);


    public void delete(List<Integer> ids);

    public void save(Emp emp);

    public Emp getById(Integer id);

    public void update(Emp emp);

    public Emp login(Emp emp);
}

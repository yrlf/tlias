package com.itheima.tliaswebmanagement.mapper;
import com.itheima.tliaswebmanagement.pojo.Result;

import com.itheima.tliaswebmanagement.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
//
//
//    @Select("select count(*) from emp")
//    public Long count();
//
//    @Select("select * from emp limit #{start}, #{pageSize} ")
//    public List<Emp> page(Integer start, Integer pageSize);
//    @Select("select * from emp")

    // 使用动态 SQL + XML 配置
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    public void delete(List<Integer> ids);


    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            "values(#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime} );")
    public void save(Emp emp);

    @Select("select * from emp where id = #{id} ")
    public Emp getById(Integer id);

    public void update(Emp emp);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);
}

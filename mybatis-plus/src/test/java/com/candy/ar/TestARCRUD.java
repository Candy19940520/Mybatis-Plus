package com.candy.ar;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.candy.bean.EmployeeAr;
import com.candy.config.SpringConfig;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/**
 * @author Candy
 * @create 2021-04-25 17:27
 * 使用AR模式进行增删改查
 */
@SpringJUnitConfig(classes = SpringConfig.class)//加载配置文件
public class TestARCRUD {

    @Test
    public void testAdd(){
        EmployeeAr employee = new EmployeeAr();
        employee.setLastName("子剑");
        employee.setAge(26);
        employee.setGender("1");
        System.out.println(employee.insert());
    }

    @Test
    public void testUpdate(){
        EmployeeAr employee = new EmployeeAr();
        employee.setEmployeeId(22);
        employee.setLastName("AR");
        employee.updateById();
    }

    @Test
    public void testSelect(){
        EmployeeAr employee = new EmployeeAr();
        System.out.println(employee.selectById(25));
        //条件查询
        QueryWrapper<EmployeeAr> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("last_name","Tom");
        List<EmployeeAr> employeeArs = employee.selectList(queryWrapper);
        System.out.println(employeeArs);
        //分页查询
        QueryWrapper<EmployeeAr> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.like("email","t");
        Page<EmployeeAr> employeeArPage = employee.selectPage(new Page<>(0, 2), queryWrapper2);
        System.out.println(employeeArPage.getRecords());
    }

    @Test
    public void testDelete(){
        EmployeeAr employee = new EmployeeAr();
        System.out.println(employee.deleteById(100));
        //条件删除
        UpdateWrapper<EmployeeAr> queryWrapper = new UpdateWrapper<>();
        queryWrapper.eq("last_name","MP");
        employee.delete(queryWrapper);
    }



}

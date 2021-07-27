package com.candy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.candy.bean.Employee;

/**
 * @author Candy
 * @create 2021-04-25 18:15
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    int deleteEmployee();

}

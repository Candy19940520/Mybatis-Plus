package com.candy;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.candy.bean.Employee;
import com.candy.config.SpringConfig;
import com.candy.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Candy
 * @create 2021-04-25 17:27
 */
@SpringJUnitConfig(classes = SpringConfig.class)//加载配置文件
public class TestCRUD {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void testAdd(){
        Employee employee = new Employee("MP", "tao.z@qq.com", "1", 27);
        Integer insert = employeeMapper.insert(employee);
        System.out.println("插入"+insert+"条数据，他的主键是："+employee.getEmployeeId());
    }

    /**
     * 使用Map和QueryWrapper作为条件构造器的区别？
     * 1.功能相似，但是在SQL语句级别有差距
     * SELECT * FROM employee WHERE (last_name = ? AND age = ?)
     * SELECT * FROM employee WHERE last_name = ? AND age = ?
     */
    @Test
    public void testSelect(){
        Map<String,Object> selectByMap = new HashMap<>();
        selectByMap.put("last_name","ZT");
        selectByMap.put("age",27);
        List<Employee> selectByMaps = employeeMapper.selectByMap(selectByMap);//相当于QueryWrapper
        System.out.println("selectByMaps--------------->"+selectByMaps);

        Page<Employee> selectPage = employeeMapper.selectPage(new Page<>(2,2), null);
        System.out.println("selectPage------------------>"+selectPage.getRecords());

        QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
        employeeQueryWrapper.eq("last_name","MP")
                .eq("age",27);
        List<Map<String, Object>> maps = employeeMapper.selectMaps(employeeQueryWrapper);
        System.out.println("selectMaps------------------>"+maps);
    }

    @Test
    public void testSelectOr(){
//         WHERE age = ? AND (last_name LIKE ? OR email LIKE ?)
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("age",27)
                .and(i -> i.like("last_name","T").or().like("email","a"));
        List<Employee> employees = employeeMapper.selectList(queryWrapper);

//        WHERE (gender = ? AND age = ?) OR (email LIKE ? OR last_name LIKE ?)
        QueryWrapper<Employee> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.or(i -> i.eq("gender",1).eq("age",27))
                .or(i -> i.like("email","a").or().like("last_name","T"));
        employeeMapper.selectList(queryWrapper3);
    }


    @Test
    public void testAlarmDelete() throws Exception {
//        Employee employee = employeeMapper.selectById(26);
//        employee.setLastName("aaa");
//        Thread.sleep(5000);
//        employeeMapper.updateById(employee);
    }

    @Test
    public void test(){
        int i = employeeMapper.deleteEmployee();//SQL注入器
        System.out.println(i);

//        int delete = employeeMapper.deleteById(1);
//        System.out.println(delete);

//        List<Employee> employees = employeeMapper.selectList(null);
//        System.out.println(employees.size());
    }

}

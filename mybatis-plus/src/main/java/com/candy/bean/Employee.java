package com.candy.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author Candy
 * @create 2021-04-25 16:18
 */
@Data
//@TableName(value = "表名")
public class Employee {

    @TableId(type = IdType.AUTO)//此注解有个主要作用，用来接收返回的主键id(如果主键名称默认是id，则可以不写)
//    @TableField(value = "employee_id",exist = false)
    private Integer employeeId ;
    private String lastName;
    private String email ;
    private String gender ;
    private Integer age ;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @Version
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
    @TableLogic
    private Integer isDelete ;

    public Employee() {}

    public Employee(String lastName, String email, String gender, Integer age) {
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }

    public Employee(Integer employeeId, String lastName, String email, String gender, Integer age) {
        this.employeeId = employeeId;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }
}

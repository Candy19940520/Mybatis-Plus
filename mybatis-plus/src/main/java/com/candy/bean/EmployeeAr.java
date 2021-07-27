package com.candy.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @author Candy
 * @create 2021-05-11 9:59
 * 继承Model类，使用AR模式
 */
@Data
@TableName(value = "employee")
public class EmployeeAr extends Model<EmployeeAr> {

    @TableId(type = IdType.AUTO)
    private Integer employeeId;
    private String lastName;
    private String email;
    private String gender;
    private Integer age;
}

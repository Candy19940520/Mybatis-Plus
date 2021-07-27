package com.candy.generator.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Candy
 * @since 2021-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("departments")
public class Departments extends Model<Departments> {

    private static final long serialVersionUID = 1L;

    /**
     * 部门编号id
     */
    @TableId(value = "department_id", type = IdType.AUTO)
    private Integer departmentId;

    /**
     * 部门名称
     */
    @TableField("department_name")
    private String departmentName;

    /**
     * 部门领导员工编号ID
     */
    @TableField("manager_id")
    private Integer managerId;

    /**
     * 位置编号id
     */
    @TableField("location_id")
    private Integer locationId;


    @Override
    protected Serializable pkVal() {
        return this.departmentId;
    }

}

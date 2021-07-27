package com.candy.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 告警表
 * </p>
 *
 * @author Candy
 * @since 2021-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("immp_alarm")
public class ImmpAlarm extends Model<ImmpAlarm> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 告警编号（IMOC中的告警uuid）
     */
    @TableField("alarms_id")
    private String alarmsId;

    /**
     * 告警级别（IMOC中的告警uuid）
     */
    @TableField("alarm_level")
    private Integer alarmLevel;

    /**
     * 告警信息（IMOC告警信息）
     */
    @TableField("alarm_description")
    private String alarmDescription;

    /**
     * 告警产生时间（IMOC告警创建时间戳）
     */
    @TableField("create_time_stamp")
    private String createTimeStamp;

    /**
     * ci名称（IMOC中ci名称）
     */
    @TableField("monitor_object")
    private String monitorObject;

    /**
     * IMOC中ci所属命名空间
     */
    @TableField("name_space")
    private String nameSpace;

    /**
     * 设备等级代码（从设备基本信息表获取）
     */
    @TableField("dev_level_code")
    private String devLevelCode;

    /**
     * 设备等级（从设备基本信息表获取）
     */
    @TableField("dev_level_name")
    private String devLevelName;

    /**
     * 所属路段（从设备基本信息表获取）
     */
    @TableField("road_partid")
    private String roadPartid;

    /**
     * 告警消除时间（）
     */
    @TableField("eliminate_time")
    private LocalDateTime eliminateTime;

    /**
     * 是否消除
     */
    @TableField("is_eliminate")
    private Integer isEliminate;

    /**
     * 是否转工单
     */
    @TableField("is_workorder")
    private Integer isWorkorder;

    /**
     * 总公司编码(从设备基本信息表获取）
     */
    @TableField("unit_code")
    private String unitCode;

    /**
     * 总公司名称（从设备基本信息表获取）
     */
    @TableField("unit_name")
    private String unitName;

    /**
     * 路公司编码（从设备基本信息表获取）
     */
    @TableField("companyid")
    private String companyid;

    /**
     * 路公司名称（从设备基本信息表获取）
     */
    @TableField("company_name")
    private String companyName;

    /**
     * 监控（分）中心编码（从设备基本信息表获取）
     */
    @TableField("mon_centerid")
    private String monCenterid;

    /**
     * 监控（分）中心名称（从设备基本信息表获取）
     */
    @TableField("mon_center_name")
    private String monCenterName;

    /**
     * 管理部门代码（从设备基本信息表获取）
     */
    @TableField("manage_dept_id")
    private String manageDeptId;

    /**
     * 管理部门名称（从设备基本信息表获取）
     */
    @TableField("manage_dept_name")
    private String manageDeptName;

    /**
     * 集团公司编码
     */
    @TableField("group_code")
    private String groupCode;

    /**
     * 集团公司名称
     */
    @TableField("group_name")
    private String groupName;

    /**
     * 设备编码
     */
    @TableField("dev_code")
    private String devCode;

    /**
     * 告警类型
     */
    @TableField("alarm_type")
    private String alarmType;

    /**
     * 告警创建时间
     */
    @TableField("alarm_create_time")
    private LocalDateTime alarmCreateTime;

    /**
     * 所属路段名称
     */
    @TableField("road_part_name")
    private String roadPartName;

    /**
     * 告警更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 告警来源
     */
    @TableField("source")
    private String source;

    /**
     * 设备类型
     */
    @TableField("dev_type")
    private String devType;

    /**
     * 设备类型名称
     */
    @TableField("dev_type_name")
    private String devTypeName;

    /**
     * 告警入库时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 设备名称
     */
    @TableField("dev_name")
    private String devName;

    /**
     * 关键词
     */
    @TableField("key_words")
    private String keyWords;

    /**
     * 关联自定义告警表id主键
     */
    @TableField("immp_alarm_library_id")
    private Long immpAlarmLibraryId;

    /**
     * 是否告警闭环（0：未闭环	1：已闭环）
     */
    @TableField("is_closed_loop")
    private String isClosedLoop;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

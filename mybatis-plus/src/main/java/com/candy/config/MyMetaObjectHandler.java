package com.candy.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @author Candy
 * @create 2021-05-13 10:30
 * 公共字段自动填充(例如新增、修改时候的"修改时间字段、新增时间字段")
 */
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler{

    /**
     * 插入操作自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("插入操作自动填充-----------------");
        Object createTime = getFieldValByName("createTime", metaObject);
        if(createTime == null){
            setFieldValByName("createTime",new Date(),metaObject);
        }
    }

    /**
     * 修改操作自动填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("修改操作自动填充-----------------");
        Object updateTime = getFieldValByName("updateTime", metaObject);
        if(updateTime == null){
            setFieldValByName("updateTime",new Date(),metaObject);
        }
    }
}

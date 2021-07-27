package com.candy.alarmTest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.candy.bean.ImmpAlarm;
import com.candy.config.SpringConfig;
import com.candy.mapper.ImmpAlarmMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Candy
 * @create 2021-04-25 17:27
 */
@SpringJUnitConfig(classes = SpringConfig.class)//加载配置文件
public class TestAlarm {

    @Autowired
    private ImmpAlarmMapper immpAlarmMapper;

    @Test
    public void testAlarmDelete(){
        QueryWrapper<ImmpAlarm> immpAlarmQueryWrapper = new QueryWrapper<>();
        immpAlarmQueryWrapper.select("id");
        immpAlarmQueryWrapper.eq("is_eliminate",1);
        Page<ImmpAlarm> immpAlarmPage = immpAlarmMapper.selectPage(new Page<>(1, 10000), immpAlarmQueryWrapper);
        long pages = immpAlarmPage.getPages();//总页数
        System.out.println("总页数-------------->"+pages);

        for (long i = pages; i >= 1; i--) {
            Page<ImmpAlarm> alarmPage = immpAlarmMapper.selectPage(new Page<>(i, 10000), immpAlarmQueryWrapper);
            List<ImmpAlarm> records = alarmPage.getRecords();
            List<Long> longList = new ArrayList<>();
            for (ImmpAlarm record : records) {
                longList.add(record.getId());
            }
            int deleteBatchIds = immpAlarmMapper.deleteBatchIds(longList);
            System.out.println("删除次数------>"+i+"----------------每次删除条数："+deleteBatchIds);
        }
    }




}

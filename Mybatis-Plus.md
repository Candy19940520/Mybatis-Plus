# Mybatis-Plus

## 一、简介集成Mybatis-Plus

### 1.代码及文档发布地址

官方地址: http://mp.baomidou.com

代码发布地址: 

Github: https://github.com/baomidou/mybatis-plus 

Gitee: https://gitee.com/baomidou/mybatis-plus

文档发布地址:

 http://mp.baomidou.com/#/?id=%E7%AE%80%E4%BB%8B

### 2.引入依赖

```
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus</artifactId>
    <version>3.4.2</version>
</dependency>

<!-- lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.12</version>
    <scope>provided</scope>
</dependency>

<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.7.0</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.49</version>
</dependency>
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.2.3</version>
</dependency>


<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.3.5</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>5.3.5</version>
</dependency>
<!-- 如果使用Spring整合其他持久层框架，需要用到-->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-orm</artifactId>
    <version>5.3.5</version>
</dependency>
<!--日志相关-->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-api</artifactId>
    <version>2.14.1</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.14.1</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-slf4j-impl</artifactId>
    <version>2.14.1</version>
<scope>test</scope>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.30</version>
</dependency>
```

### 3.编写Spring和Mybatis-Plus的集成配置

![集成Mybatis-Plus](C:\Users\61788\Desktop\Mybatis-Plus\集成Mybatis-Plus.png)



**具体代码查看项目mybatis-plus**

**注意事项：**

1. 需要在实体中使用@TableId指定主键(或者指定全局配置)
2. 如果实体类和表名不一致，需要使用@TableName(value = "表名")(或者指定全局配置)

### 4.Mybatis-Plus和原生Mybatis的自定义配置

其余配置查看官方文档：[https://mp.baomidou.com/config/#%E4%BD%BF%E7%94%A8%E6%96%B9%E5%BC%8F](https://mp.baomidou.com/config/#使用方式)

```
package com.candy.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Candy
 * @create 2021-04-25 16:39
 * Mybatis-Plus的自定义配置
 */
@Configuration
@MapperScan(basePackages = {"com.candy.mapper"})
public class MybatisPlusConfig {

    //Mybatis-Plus的全局配置
    @Bean
    public GlobalConfig globalConfig(GlobalConfig.DbConfig dbConfig){
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setDbConfig(dbConfig);
        return globalConfig;
    }

    @Bean
    public GlobalConfig.DbConfig dbConfig(){
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        //指定全局主键生成策略
        dbConfig.setIdType(IdType.AUTO);
        return dbConfig;
    }

    /**
     * Mybatis的原生全局配置,必须在MybatisSqlSessionFactoryBean引用
     * 原生Mybatis的配置类叫org.apache.ibatis.session.Configuration,这里使用MybatisConfiguration包装了一下
     * MybatisConfiguration extends Configuration
     */
    @Bean
    public MybatisConfiguration mybatisConfiguration(){
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        mybatisConfiguration.setMapUnderscoreToCamelCase(true);//驼峰命名可以不写,默认true
        return mybatisConfiguration;
    }

    //分页插件
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}

```

Mybatis-Plus源码中默认指定驼峰属性为：true

![驼峰命名](C:\Users\61788\Desktop\Mybatis-Plus\驼峰命名.png)



### 5.逻辑删除

**执行XXXMapper.deleteXXX();方法的时候，会自动变成修改操作**

**执行任何查询操作的时候，会自动查询未删除的数据**

- 设置GlobalConfig.DbConfig

  ![逻辑删除](C:\Users\61788\Desktop\Mybatis-Plus\逻辑删除.png)

- 设置实体逻辑删除属性

  ```
  @TableLogic
  private String isDelete ;
  ```

### 6.公共字段自动填充(例如新增、修改时候的"修改时间字段、新增时间字段")

1. 自定义类实现MetaObjectHandler接口

   ```
   package com.candy.config;
   
   import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
   import lombok.extern.slf4j.Slf4j;
   import org.apache.ibatis.reflection.MetaObject;
   
   import java.time.LocalDateTime;
   
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
               this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
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
               this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐)
           }
       }
   }
   ```

2. 标记实体自动填充字段，并设置类型

   ```
   /**
        * 默认不处理
        */
       DEFAULT,
       /**
        * 插入时填充字段
        */
       INSERT,
       /**
        * 更新时填充字段
        */
       UPDATE,
       /**
        * 插入和更新时填充字段
        */
       INSERT_UPDATE
   @TableField(fill = FieldFill.INSERT)
   private Date createTime;
   
   @TableField(fill = FieldFill.UPDATE)
   private Date updateTime;
   ```

3. 设置全局配置GlobalConfig的setMetaObjectHandler

   ![全局配置](C:\Users\61788\Desktop\Mybatis-Plus\全局配置.png)










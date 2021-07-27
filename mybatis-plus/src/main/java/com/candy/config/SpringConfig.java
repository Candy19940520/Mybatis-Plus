package com.candy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Candy
 * @create 2021-04-25 16:43
 */
@Configuration
@ComponentScan(basePackages = {"com.candy.bean","com.candy.config","com.candy.mapper","com.candy.service"})
@Import(JDBCconfig.class)
@EnableTransactionManagement(proxyTargetClass = true)
public class SpringConfig {
}

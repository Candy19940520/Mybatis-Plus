package com.candy.config.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @author Candy
 * @create 2021-05-13 8:09
 */
public class deleteEmployee extends AbstractMethod{

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = "DELETE FROM "+tableInfo.getTableName()+" WHERE last_name='MP'";
        SqlSource sqlSource = languageDriver.createSqlSource(configuration,sql,modelClass);
        return this.addInsertMappedStatement(mapperClass,modelClass,"deleteEmployee",sqlSource,new NoKeyGenerator(), null, null);
    }
}

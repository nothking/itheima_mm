package com.itheima.mm.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;

/**
 * @Author cl
 * @Date 2020/11/20 14:47:02
 */
public class DruidDataSourceFactory extends PooledDataSourceFactory {
    public DruidDataSourceFactory() {
        this.dataSource = new DruidDataSource();
    }
}

package study.mengduo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @aothor mengDuo
 * @date 2020/3/10 21:18
 */
@Configuration
@EnableConfigurationProperties(DuridProperties.class)
public class DuridDataSourceConfig {
    @Bean
    public DruidDataSource dataSource(DuridProperties duridProperties){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(duridProperties.getUrl());
        dataSource.setUsername(duridProperties.getUsername());
        dataSource.setPassword(duridProperties.getPassword());
        dataSource.setDriverClassName(duridProperties.getDriverClassName());
        dataSource.setInitialSize(duridProperties.getInitialSize());
        dataSource.setMinIdle(duridProperties.getMinIdle());
        dataSource.setMaxActive(duridProperties.getMaxActive());
        dataSource.setMaxWait(duridProperties.getMaxWait());
        return dataSource;
    }
}

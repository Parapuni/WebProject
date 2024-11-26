package cmt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * 数据源配置类
 * 数据持久化使用JDBC
 */
@Configuration
public class DataSourceConfig {

    /**
     * 数据源使用内嵌式H2数据库
     */
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().
                setName("csdb").
                setType(EmbeddedDatabaseType.H2).
                setScriptEncoding("UTF-8").
                addScript("classpath:schema.sql").
                build();
    }

    /**
     * 配置jdbc模板，使用基本模板
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}

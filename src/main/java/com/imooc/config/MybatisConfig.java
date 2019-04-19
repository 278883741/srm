package com.imooc.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ConditionalOnClass({ EnableTransactionManagement.class })
@AutoConfigureAfter({ DataSourceConfig.class })
public class MybatisConfig {
    @Value("${mybatis.typeAliasesPackage}")
    private String typeAliasesPackage;


    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;


    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

        // SET dataSource
        sessionFactory.setDataSource(dataSource);

        /** 设置Mybatis 实体类别名扫描包路径 */
        sessionFactory.setTypeAliasesPackage("com.imooc.model");

        Resource[] mapperLocation = new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/*.xml");
        sessionFactory.setMapperLocations(mapperLocation);
        sessionFactory.setPlugins(new Interceptor[]{pageHelper()});

        return sessionFactory;
    }
    @Bean
    public PageHelper pageHelper(){
        // PageHelper首先将前端传递的参数保存到page这个对象中，接着将page的副本存放入ThreadLoacl中，这样可以保证分页的时候，参数互不影响，接着利用了mybatis提供的拦截器，取得ThreadLocal的值，重新拼装分页SQL，完成分页。
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();

        // 分页插件会自动检测当前的数据库链接，自动选择合适的分页方式。 你可以配置helperDialect属性来指定分页插件使用哪种方言。配置时，可以使用下面的缩写值：
        // oracle,mysql,mariadb,sqlite,hsqldb,postgresql,db2,sqlserver,informix,h2,sqlserver2012,derby
        properties.put("dialect","mysql");
        properties.put("offsetAsPageNum",true);

        // 设置为true时，使用RowBounds分页会进行count查询
        properties.put("rowBoundsWithCount",true);

        // 设置为true时，如果pageSize=0或者RowBounds.limit = 0就会查询出全部的结果，相当于没有执行分页查询，但是返回结果仍然是Page类型
        properties.put("pageSizeZero",true);

        // 启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
        // 禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据
        properties.put("reasonable",true);

        // 支持通过 Mapper 接口参数来传递分页参数，默认值false，分页插件会从查询方法的参数值中，自动根据上面 params 配置的字段中取值
        properties.put("supportMethodsArguments",false);
        properties.put("returnPageInfo","none");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
    // Spring 事务
    @Bean(name = "txManager")
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(dataSource);
        return txManager;
    }


    @Bean(name = "mapperScannerConfigurer")
    public MapperScannerConfigurer MapperScannerConfigurer1() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.aoji.mapper");
        Properties properties = new Properties();
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        properties.setProperty("mappers", Mapper.class.getName());
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}

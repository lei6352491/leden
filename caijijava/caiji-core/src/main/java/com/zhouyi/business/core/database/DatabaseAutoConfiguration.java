/**
 * 
 */
package com.zhouyi.business.core.database;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

/**
 * 
 * 
 *
 */

public class DatabaseAutoConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(DatabaseAutoConfiguration.class);
	@Autowired
	private MybatisProperties properties;
	@Autowired
	private ResourceLoader resourceLoader = new DefaultResourceLoader();
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		try{
			CommonXMLMapperBuilder builder = new CommonXMLMapperBuilder();
			builder.setBaseResultMap("BaseResultMap");
			builder.setBaseTableName("BaseTable");
			builder.setKeyGenMode(KeyGenMode.AUTO_INCREMENT);
			
			SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
			factory.setDataSource(dataSource);
			if(this.properties.getConfigLocation()!=null){
				factory.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
			}
			factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
			factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
			factory.setMapperLocations(builder.builderCommonMapper(this.properties.resolveMapperLocations()));
			
			return factory.getObject();
		}catch(Exception e){
			logger.error("sqlSessionFactory_err",e);
			throw e;
		}
	}
	
}

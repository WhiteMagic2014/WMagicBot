package com.whitemagic2014.bean;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.sqlite.javax.SQLiteConnectionPoolDataSource;

import com.whitemagic2014.pojo.DBVersionTable;
import com.whitemagic2014.pojo.Version;
import com.whitemagic2014.util.Path;

@Configuration
@MapperScan(basePackages = "com.whitemagic2014.dao")
public class SqlLiteConfig {

	@Autowired
	DataSource dataSource;

	
	@Bean(name="sqliteDataSource")
	public DataSource sqliteDataSource() {
		SQLiteConnectionPoolDataSource pool = new SQLiteConnectionPoolDataSource();
		pool.setUrl("jdbc:sqlite:" + Path.getPath() + "botData.db");
		return pool;
	}
	
	
	@Bean
	public SqlSessionFactory sqliteSqlSessionFactory(@Qualifier("sqliteDataSource") DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory
				.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
		return sessionFactory.getObject();
	}

	
	
	@Bean(name="dbversionList")
	public List<DBVersionTable> getDbVersionTables(){
		List<DBVersionTable> result = new ArrayList<DBVersionTable>();
		
		
		DBVersionTable v0_0_2 = new DBVersionTable();
		v0_0_2.setVer(new Version("0.0.2"));
		List<String> sql_0_0_2 = new ArrayList<>();
		sql_0_0_2.add("CREATE TABLE \"user_plan\" (\n" + 
				"  \"id\" INTEGER PRIMARY KEY autoincrement,\n" + 
				"  \"uid\" text(20),\n" + 
				"  \"itemId\" integer,\n" + 
				"  \"itemName\" text(64),\n" + 
				"  \"nowNum\" integer,\n" + 
				"  \"planNum\" integer\n" + 
				");");
		v0_0_2.setSqls(sql_0_0_2);
		result.add(v0_0_2);
		
		DBVersionTable v0_0_3 = new DBVersionTable();
		v0_0_3.setVer(new Version("0.0.3"));
		List<String> sql_0_0_3 = new ArrayList<>();
		sql_0_0_3.add("CREATE TABLE \"can_eat\" (\n" + 
				"  \"id\" INTEGER PRIMARY KEY autoincrement,\n" + 
				"  \"itemName\" text(64),\n" + 
				"  \"can\" integer\n" + 
				");");
		v0_0_3.setSqls(sql_0_0_3);
		result.add(v0_0_3);
		
		
		return result;
	}
	
	
}

package school;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;




@Configuration
public class DataBaseInfo {
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource, 
			ApplicationContext applicationContext)
			throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		// interface 와 xml 이 같은 패키지 내에 있을 경우 사용할 필요가 업다 
		// classpath (리소시스)에다가 mappers라는 폴더 밑에  . **는 폴더가 있을수도 있다.//*Mapper.xml
		// mapper.xml을 따로 할거 아니면 필요가 없다 .
	//	sessionFactory.setMapperLocations(applicationContext.getResources("classpath:mappers/**/*Mapper.xml"));
		sessionFactory.setTypeAliasesPackage("school.domain");
		return sessionFactory.getObject();
	}

	
}

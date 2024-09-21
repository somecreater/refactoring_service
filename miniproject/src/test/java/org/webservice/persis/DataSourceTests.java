package org.webservice.persis;


import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DataSourceTests {
	@Setter(onMethod_= {@Autowired})
	private DataSource dataSource;
	
	@Setter(onMethod_= {@Autowired})
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	@DisplayName("마이 바티스 연결 테스트")
	public void testMyBatis() {
		try(SqlSession session=sqlSessionFactory.openSession();
				Connection con=session.getConnection();
				){
			log.info(session);
			log.info(con);
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
	/*
	@Test
	public void testCOnnection() {
		
		try(Connection con=dataSource.getConnection()){
			log.info(con);
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}*/
	
}
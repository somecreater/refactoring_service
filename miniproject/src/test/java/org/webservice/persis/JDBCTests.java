package org.webservice.persis;



import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.MAP;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.webservice.*;

import lombok.extern.log4j.Log4j;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
public class JDBCTests {


	@Autowired
	private DataSource dataSource;

	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;

	@Autowired
	private EntityManager entityManager;
/*
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			//e.printStackTrace();
			log.info(e.getMessage());
		}
	}
	
	//@Test
	@DisplayName("sqld 연결 테스트")
	public void testConnection() {
		try(Connection con=
				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","c##book_ex","book_ex")){
			//log.info(con);
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
*/
	@Test
	@DisplayName("MySql+Hibernate(JPA)+JAVA 연결 테스트")
	public void testJPA(){

		// DataSource 확인
		assertThat(dataSource).isNotNull();
		System.out.println("DataSource 연결 성공");

		// EntityManagerFactory 확인
		assertThat(entityManagerFactory).isNotNull();
		System.out.println("EntityManagerFactory 연결 성공");

		// EntityManager 확인
		assertThat(entityManager).isNotNull();
		System.out.println("EntityManager 연결 성공");

		// 실제 MySQL 연결 확인
		String query = "SELECT 1";
		Integer result = entityManager.createNativeQuery(query).getSingleResult() != null ? 1 : 0;

		// 결과가 1이어야 정상
		assertThat(result).isEqualTo(1);
        System.out.println("MySQL 연결 성공");
	}

}

package org.webservice.persis;



import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.webservice.*;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("sqld 연결 테스트")
	public void testConnection() {
		try(Connection con=
				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","c##book_ex","book_ex")){
			log.info(con);
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
}

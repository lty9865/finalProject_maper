package com.mapers.common;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DataSourceManager {
	// 1. singleton pattern
	private static DataSourceManager instance = new DataSourceManager();
	// DataSource는 java.sql에서의 interface를 가져옴
	private DataSource dataSource;
	
	// 2. 생성자에 Connection Pool 생성되도록 짬
	private DataSourceManager() {
		// was tomcat에서 제공하는 dbcp를 생성
		BasicDataSource dbcp = new BasicDataSource();
		
		// dbcp(datasource)에 driver와 dbconnection 정보를 설정
		dbcp.setDriverClassName("oracle.jdbc.OraclDriver");
		dbcp.setUrl("jdbc:oracle:thin:@localhost:xe");
		dbcp.setUsername("c##mapers");
		dbcp.setPassword("mapers1234");
		
		// setInitialSize() = 처음 로드할 때 생성할 connection 개수 설정
		dbcp.setInitialSize(3);
		
		// setMaxTotal() = 사용할 최대 connection 개수 설정
		dbcp.setMaxTotal(10);
		
		// 인스턴스 변수인 dataSource에 위를 통해 설정한 dbcp를 할당
		this.dataSource = dbcp;
	}
	
	// 3. singleton pattern
	public static DataSourceManager getInstance() {
		return instance;
	}
	
	// 4. DataBase Connection Pool을 만들어둔,
	// 		dbcp(DataSource)에 설정해 놓은 dbcp를 dataSource의 이름으로 가져옴
	public DataSource getDataSource() {
		return dataSource;
	}
	
}

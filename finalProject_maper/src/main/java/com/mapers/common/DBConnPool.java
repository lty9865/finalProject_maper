package com.mapers.common;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnPool {
	public Connection conn;
	public InitialContext initCtx;
	public Context envCtx;
	public DataSource ds;
	
	public Connection getConn() {
		try {
			conn = null;
			// 외부에서 데이터를 읽어야 하기에 Context 사용
			initCtx = new InitialContext();
			// tomcat 서버에 정보를 담아놓은 곳으로 이동
			envCtx = (Context) initCtx.lookup("java:comp/env"); // 자바 환경설정
			// 데이터소스 객체 선언, Resource name 값을 찾아옴
			ds = (DataSource) envCtx.lookup("jdbc/myoracle");
			// 데이터 소스를 기준으로 커넥션을 연결
			conn = ds.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}

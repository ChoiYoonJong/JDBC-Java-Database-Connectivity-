package com.uni.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	/*SingleTon Pattern : 클래스에 대한 객체가 프로그램 구동내내 한개만 작성되어 사용하게 함.
	 * static 으로 작성
	 */
	
	
		// 기존DB 처리작업(select,insert,update,delete 등) 시 마다
		// 세로운 Conncetion 객체를 생성하여 DB와 연결하여 진행함
		// 이렇게 되면 한 사용자가 여러 개의 커넥션 객체를 만들어 DB와 접속 하게 됨.
		// -> 여러 객체 생성으로 인한 메모리 남비 + DB에는 접속 가능한 커넥션 객체 수 가 제한되어 있음.
		 
		// 이를 해경하기 위하여 사요자당 커넥션 객체를 하나만 만들 수 있게 하여
		// 메모리 낭비 및 DB 연결 객체수 오버를 방지하게하는 실긍톤 패턴을 적용!
		
		// * 싱글톤(Single Tone) 패턴
		// 프로그램 구동 시 메모리에 객체를 딱 한개만 기록되게 하는 디자인 패턴
		// 모든 필드와 메소드를 static으로 선언하여
		// static 영역에 공용으로 사용할 수 있는 단 하나의 객체를 만듬.
		
		// 한 개의 공용 커넥션 객체를 저장할 참조변수 선언.
		// 외부에서 직접 접근을 할 수 없게 private 선언
	private static Connection conn = null; // private static 으로 선언 conn = null;
											//외부에서 직접적으로 접근할수없음
	// DB연결을 위해 공용 커넥션 객체를 반환해주는 메소드
	public static Connection getConnection(){//접근을 할 수 있게 해주는 메소드
		if(conn == null) {
			
				try {
					
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "STUDENT", "STUDENT");
					conn.setAutoCommit(false);
				}catch(Exception e) { //최상의 Exception 으로 모든 예외를 잡아서 처리해준다.
					e.printStackTrace();
				}
					
				/*} catch (ClassNotFoundException e) { // 이것도 가능한 Exception
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			
				
			
			
		}
		
		
		return conn;
	}
	// connection 과 관련된 conn 공통으로 뺀다.
	public static void close(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) {//conn 이 Null 이 아니고 conn 이 닫혀있냐?
				conn.close();// 닫아주면 된다.
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
		public static void close(Statement stmt) { // PreparedStatement 부모가 Statement이기 때문에 Statemnet 만 close 한다.
			
			try {
				if(stmt != null && !stmt.isClosed()) {
					stmt.close();// 닫아주면 된다.
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//ResultSet 닫기
		public static void close(ResultSet rset) { 
			
			try {
				if(rset != null && !rset.isClosed()) {
					rset.close();// 닫아주면 된다.
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 공통으로 commit 
		public static void commit(Connection conn) {//Connection 매개변수로 받아서..
			
			try {
				if(conn != null && !conn.isClosed()) {// 만약 conn 이 null 이 아니고 닫아져있지 않은 상황이면
					conn.commit();// conn commit 해주면 된다.
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// 공통으로 rollback 
				public static void rollback(Connection conn) {//Connection 매개변수로 받아서..
					
					try {
						if(conn != null && !conn.isClosed()) {// 만약 conn 이 null 이 아니고 닫아져있지 않은 상황이면
							conn.rollback();// conn rollback 해주면 된다.
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		
}

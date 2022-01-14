package org.kh.common;



import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;



// JDBCTemplate 클래스는 공통적으로 사용되는 코드를 작성해놓은 클래스

// 코드의 중복을 줄일 수 있음



public class JDBCTemplate {



		public  JDBCTemplate() {} //디폴트 생성자



		//new JDBCTemplate().getConnection();

		//위에꺼처럼 원래 있다면

		//JDBCTemplate.getConnection(); 이렇게 호출하면 된다.

		//static를 쓰면 힙영역에 미리 올라가있는 상태라 new없이 사용해도 괜찮다.

		

		//static는 메모리에 올려져있는 메소드라고 생각하면 편하다.

		public static Connection getConnection() {

			Connection conn = null;

			

			try {

				Class.forName("oracle.jdbc.driver.OracleDriver");

				

				conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");

			

				conn.setAutoCommit(false);

				

				

			

			

			} catch (ClassNotFoundException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}

			

			return conn;

			

			

		}

		

		//커넥션 객체가 null이아니거나 이미 종료된게 아니라면 닫아라.

		public static void close(Connection conn) {

			try {

				if(conn!=null && !conn.isClosed()) {

					conn.close();

				}

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}

			//or로 했을 경우

			//conn!=null || !conn.isClsed();

			//닫아진 상태가 아니라면 null인경우나 닫아진 경우엔 하면 안된다.

			//커넥션에있는 값이 닫아져버렸다. 앞에조건만 보면 닫아지긴 했지만 null은아니여서 참이기때문에 닫았는데 또닫게된다.

			//conn이 null이 아니면서 동시에 이미닫아진상태가 아닐때만 닫아라.

		}

		

		//commit에 대한 것

		public static void commit(Connection conn) {

			try {

				if(conn != null && !conn.isClosed()) {

					conn.commit();

				}

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}

		}

		

		//rollback에 대한것

		public static void rollback(Connection conn) {

			try {

				if(conn != null && !conn.isClosed()) {

					conn.rollback();

				}

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}

		}

		

		//메소드 오버로딩

		// -> 동일한 이름의 메소드 이름을 가지고 있으나 매개변수의 개수나 타입이 다른 메소드

		

		//메소드 오버라이딩

		// -> 부모가 가지고 있는 메소드를 자식 클래스에서 새롭게 재정의 하는 메소드

		

	

		public static void close(ResultSet rset) {

			try {

				if(rset!=null && !rset.isClosed()) {

					rset.close();

				}

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}

		}

		

		//Prestatement 부모클래스 자식 래퍼런스는 자식클래스의 주소도 저장할 수 있다.

		//그래서 api클래스를 보면 다 저장할 수 있는 것이다.

		//animal이 있고 tiger상속받은게 있다면 다 저장할수 있다는 것이다.

		public static void close(Statement stmt) {

			try {

				if(stmt!=null && !stmt.isClosed()) {

					stmt.close();

				}

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}

		}

		

}

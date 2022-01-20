package com.uni.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.uni.member.model.dto.Member;
/*1.Connection 객체연결하기
 * 2.Statememt 객체 생성하기
 * 3.ResultSet 객체 생성하기
 * 4.sql 작성하기
 * 5. resultSet 결과 담기
 * 6. list 에 책체 하나씩 담기
 * 7. close 하기 (자원 반납 - 생성의 역순)
 * */
public class MemberDao {

	public ArrayList<Member> selectAll() {
		
		ArrayList<Member> list = null;
		
		Connection conn = null;//DB 연결할 객체
		Statement stmt = null;// 실행할 쿼리
		ResultSet rset = null;//select 한후 결과값 받아올 객체
		
		String sql = "SELECT * FROM MEMBER";// 자동으로 ; 을 붙여 실행 되므로 붙히지 않는다.
		
		
			//1.Jdbc driver 등록처리 : 해당 database 벤더 사가 제공하는 클래스 등록
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");
			
			//2. 등록된 클래스를 이용해서 db연결
			// 드라이버타입@ip주소 :포트번호:db이름(SID)
			//orcl:사용자정의설치, thin:자동으로 설치 // ip주소 -> localhost롤 변경해도 됨 127.0.0.1 - 나 자신 ip
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe" ,"STUDENT", "STUDENT");
			System.out.println("conn =" + conn);//성공하면 connection 정보, 실패하면 null값
			
			
			//3. 쿼리문을 실행할 statement 객체 생성
			stmt = conn.createStatement(); 
			
			//4. 쿼리문 전송, 실행 결과를 ResultSet으로 받기
			rset = stmt.executeQuery(sql);// executeQuery 메소드로 sql 메게변수로 던져서 쿼리 실행 그결과 값을 rset 으로 받는다.
			
			//5.받은 결과값을 객체에 옮겨서 저장하기
			list = new ArrayList<Member>();
			
			//여러행
			//각 행마다 접근해서 
			while(rset.next()) { 
				Member m = new Member();
				m.setUserId(rset.getString("USERID"));
				m.setPassword(rset.getString("PASSWORD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
				list.add(m); // while 문을 돌면서 한행 한행들을 객체로 만들어서 list 에 더해진다.
				
			}
			
		} catch (ClassNotFoundException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {//마지막으로 열었으니 닫아줘야한다.
			
			
			try {// 자원반납의 순서는 생성의 역순대로...
				rset.close();
				stmt.close();
				//conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list; //list 로 받아주기때문에 list로 반환
	}

	public Member selectOne(String memberId) {
		Member m = null; // 아무값도 못 가져왔을때 null 디폴트로 null으로 생성
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER WHERE USERID = ? "; //쿼리 작성
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");
			
			//2. 등록된 클래스를 이용해서 db연결
			// 드라이버타입@ip주소 :포트번호:db이름(SID)
			//orcl:사용자정의설치, thin:자동으로 설치 // ip주소 -> localhost롤 변경해도 됨 127.0.0.1 - 나 자신 ip
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe" ,"STUDENT", "STUDENT");
			System.out.println("conn =" + conn);//성공하면 connection 정보, 실패하면 null값
			
			
			//3. 쿼리문을 실행할 statement 객체 생성
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, memberId);
			//4. 쿼리문 전송, 실행 결과를 ResultSet으로 받기
			rset = pstmt.executeQuery();// executeQuery 메소드로 sql 메게변수로 던져서 쿼리 실행 그결과 값을 rset 으로 받는다.
			
			//5.받은 결과값을 객체에 옮겨서 저장하기
			//list = new ArrayList<Member>();
			
			//여러행
			//각 행마다 접근해서 
			// 여기서는 하나만 하기때문에 list 를 작성하지 않는
			if(rset.next()) { //하나만 하기때문에 while 문을 쓰지 않는다.
				m = new Member(); // 멤버위에서 선언해놓은 m
				
				m.setUserId(rset.getString("USERID"));
				m.setPassword(rset.getString("PASSWORD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
				
				//list.add(m); // while 문을 돌면서 한행 한행들을 객체로 만들어서 list 에 더해진다.
				//한개의 정보가 하기때문에 list를 사용 안해도된다.
			}
			
		} catch (ClassNotFoundException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {//마지막으로 열었으니 닫아줘야한다.
			
			
			try {// 자원반납의 순서는 생성의 역순대로...
				rset.close();
				pstmt.close();
				//conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return m;//m 으로 리턴
	}

	public List<Member> selectByName(String memberName) {
		
		ArrayList<Member> list = null;
		
		Connection conn = null;//DB 연결할 객체
		PreparedStatement pstmt = null;// 실행할 쿼리
		ResultSet rset = null;//select 한후 결과값 받아올 객체
		
		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE ? ";// sql 작성
		
		
			
		try {
			
			//1.Jdbc driver 등록처리 : 해당 database 벤더 사가 제공하는 클래스 등록
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");
			
			//2. 등록된 클래스를 이용해서 db연결
			// 드라이버타입@ip주소 :포트번호:db이름(SID)
			//orcl:사용자정의설치, thin:자동으로 설치 // ip주소 -> localhost롤 변경해도 됨 127.0.0.1 - 나 자신 ip
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe" ,"STUDENT", "STUDENT");
			System.out.println("conn =" + conn);//성공하면 connection 정보, 실패하면 null값
			
			
			//3. 쿼리문을 실행할 statement 객체 생성
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1,"%" + memberName + "%");
			//4. 쿼리문 전송, 실행 결과를 ResultSet으로 받기
			rset = pstmt.executeQuery();// executeQuery 메소드로 sql 메게변수로 던져서 쿼리 실행 그결과 값을 rset 으로 받는다.
			
			//5.받은 결과값을 객체에 옮겨서 저장하기
			list = new ArrayList<Member>();
			
			//여러행
			//각 행마다 접근해서 
			while(rset.next()) { 
				Member m = new Member();
				
				m.setUserId(rset.getString("USERID"));
				m.setPassword(rset.getString("PASSWORD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
				list.add(m); // while 문을 돌면서 한행 한행들을 객체로 만들어서 list 에 더해진다.
				
			}
			
		} catch (ClassNotFoundException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {//마지막으로 열었으니 닫아줘야한다.
			
			
			try {// 자원반납의 순서는 생성의 역순대로...
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list; //list 로 받아주기때문에 list로 반환
	}

	public int inserMember(Member m) {
		
		int result = 0; // 결과값을 받아 줄 result 0으로 초기화
		
		Connection conn = null;//DB 연결할 객체
		PreparedStatement pstmt = null;// 실행할 쿼리
		//ResultSet 은 반화해주지 않는다. 성공한 갯수로 리턴 하기 때문에 ResultSet 은 Select 때 사용 하기때문에 여기에서는 사용하지 않는다.
		
		
		//INSERT SQL
		String sql = "INSERT INTO MEMBER VALUES (?,?,?,?,?,?,?,?,?,sysdate)";
					/*+ "'" + m.getUserId() + "',"
					+ "'" + m.getPassword() + "',"
					+ "'" + m.getUserName() + "',"
					+ "'" + m.getGender() + "',"
					+ "'" + m.getAge() + "',"
					+ "'" + m.getEmail() + "',"
					+ "'" + m.getPhone() + "',"
					+ "'" + m.getAddress() + "',"
					+ "'" + m.getHobby() + "',"
					+ "sysdate)"; */
							
		System.out.println(sql);
		
		//1.Jdbc driver 등록처리 : 해당 database 벤더 사가 제공하는 클래스 등록
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
		System.out.println("드라이버 등록 성공");
		
		//2. 등록된 클래스를 이용해서 db연결
		// 드라이버타입@ip주소 :포트번호:db이름(SID)
		//orcl:사용자정의설치, thin:자동으로 설치 // ip주소 -> localhost롤 변경해도 됨 127.0.0.1 - 나 자신 ip
		conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe" ,"STUDENT", "STUDENT");
		System.out.println("conn =" + conn);//성공하면 connection 정보, 실패하면 null값
		
		
		//3. 쿼리문을 실행할 statement 객체 생성
		pstmt = conn.prepareStatement(sql);
		//commit 오토로 하지 않는다.
		conn.setAutoCommit(false);
		

		pstmt.setString(1,m.getUserId());
		pstmt.setString(2,m.getPassword());
		pstmt.setString(3,m.getUserName());
		pstmt.setString(4,m.getGender());
		pstmt.setInt(5,m.getAge());
		pstmt.setString(6,m.getEmail());
		pstmt.setString(7,m.getPhone());
		pstmt.setString(8,m.getAddress());
		pstmt.setString(9,m.getHobby());
		
		
		
		result = pstmt.executeUpdate();//처리한 행의 갯수리턴
		
		
		if (result >0) conn.commit(); // 만약 0 보다 크면 성공한경우이기때문에 commit 실행
		else conn.rollback();// insert 가 제대로 되지 않아서 0보다 크지 않기때문에 되돌리기 실행
		System.out.println(result);
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();//연결도 close
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return result; // return 값을 result로 반환 
	}

	public int updateMember(Member m) {
		
		int result = 0; // 결과값을 받아 줄 result 0으로 초기화
		
		Connection conn = null;//DB 연결할 객체
		PreparedStatement pstmt = null;// 실행할 쿼리
		//ResultSet 은 반화해주지 않는다. 성공한 갯수로 리턴 하기 때문에 ResultSet 은 Select 때 사용 하기때문에 여기에서는 사용하지 않는다.
		
		//UPDATE SQL
		String sql = "UPDATE MEMBER SET PASSWORD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ? WHERE USERID = ?";
					/*+" PASSWORD = '" + m.getPassword() + "' , "
					+" EMAIL = '" + m.getEmail() + "' , "
					+" PHONE = '" + m.getPhone() + "' , "
					+" ADDRESS = '" + m.getAddress() + "'" 
					+" WHERE USERID = '" + m.getUserId() + "'" ;*/
		
		// 위와 같은 것을쓸때 "EXAMPLE = '" 이렇게 앞에 " 하고 붙어 쓰이면 위에 있는문장하고 연결 되는거로 인식이 되서 의도한것대로 안되는 경우가 있을 수 있다. 
		// 그래서 이왕이면 띄어 쓰는것이 좋다.
		// ex) UPDATE SET
		//     SSS = 'A' WHERE     이런경우가 될 수 있다. String 으로 쓰기 때문에 여기 안에서라도 오류가 날 수 있다. (조심)
							
		System.out.println(sql);
		
		//1.Jdbc driver 등록처리 : 해당 database 벤더 사가 제공하는 클래스 등록
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
		System.out.println("드라이버 등록 성공");
		
		//2. 등록된 클래스를 이용해서 db연결
		// 드라이버타입@ip주소 :포트번호:db이름(SID)
		//orcl:사용자정의설치, thin:자동으로 설치 // ip주소 -> localhost롤 변경해도 됨 127.0.0.1 - 나 자신 ip
		conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe" ,"STUDENT", "STUDENT");
		System.out.println("conn =" + conn);//성공하면 connection 정보, 실패하면 null값
		
		
		//3. 쿼리문을 실행할 statement 객체 생성
		pstmt = conn.prepareStatement(sql);
		
		//commit 오토로 하지 않는다.
		conn.setAutoCommit(false);
		
		pstmt.setString(1,m.getPassword());
		pstmt.setString(2,m.getEmail());
		pstmt.setString(3,m.getPhone());
		pstmt.setString(4,m.getAddress());
		pstmt.setString(5,m.getUserId());
		
		
		
		
		
		result = pstmt.executeUpdate(); // executeUpdate 로 sql 넣어서 업데이트 실행 // 처리한 행의 갯수 리턴
		
		if (result >0) conn.commit(); // 만약 0 보다 크면 성공한경우이기때문에 commit 실행
		else conn.rollback();// insert 가 제대로 되지 않아서 0보다 크지 않기때문에 되돌리기 실행
		System.out.println(result);
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();//연결도 close
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return result; // return 값을 result로 반환 
	}

	public int deleteMember(String userId) {
int result = 0; // 결과값을 받아 줄 result 0으로 초기화
		
		Connection conn = null;//DB 연결할 객체
		PreparedStatement pstmt = null;// 실행할 쿼리
		//ResultSet 은 반화해주지 않는다. 성공한 갯수로 리턴 하기 때문에 ResultSet 은 Select 때 사용 하기때문에 여기에서는 사용하지 않는다.
		
		//UPDATE SQL
		String sql = "DELETE FROM MEMBER WHERE USERID = ?";
		
		// 위와 같은 것을쓸때 "EXAMPLE = '" 이렇게 앞에 " 하고 붙어 쓰이면 위에 있는문장하고 연결 되는거로 인식이 되서 의도한것대로 안되는 경우가 있을 수 있다. 
		// 그래서 이왕이면 띄어 쓰는것이 좋다.
		// ex) UPDATE SET
		//     SSS = 'A' WHERE     이런경우가 될 수 있다. String 으로 쓰기 때문에 여기 안에서라도 오류가 날 수 있다. (조심)
							
		System.out.println(sql);
		
		//1.Jdbc driver 등록처리 : 해당 database 벤더 사가 제공하는 클래스 등록
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
		System.out.println("드라이버 등록 성공");
		
		//2. 등록된 클래스를 이용해서 db연결
		// 드라이버타입@ip주소 :포트번호:db이름(SID)
		//orcl:사용자정의설치, thin:자동으로 설치 // ip주소 -> localhost롤 변경해도 됨 127.0.0.1 - 나 자신 ip
		conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe" ,"STUDENT", "STUDENT");
		System.out.println("conn =" + conn);//성공하면 connection 정보, 실패하면 null값
		
		
		//3. 쿼리문을 실행할 statement 객체 생성
		pstmt = conn.prepareStatement(sql);
		
		//commit 오토로 하지 않는다.
		conn.setAutoCommit(false);
		
		pstmt.setString(1,userId);
		
		result = pstmt.executeUpdate(); // executeUpdate 로 sql 넣어서 업데이트 실행 // 처리한 행의 갯수 리턴
		
		if (result >0) conn.commit(); // 만약 0 보다 크면 성공한경우이기때문에 commit 실행
		else conn.rollback();// insert 가 제대로 되지 않아서 0보다 크지 않기때문에 되돌리기 실행
		System.out.println(result);
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();//연결도 close
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return result;
	}

}

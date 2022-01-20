package com.uni.member.model.dao;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.uni.member.model.dto.Member;

public class MemberDAO {
	
	//기본생성자 작성전에 sql 구문을 읽어올 properties 참조변수 선언
	private Properties prop = null;
	
	//외부에서 .properties 파일 읽어와서 prop 참조변수에 저장 
	public MemberDAO() { // 기본 생성자 생성
		
		
		
		try {
			prop = new Properties();
			prop.load(new FileReader("resources/query.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
public ArrayList<Member> selectAll(Connection conn) {
		
		ArrayList<Member> list = null;
		Statement stmt = null;// 실행할 쿼리
		ResultSet rset = null;//select 한후 결과값 받아올 객체
		
		//String sql = "SELECT * FROM MEMBER";// 자동으로 ; 을 붙여 실행 되므로 붙히지 않는다.
		String sql = prop.getProperty("seleceAll");
		
			
		
		try {
			
			//conn = new JDBCTemplate().getConnection();//이미 static 으로 올라가져있기때문에 생성할 필요가없다.
			
			
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {//마지막으로 열었으니 닫아줘야한다.
			
				//close(rset);
				//close(stmt);
				
			
		}
		return list; //list 로 받아주기때문에 list로 반환
	}

	public Member selectOne(Connection conn,String memberId) {//매개변수의 갯수가 달라서 오류가 난다. String memberId 앞에 Connection conn 작성
		Member m = null; // 아무값도 못 가져왔을때 null 디폴트로 null으로 생성
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//String sql = "SELECT * FROM MEMBER WHERE USERID = ? "; //쿼리 작성
		String sql = prop.getProperty("selectOne");
		try {
			
			

			
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
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {//마지막으로 열었으니 닫아줘야한다.
			
			//close(rset);
			//close(pstmt);
			
		}
		
		return m;//m 으로 리턴
	}

	public List<Member> selectByName(Connection conn,String memberName) {
		
		ArrayList<Member> list = null;
		
		
		PreparedStatement pstmt = null;// 실행할 쿼리
		ResultSet rset = null;//select 한후 결과값 받아올 객체
		
		//String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE ? ";// sql 작성
		String sql = prop.getProperty("selectByName");
		
		
			
		try {
			
			

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
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {//마지막으로 열었으니 닫아줘야한다.
			
			//close(rset);
			//close(pstmt);
			
		}
		return list; //list 로 받아주기때문에 list로 반환
	}

	public int inserMember(Connection conn,Member m) {
		System.out.println(" 111111 " + m);
		int result = 0; // 결과값을 받아 줄 result 0으로 초기화
		
		PreparedStatement pstmt = null;// 실행할 쿼리
		//ResultSet 은 반화해주지 않는다. 성공한 갯수로 리턴 하기 때문에 ResultSet 은 Select 때 사용 하기때문에 여기에서는 사용하지 않는다.
		
		
		//INSERT SQL
		String sql = prop.getProperty("insertMember");
		//String sql = "INSERT INTO MEMBER VALUES (?,?,?,?,?,?,?,?,?,sysdate)";
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
		
		
		
		try {
			
		
			//3. 쿼리문을 실행할 statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
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
		
		
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			//close(pstmt);
		}
		return result; // return 값을 result로 반환 
	}

	public int updateMember(Connection conn, Member m) {
		
		int result = 0; // 결과값을 받아 줄 result 0으로 초기화
		
		PreparedStatement pstmt = null;// 실행할 쿼리
		//ResultSet 은 반화해주지 않는다. 성공한 갯수로 리턴 하기 때문에 ResultSet 은 Select 때 사용 하기때문에 여기에서는 사용하지 않는다.
		
		//UPDATE SQL
		String sql = prop.getProperty("updateMember");
		//String sql = "UPDATE MEMBER SET PASSWORD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ? WHERE USERID = ?";
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
			//3. 쿼리문을 실행할 statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,m.getPassword());
			pstmt.setString(2,m.getEmail());
			pstmt.setString(3,m.getPhone());
			pstmt.setString(4,m.getAddress());
			pstmt.setString(5,m.getUserId());
		
			result = pstmt.executeUpdate(); // executeUpdate 로 sql 넣어서 업데이트 실행 // 처리한 행의 갯수 리턴
		
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
				//close(pstmt);
				
			
		}
		return result; // return 값을 result로 반환 
	}

	public int deleteMember(Connection conn , String userId) {
		int result = 0; // 결과값을 받아 줄 result 0으로 초기화
		
		PreparedStatement pstmt = null;// 실행할 쿼리
		//ResultSet 은 반화해주지 않는다. 성공한 갯수로 리턴 하기 때문에 ResultSet 은 Select 때 사용 하기때문에 여기에서는 사용하지 않는다.
		
		//UPDATE SQL
		String sql = prop.getProperty("deleteMember");
		//String sql = "DELETE FROM MEMBER WHERE USERID = ?";
		
		// 위와 같은 것을쓸때 "EXAMPLE = '" 이렇게 앞에 " 하고 붙어 쓰이면 위에 있는문장하고 연결 되는거로 인식이 되서 의도한것대로 안되는 경우가 있을 수 있다. 
		// 그래서 이왕이면 띄어 쓰는것이 좋다.
		// ex) UPDATE SET
		//     SSS = 'A' WHERE     이런경우가 될 수 있다. String 으로 쓰기 때문에 여기 안에서라도 오류가 날 수 있다. (조심)
							
		System.out.println(sql);
		
		
		
		try {
		
			//3. 쿼리문을 실행할 statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1,userId);
			
			result = pstmt.executeUpdate(); // executeUpdate 로 sql 넣어서 업데이트 실행 // 처리한 행의 갯수 리턴
			
			
		
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
				//close(pstmt);
				
		}
		return result;
	}

	public List<Member> selectAllDeleteMember(Connection conn) {
		
		ArrayList<Member> list = null;
		
		
		PreparedStatement pstmt = null;// 실행할 쿼리
		ResultSet rset = null;//select 한후 결과값 받아올 객체
		
		//String sql = "SELECT * FROM MEMBER_DEL ";// sql 작성
		String sql = prop.getProperty("selectAllDeleteMember");
		
		
			
		try {
			
			

			//3. 쿼리문을 실행할 statement 객체 생성
			pstmt = conn.prepareStatement(sql);
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
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {//마지막으로 열었으니 닫아줘야한다.
			
			//close(rset);
			//close(pstmt);
			
		}
		return list; //list 로 받아주기때문에 list로 반환
	}

	


}
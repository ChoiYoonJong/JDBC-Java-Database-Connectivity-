package org.kh.member.dao; 

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
import java.util.ArrayList; 

import org.kh.member.model.vo.Member; 

public class MemberDao { 
	
	//DBMS에 접근하는 전체 회원 조회 메소드 
	public ArrayList<Member> selectMemberAll() { 
		
		//필요한 레퍼런스 생성(Connection, Statement, ResultSet) 
		//java.sql 패키지 import 
		Connection conn = null; //Connection 레퍼런스 (객체 생성은 아직 안함) 
		Statement stmt = null; //Statement 래퍼런스 (객체 생성은 아직 안함)
		ResultSet rset=null; //ResultSet 래퍼런스 (객체 생성은 아직 안함) 
		
		ArrayList<Member> list=new ArrayList<Member>(); 
		
		//@ JDBC Coding 절차 
		
		try { 
			
			//1. Driver 등록 
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			
			//2. DBMS와 연결 (Connection 사용) 
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe" ,"STUDENT", "STUDENT");
			//첫번째는 연결 정보(DB종류, 타입, IP주소, 포트 등등) 
			//두번째는 연결하는 DB의 ID(아이디) 
			//세번째는 연결하는 ID의 PW(비밀번호) 
			//get으로 읽어오는것이므로 저장하는 것이다.conn에 저장하는 것이다.
			
			System.out.println(conn); 
			// conn 레퍼런스 안에 있는 값을 출력시
			// DBMS에 정상연결 되었다면 hashCode 값(Connection 값)이 출력 되고 
			// DBMS에 연결 실패 하였다면 null값을 리턴
			//3. Statement 생성 - 작성한 쿼리문을 전송할 객체를 생성 해야 함
			// Connection (conn) 객체를 이용하여 Statement 객체를 생성함 
			
			stmt=conn.createStatement(); // Statement 객체 생성 
			
			//4. SQL 전송 
			// - Query 문이 있어야 전송합니다. 
			String query = "select * from member"; 
			rset=stmt.executeQuery(query); // Statement 객체를 이용하여 쿼리문 실행 
			//결과를 rset에 담는다. 
			
			//5. 결과 처리 
			// rset을 이용하여 결과를 처리함 
			// rset.next() 메소드와 rset.set...() 메소드를 이용함 
			// next 메소드는 각 행을 가리키는 메소드(작업시 마다 사용해야함) 
			// set...() 메소드는 각 컬럼의 정보를 가져올때 사용함 
			
			//rset.next() 
			//rset.getString("member_id"); -> rset을 이용해스트링값갖고오는데 그컬럼명은 멤버아이디꺼갖고오라는 것이다. 
			
			
			while(rset.next()) {
				Member m = new Member(); 
				
				//ID값 저장하기 
				m.setMemberId(rset.getString("USERID")); 
				m.setMemberPwd(rset.getString("PASSWORD")); 
				m.setMemberName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER").charAt(0));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
				
				list.add(m); 
				
			} 
			
		} catch (ClassNotFoundException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
			
		} 
		//오라클에 jdbc에 드라이버에 오라클드라이버를 등록하겠다. 
		//이코드가 없어도 8버전부턴 가능하긴한데 넣어주는게 좋다. 
		catch (SQLException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace();
			
		}finally { 
			//6. 연결된 리소스 닫기 (close 작업 - finally에서 작업) 
			try { 
				rset.close(); 
				stmt.close(); 
				conn.close(); 
				
			} catch (SQLException e) { 
				// TODO Auto-generated catch block 
				e.printStackTrace(); 
				
			} 
			
		} return list; 
		
	} public Member selectMemberId(String userId) { 
		
		Connection conn=null;//DBMS와 연결 
		Statement stmt=null;//작성한 쿼리문을 전송할 객체를 생성 해야 함 
		ResultSet rset=null;//SELECT 구문일 경우 ResultSet을 이용하여 정보를 객체에 담는 처리 
		Member m = null; //멤버객체를 여기선 바깥에 만드는데 그 이유는? 
		//결과가 조회되던안되던 리턴받을 것이다. 
		
		try { 
			
			//1. Driver 등록 
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			
			//2. DBMS 연결 
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT"); 
			//SQLException 에러 뜰수있기 때문에 catch로잡아줘야한다. 
			
			//3. Statement 객체 생성 
			// Connection (conn) 객체를 이용하여 Statement 객체를 생성함 
			stmt=conn.createStatement();
			
			//4. SQL 전송 
			// - Query 문이 있어야 전송합니다. 
			String query="select * from member where USERID='"+userId+"'"; 
			rset=stmt.executeQuery(query);// Statement 객체를 이용하여 쿼리문 실행 
			
			//5. 결과 처리 
			// rset을 이용하여 결과를 처리함 
			// rset.next() 메소드와 rset.set...() 메소드를 이용함 
			// next 메소드는 각 행을 가리키는 메소드(작업시 마다 사용해야함) 
			// set...() 메소드는 각 컬럼의 정보를 가져올때 사용함 
			
			if(rset.next()) {//만약에 1명의 결과가 조회되었다면 진행하고 없다면 하지마라! 
				
				m=new Member();//결과가 있으므로 정보를 저장할 VO 객체 생성 
				m.setMemberId(rset.getString("USERID"));
				m.setMemberPwd(rset.getString("PASSWORD"));
				m.setMemberName(rset.getString("USERNAME")); 
				m.setGender(rset.getString("GENDER").charAt(0));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
				
			} 
			
		} catch (ClassNotFoundException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace(); 
			
		} catch (SQLException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace();
			
		}finally { 
			//6. 연결된 리소스 닫기 (close 작업 - finally에서 작업) 
			try { 
				rset.close(); 
				stmt.close(); 
				conn.close(); 
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
				
			} 
			
		} return m; //m래퍼런스 안에 있는 값을 리턴(Member 객체 혹은 null)
		
	} 
	
	public ArrayList<Member> selectMemberName(String userName) {
		// TODO Auto-generated method stub
		Connection conn=null; 
		Statement stmt=null;
		ResultSet rset=null; 
		
		ArrayList<Member> list = new ArrayList<Member>(); 
		
		try {
			//1. Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			
			//2. DBMS 연결 
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe" ,"STUDENT", "STUDENT"); 
			
			//연결되었는지 확인해볼 것이다. 
			System.out.println(conn); 
			
			//3. Statement 객체 생성 
			stmt=conn.createStatement(); 
			
			//4. SQL 전송 
			// - Query 문이 있어야 전송합니다. 
			String query="select * from member where USERNAME like '%"+userName+"%'";
			rset=stmt.executeQuery(query); 
			
			//5. 결과 처리 
			//while을 쓴 이유는 값을 저장하기 위해서이다. 
			
			while(rset.next()) { 
				Member m = new Member(); 
				
				//ID값 저장하기 
				m.setMemberId(rset.getString("USERID"));
				m.setMemberPwd(rset.getString("PASSWORD"));
				m.setMemberName(rset.getString("USERNAME")); 
				m.setGender(rset.getString("GENDER").charAt(0));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
				
				list.add(m);
				
			}
			
		} catch (ClassNotFoundException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally { 
			
			try {
				rset.close(); 
				stmt.close();
				conn.close();
				
			} catch (SQLException e) { 
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} 
			
		} return list; 
		
	} 
	
	//회원 가입용 메소드 
	public int insertMember(Member m) { 
		
		//래퍼런스 3종세트 
		Connection conn=null;
		Statement stmt=null; 
		//ResultSet rset=null; -> 이제 안만들어도된다. select가아니므로 결과값이 돌아올 필요가 없기 때문이다. 
		int result=0; //결과를 숫자로 받을려고 한다. 
		
		try { 
			
			//1. Driver 등록 
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			
			//2. DBMS 연결 
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			
			//3. Statement 객체 생성 
			stmt = conn.createStatement(); 
			
			//4. SQL 전송 
			String query="insert into member values("+ 
							"'"+m.getMemberId()+"',"+ 
							"'"+m.getMemberPwd()+"',"+ 
							"'"+m.getMemberName()+"',"+
							"'"+m.getGender()+"',"+
							"'"+m.getAge()+"',"+
							"'"+m.getEmail()+"',"+
							"'"+m.getPhone()+"',"+
							"'"+m.getAddress()+"',"+
							"'"+m.getHobby()+"',"+"sysdate)";
			
			System.out.println(query); 
			//stmt.executeQuery(sql) -> select 전용이다. 
			//0이넘어오면 정상적으로되었다 하면되고 아니면 정상가입 안되었다고 하면된다. 
			result=stmt.executeUpdate(query); //return 타입이 int형이다.
			
			//commit과 rollback가 없을경우 트랜잭션을 계속 잡고 있게 된다. 
			if(result>0) {//정상처리되었을때 
				conn.commit(); //적용
				
			} else { 
				conn.rollback(); //되돌리기
				
			} 
			
		} catch (ClassNotFoundException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			try { 
				stmt.close(); 
				conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
				
			} 
			
		} return result;
		
	} 
	
	public int updateMember(String userId,Member m1) { 
		//업데이트일 경우 기준을 userId로 둬서 값을 구했다.
		
		//래퍼런스 3종세트 
		Connection conn=null; 
		Statement stmt=null; //ResultSet rset=null; -> 이제 안만들어도된다. select가아니므로 결과값이 돌아올 필요가 없기 때문이다.
		int result=0; 
		try { 
			
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT"); 
			stmt = conn.createStatement(); 
			
			System.out.println(conn); 
			
			String query="update member set USERID="+ 
			"'"+m1.getMemberId()+"'"+",PASSWORD="+ 
					"'"+m1.getMemberPwd()+"'"+",USERNAME="+
			"'"+m1.getMemberName()+"'"+",GENDER="+ 
					"'"+m1.getGender()+"'"+",AGE="+
			"'"+m1.getAge()+"'"+",EMAIL="+
			"'"+m1.getEmail()+"'"+",PHONE="+
			"'"+m1.getPhone()+"'"+",ADDRESS="+
			"'"+m1.getAddress()+"'"+",HOBBY="+
			"'"+m1.getHobby()+"'"+",enroll_date=sysdate where USERID="+"'"+userId+"'";
			
			System.out.println(query); 
			
			result=stmt.executeUpdate(query); //return 타입이 int형이다.
			
			//commit과 rollback가 없을경우 트랜잭션을 계속 잡고 있게 된다. 
			System.out.println("성공"); 
			if(result>0) {//정상처리되었을때
				conn.commit(); //적용
				
			} else { 
				conn.rollback(); //되돌리기
				
			} 
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
			
		}finally { 
			try { 
				stmt.close(); 
				conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} 
			
		} return result; 
		
	} public int deleteMember(String userId) { 
		
		// TODO Auto-generated method stub 
		//래퍼런스 3종세트 
		Connection conn=null;
		Statement stmt=null; 
		//ResultSet rset=null; -> 이제 안만들어도된다. select가아니므로 결과값이 돌아올 필요가 없기 때문이다. 
		int result=0; //결과를 숫자로 받을려고 한다. 
		
		try { Class.forName("oracle.jdbc.driver.OracleDriver"); 
		conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT"); 
		
		stmt = conn.createStatement(); 
		
		String query="delete from member where userid='"+userId+"'"; 
		
		System.out.println(query); 
		//stmt.executeQuery(sql) -> select 전용이다. 
		
		//0이넘어오면 정상적으로되었다 하면되고 아니면 정상가입 안되었다고 하면된다. 
		result=stmt.executeUpdate(query); //return 타입이 int형이다. 
		
		//commit과 rollback가 없을경우 트랜잭션을 계속 잡고 있게 된다. 
		if(result>0) {//정상처리되었을때 
			conn.commit(); //적용 
			
		} else { 
			conn.rollback(); //되돌리기 
			
		} 
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}finally {
		try { 
			stmt.close();
			conn.close();
			
		} catch (SQLException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	} 
	
		return result; 
		
	} 
	
}
	
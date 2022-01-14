package org.kh.member.dao;



import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

import java.util.ArrayList;



import org.kh.common.JDBCTemplate;

import org.kh.member.model.vo.Member;



public class MemberDao {

	

	//DBMS에 접근하는 전체 회원 조회 메소드

	public ArrayList<Member> selectMemberAll(Connection conn) {

		Statement stmt =null;

		ResultSet rset = null;

		

		ArrayList<Member> list = new ArrayList<Member>();

		

		try {

			stmt = conn.createStatement();

			

			String query = "select * from member";

			

			rset = stmt.executeQuery(query);

			

			while(rset.next()) {

				Member m=new Member();//결과가 있으므로 정보를 저장할 VO 객체 생성

				m.setMemberId(rset.getString("USERID"));

				m.setMemberPwd(rset.getString("PASSWORD"));

				m.setMemberName(rset.getString("USERNAME"));

				m.setGender(rset.getString("gender").charAt(0));

				m.setAge(rset.getInt("age"));

				m.setEmail(rset.getString("email"));

				m.setPhone(rset.getString("phone"));

				m.setAddress(rset.getString("address"));

				m.setHobby(rset.getString("hobby"));

				m.setEnrollDate(rset.getDate("ENROLLDATE"));

				

				list.add(m);

			}

			

			

			

			

			

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}finally {

			JDBCTemplate.close(rset);

			JDBCTemplate.close(stmt);

			

		}

		



		return list;



		

	}

	

	public Member selectMemberId(String userId,Connection conn) {

			

		PreparedStatement pstmt=null;

		ResultSet rset=null;

		

		Member m=null;

		

		String query="select * from member where USERID=?";

		try {

			pstmt=conn.prepareStatement(query);

			pstmt.setString(1, userId);

			rset=pstmt.executeQuery();

			

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

			

			

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}finally {

			JDBCTemplate.close(rset);

			JDBCTemplate.close(pstmt);

//			try {

//				pstmt.close();

//				

//			} catch (SQLException e) {

//				// TODO Auto-generated catch block

//				e.printStackTrace();

//			}

//			

		}

		return m;

		

		

		

	}



	public ArrayList<Member> selectMemberName(String userName,Connection conn) {

		// TODO Auto-generated method stub

		

		PreparedStatement pstmt=null;

		ResultSet rset=null;

		

	

		ArrayList<Member> list = new ArrayList<Member>();

		

		try {

			

			String query="select * from member where USERNAME like '%'||?||'%'";

	

			pstmt=conn.prepareStatement(query);

			pstmt.setString(1, userName);

			rset=pstmt.executeQuery();

		

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

			

			

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}finally {

			JDBCTemplate.close(rset);

			JDBCTemplate.close(pstmt);

//			try {

//				pstmt.close();

//			} catch (SQLException e) {

//				// TODO Auto-generated catch block

//				e.printStackTrace();

//			}

		}

		

		

		return list;

		

		

		

	}

	//회원 가입용 메소드

	public int insertMember(Member m,Connection conn) {

		PreparedStatement pstmt = null;

		

		int result = 0;

		

		String query = "insert into member values (?,?,?,?,?,?,?,?,?,sysdate)";

		

		try {

			pstmt=conn.prepareStatement(query);

			pstmt.setString(1, m.getMemberId());

			pstmt.setString(2, m.getMemberPwd());

			pstmt.setString(3, m.getMemberName());

			pstmt.setString(4, String.valueOf(m.getGender()));

			pstmt.setInt(5, m.getAge());

			pstmt.setString(6, m.getEmail());

			pstmt.setString(7, m.getPhone());

			pstmt.setString(8, m.getAddress());

			pstmt.setString(9, m.getHobby());

			

			result=pstmt.executeUpdate();//위에서 쿼리 담았기 때문에 안담는다.

			//System.out.println(query);

			

			

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}finally {

			JDBCTemplate.close(pstmt);

			

		}

		return result;

	}



	public int updatemember(String userId,Member m2,Connection conn) {

		// TODO Auto-generated method stub

		

		PreparedStatement pstmt=null;

		int result=0;

		try {

		

			String query= "update member set  member_pwd=?, email=?,phone=?,address=?,hobby=? where member_id=?";

			pstmt = conn.prepareStatement(query);

			

			pstmt.setString(1, m2.getMemberPwd());

			pstmt.setString(2, m2.getEmail());

			pstmt.setString(3, m2.getPhone());

			pstmt.setString(4, m2.getAddress());

			pstmt.setString(5, m2.getHobby());

			pstmt.setString(6, userId);

			

			

			

			

			

			

			System.out.println(query);

			result=pstmt.executeUpdate();

			

			if(result>0) {

				conn.commit();

			}

			else {

				conn.rollback();

			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}finally {

			JDBCTemplate.close(pstmt);

			

			

		

		}

		return result;

	

		

		

	}

	public int deleteMember(String userId,Connection conn) {

		// TODO Auto-generated method stub

		//래퍼런스 3종세트

		

		PreparedStatement pstmt=null;

		//ResultSet rset=null; -> 이제 안만들어도된다. select가아니므로 결과값이 돌아올 필요가 없기 때문이다.

		int result=0; //결과를 숫자로 받을려고 한다.



		try {





			//String query="delete from member where member_id='"+userId+"'";

			String query="delete from member where USERID=?";

			pstmt = conn.prepareStatement(query);

			

			pstmt.setString(1, userId);

			System.out.println(query);

			//stmt.executeQuery(sql) -> select 전용이다.



			//0이넘어오면 정상적으로되었다 하면되고 아니면 정상가입 안되었다고 하면된다.

			result=pstmt.executeUpdate(); //return 타입이 int형이다.



			//commit과 rollback가 없을경우 트랜잭션을 계속 잡고 있게 된다.

			



		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}finally {

			JDBCTemplate.close(pstmt);

			



		}



		return result;

	}

}
package org.kh.member.model.service;



import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

import java.util.ArrayList;



import org.kh.common.JDBCTemplate;

import org.kh.member.dao.MemberDao;

import org.kh.member.model.vo.Member;



public class MemberService {





	// 서비스 클래스의 역할

	// 기존에는 DAO가 DBMS와 연결하고 커밋과 롤백시에도 직접 처리 하였음

	// DAO는 DBMS에 접근하여 데이터를 가져오는게 핵심 역할일뿐

	// 실제 DBMS에 연결하거나 커밋, 롤백 작업을 하는 것은 확장성 문제가 발생할 수 있음

	// 그렇기 때문에 이제는 서비스에서 DBMS와의 연결 작업 (드라이브 등록 및 Connection)을

	// 진행하고, 트랜잭션 관리를 처리하는 역할을 함

	// Service에서 연결을 한 후 연결 정보를 다 insert나 delete로 준다. 



	public ArrayList<Member> selectMemberAll() {

		//서비스는 DB에 연결하는 역할

		Connection conn = JDBCTemplate.getConnection();

		//getConnection()앞에 JDBCTemplate를 계속 붙이는데 이걸 다르게 할 수 있다.

		

		ArrayList<Member> list=null;

		list = new MemberDao().selectMemberAll(conn);

		//	MemberDao dao = new MemberDao();

		//list = dao.selectMemberAll(conn);

		

		JDBCTemplate.close(conn);





		return list;



	}



	//회원 가입 메소드

	public int insertMember(Member m) {

		//서비스역할 드라이버 등록

		Connection conn=JDBCTemplate.getConnection();



		int result=0;

		result = new MemberDao().insertMember(m,conn);//커넥션정보도 같이 보내줘야 한다.

	

		if(result>0) {

			JDBCTemplate.commit(conn);

		}

		else {

			JDBCTemplate.rollback(conn);

		}

		

		JDBCTemplate.close(conn);

		

		return result; //최종 결과값으로 컨트롤러에게 전달

	}



	//회원아이디검색

	public Member selectMemberId(String userId) {

		//서비스는 DB에 연결하는 역할

		Connection conn=JDBCTemplate.getConnection();

		Member m =null;

		m= new MemberDao().selectMemberId(userId, conn);

		

		JDBCTemplate.close(conn);

		



		return m;

	}







	//회원 이름검색

	public ArrayList<Member> selectMemberName(String userName) {

		Connection conn=JDBCTemplate.getConnection();

		ArrayList<Member> list=null;

		Member m = new Member();

//		MemberDao dao = new MemberDao();

//		list = dao.selectMemberName(userName,conn);

		list=new MemberDao().selectMemberName(userName, conn);



		JDBCTemplate.close(conn);



		return list;



	}



	//회원 정보 수정 메소드

	public int updateMember(String userId,Member m) {

		Connection conn=JDBCTemplate.getConnection();

		int result=0;

//		MemberDao dao = new MemberDao();

//		result=dao.updatemember(userId,m,conn);

		result=new MemberDao().updatemember(userId, m, conn);

		

		

		

		if(result>0) {

			JDBCTemplate.commit(conn);

		}

		else {

			JDBCTemplate.rollback(conn);

		}

		

		

		JDBCTemplate.close(conn);



		return result;

	}



	//회원 삭제 메소드

	public int deleteMember(String userId) {

		Connection conn=JDBCTemplate.getConnection();

		int result=0;



		MemberDao dao = new MemberDao();

		result=dao.deleteMember(userId,conn);



		if(result>0) {

			JDBCTemplate.commit(conn);

		}

		else {

			JDBCTemplate.rollback(conn);

		}



		JDBCTemplate.close(conn);







		return result;



	}



}

package com.uni.member.model.service;

import static com.uni.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.uni.member.model.dao.MemberDAO;
import com.uni.member.model.dto.Member;

// service 에는 비지니스 로직이 들어간다. 가공을 하고 Connection 연결하고, Dao 에 있던 것을 분리해서 가져오고
// 값만 Dao 에서 트랜젝션을 처리하든지, controller 에게 return 해줘야한다.
// service 에 호출하는것으로 변경  Service에서 Connection 에 연결   
/* Service 클레스에서 메소드 작성 방법
	 * 1) Controller로 부터 인지를 전달받음
	 * 2) Connection 객체 생성
	 * 3) Dao 객체 생성
	 * 4) Dao로 생성한 Connection 객체와 인자를 전달
	 * 5) Dao 수행 결과를 가지고 비즈니스 로직 및 트랜잭션 관리를 함 */
public class MemberService {

	public ArrayList<Member> selectAll() {
		Connection conn = getConnection();//Connection 연결
		
		ArrayList<Member> list = new MemberDAO().selectAll(conn);//selectAll에 Connection 객체를 담은conn을 넘겨오는것을 받는다.
		return list; //받아온 list를 다시 리턴해준다.
	}

	public Member selectOne(String memberId) {
		Connection conn = getConnection(); //Connection 연결
		Member m = new MemberDAO().selectOne(conn, memberId); // Connection 객체를 넘겨준것을 받는다. 
		
		return m;// 받아온 Member 객체인 m 을 다시 리턴해준다.
	}

	public List<Member> selectByName(String memberName) {
		Connection conn = getConnection(); //Connection 연결
		List<Member> list = new MemberDAO().selectByName(conn,memberName);
		return list;//받아온 list를 다시 리턴해준다.
	}

	public int inserMember(Member m) {
		Connection conn = getConnection(); //Connection 연결
		int result = new MemberDAO().inserMember(conn,m);//MemberDAO에 있는 insertMember호출해서 결과를 가져와서 Connection 연결 하는부분을 같이 인자로 담아서 보내준다.
		
		if (result >0) commit(conn); // 만약 0 보다 크면 성공한경우이기때문에 commit 실행
		else rollback(conn);// insert 가 제대로 되지 않아서 0보다 크지 않기때문에 되돌리기 실행
		
		return result;
	}

	public int updateMember(Member m) {
		Connection conn = getConnection(); //Connection 연결
		int result = new MemberDAO().updateMember(conn,m);
		
		if (result >0) commit(conn); // 만약 0 보다 크면 성공한경우이기때문에 commit 실행
		else rollback(conn);// insert 가 제대로 되지 않아서 0보다 크지 않기때문에 되돌리기 실행
		
		return result;
	}

	public int deleteMember(String userId) {
		Connection conn = getConnection(); //Connection 연결
		int result = new MemberDAO().deleteMember(conn,userId);
		
		if (result >0) commit(conn); // 만약 0 보다 크면 성공한경우이기때문에 commit 실행
		else rollback(conn);// insert 가 제대로 되지 않아서 0보다 크지 않기때문에 되돌리기 실행
		
		return result;
	}

	public void exitProgram() {
		close(getConnection());//닫아주면 JDBC에서 close Connection 한것을 호출. 
		
	}

	public List<Member> selectAllDeleteMember() {
		Connection conn = getConnection(); //Connection 연결
		
		List<Member> list = new MemberDAO().selectAllDeleteMember(conn);//받아온 매개변수 가 없기때문에 Connection 객체만 넘겨준다.
		return list;// 받아올 list 를 리턴해준다.
	}

}

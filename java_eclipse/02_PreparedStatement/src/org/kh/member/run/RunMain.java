package org.kh.member.run;



import org.kh.member.view.MemberMgr;



public class RunMain {

	public static void main(String[] args) {

		MemberMgr mgr = new MemberMgr();
		mgr.mainMenu();

		

		

		//new MemberMgr().mainMenu(); //객체 만들자 마자 실행

		//한번만 만들어서 실행하는걸로 challenge방식으로 할 수도 있다.

	}

}

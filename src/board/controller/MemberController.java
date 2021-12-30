package board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import board.container.Container;
import board.dto.Article;
import board.dto.Member;
import board.util.Util;

public class MemberController extends Controller {

	private Scanner sc;
	private List<Member> members;
	private String command;
	private String actionMethodName;

	public MemberController(Scanner sc) {
		this.sc = sc;
		members = Container.memberDao.members;
	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		case "whoami":
			showWhoAmI();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다.");
			break;
		}
	}

	private void doLogin() {

		System.out.print("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.print("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();

		Member member = getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println("해당 회원은 존재하지 않습니다.");
			return;
		}

		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("틀린 비밀번호입니다. 확인해주세요.");
			return;
		}

		loginedMember = member;
		System.out.println("== 로그인 완료 ==");
		System.out.printf("%s님 환영합니다.\n", loginedMember.name);

	}

	private void doLogout() {

		System.out.printf("%s님 로그아웃 되었습니다.\n", loginedMember.name);
		loginedMember = null;
	}

	private void showWhoAmI() {

		if (loginedMember == null) {
			System.out.println("로그아웃 상태입니다.");
			return;
		}

		System.out.println("== 로그인 된 회원의 정보 ==");
		System.out.printf("로그인 아이디 : %s\n", loginedMember.loginId);
		System.out.printf("이름 : %s\n", loginedMember.name);

	}

	private void doJoin() {

		int id = Container.memberDao.getNewId();
		String loginId = null;

		System.out.println("== 회원가입 ==");

		while (true) {
			System.out.print("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (isJoinableLoginId(loginId) == false) {
				System.out.printf("%s는 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}

			break;
		}

		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {
			System.out.print("로그인 비밀번호 : ");
			loginPw = sc.nextLine();

			System.out.print("로그인 비밀번호 확인 : ");
			loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호를 다시 입력해주세요");
				continue;
			}

			break;
		}

		System.out.print("이름 : ");
		String name = sc.nextLine();

		String regDate = Util.getCurrentDate();

		Member member = new Member(id, regDate, loginId, loginPw, name);

		Container.memberDao.add(member);

		System.out.printf("%s님 환영합니다. %d번 회원가입되었습니다.\n", name, id);

	}

	private Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return null;
		}

		return members.get(index);
	}

	private boolean isJoinableLoginId(String loginId) {

		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}

		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {

		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}

		return -1;
	}

	public void makeTestDate() {
		System.out.println("테스트를 위한 회원 데이터를 생성합니다.");

		Container.memberDao
				.add(new Member(Container.memberDao.getNewId(), Util.getCurrentDate(), "admin", "admin", "관리자"));
		Container.memberDao
				.add(new Member(Container.memberDao.getNewId(), Util.getCurrentDate(), "test1", "test1", "홍길동"));
		Container.memberDao
				.add(new Member(Container.memberDao.getNewId(), Util.getCurrentDate(), "test2", "test2", "임꺽정"));

	}

}

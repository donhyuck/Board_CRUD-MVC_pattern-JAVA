package board.controller;

import board.dto.Member;

public abstract class Controller {

	public static Member loginedMember; // 각각의 Controller에서 접근

	public abstract void doAction(String command, String actionMethodName);

	public abstract void makeTestDate();

	public static boolean isLogined() {
		return loginedMember != null;
	}
}

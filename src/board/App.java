package board;

import java.util.Scanner;

import board.controller.ArticleController;
import board.controller.Controller;
import board.controller.ExportController;
import board.controller.MemberController;

public class App {

	public void start() {

		System.out.println("== 프로그램 시작 ==");
		Scanner sc = new Scanner(System.in);

		ArticleController articleController = new ArticleController(sc);
		MemberController memberController = new MemberController(sc);
		ExportController exportController = new ExportController(sc);

		articleController.makeTestDate();
		memberController.makeTestDate();
		exportController.makeTestDate();

		while (true) {
			System.out.print("명령어 : ");
			String command = sc.nextLine();

			command.trim();

			if (command.equals("system exit")) {
				break;
			}

			if (command.length() == 0) {
				continue;
			}

			String[] commandBits = command.split(" ");

			if (commandBits.length == 1) {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}

			String controllerName = commandBits[0];
			String actionMethodName = commandBits[1];

			Controller controller = null;

			if (controllerName.equals("article")) {
				controller = articleController;

			} else if (controllerName.equals("member")) {
				controller = memberController;

			} else if (controllerName.equals("export")) {
				controller = exportController;

			} else {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}

			String actionName = controllerName + "/" + actionMethodName;

			// 명령어 작동전에 필요한 작업에 따라 로그인/로그아웃을 판단
			switch (actionName) {
			case "article/write":
			case "article/delete":
			case "article/modify":
			case "member/logout":
				if (controller.isLogined() == false) {
					System.out.println("로그인 후 이용해주세요");
					continue;
				}
				break;
			}

			switch (actionName) {
			case "member/login":
			case "member/join":
				if (controller.isLogined()) {
					System.out.println("로그아웃 후 이용해주세요");
					continue;
				}
				break;
			}

			controller.doAction(command, actionMethodName);
		}

		sc.close();
		System.out.println("== 프로그램 종료 ==");
	}
}

package board;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import board.controller.ArticleController;
import board.controller.Controller;
import board.controller.MemberController;
import board.dto.Article;
import board.dto.Member;

public class App {

	public void start() {

		System.out.println("== 프로그램 시작 ==");
		Scanner sc = new Scanner(System.in);

		ArticleController articleController = new ArticleController(sc);
		MemberController memberController = new MemberController(sc);

		articleController.makeTestDate();

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

			} else {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}

			controller.doAction(command, actionMethodName);
		}

		sc.close();
		System.out.println("== 프로그램 종료 ==");
	}
}

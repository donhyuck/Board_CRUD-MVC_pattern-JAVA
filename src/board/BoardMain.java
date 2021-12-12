package board;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		List<Article> articles = new ArrayList<>();
		int lastId = 1;

		System.out.println("== 프로그램 시작 ==");

		while (true) {
			System.out.print("명령어 : ");
			String command = sc.nextLine();

			command.trim();

			if (command.length() == 0) {
				continue;
			}

			if (command.equals("article list")) {
				System.out.println("== 게시물 목록 ==");
				System.out.println("  번호  |  제목  ");

				for (int i = 0; i < articles.size(); i++) {
					Article currentArticle = articles.get(i);
					System.out.printf("   %s   |  %s \n", currentArticle.id, currentArticle.title);
				}
			}

			else if (command.equals("article write")) {

				int id = lastId++;

				System.out.print("제목 : ");
				String title = sc.nextLine();

				System.out.print("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, title, body);

				articles.add(article);

				System.out.println("게시물 등록이 완료되었습니다.");

			}

			else if (command.equals("system exit")) {
				break;
			}

			else {
				System.out.printf("%s는 존재하지 않는 명령어 입니다.\n", command);
			}
		}

		System.out.println("== 프로그램 종료 ==");

	}
}

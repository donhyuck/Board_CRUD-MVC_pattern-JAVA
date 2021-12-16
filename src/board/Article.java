package board;

public class Article {
	int id; // 게시물 번호
	String regDate; // 게시물 작성일
	String title; // 제목
	String body; // 내용
	int hit; // 게시물 조회수

	public Article(int id, String regDate, String title, String body) {
		this(id, regDate, title, body, 0);
	}

	public Article(int id, String regDate, String title, String body, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.hit = hit;
	}

	public void increaseHit() {
		hit++;
	}
}

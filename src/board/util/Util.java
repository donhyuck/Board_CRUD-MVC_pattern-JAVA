package board.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {

	public static String getCurrentDate() {
		// 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
		LocalDate now = LocalDate.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		String formatedNow = now.format(formatter);

		return formatedNow;
	}

}

package com.example.demo.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public final class CalendarUtil {

	private static Calendar calendar = Calendar.getInstance();

	private CalendarUtil() {
	}

	/**
	 * 休日かどうか判定
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static boolean isHoliday(int year, int month, int day) {
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, day);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
	}

	// TODO とりあえず2024年だけ。要検討。
	/**
	 * 祝日かどうか判定
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static boolean isNationalHoliday(int year, int month, int day) {

		return isNationalHolidayIn2024(year, month, day);

	}

	/**
	 * 2024年の祝日かどうか判定
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	private static boolean isNationalHolidayIn2024(int year, int month, int day) {
		if (year != 2024) {
			return false;
		}

		/**
		 * 1 1/1 (月) 元日 2 1/8 (月) 成人の日 3 2/11 (日) 建国記念の日 4 2/12 (月) 振替休日 5 2/23 (金)
		 * 天皇誕生日 6 3/20 (水) 春分の日 7 4/29 (月) 昭和の日 8 5/3 (金) 憲法記念日 9 5/4 (土) みどりの日 10 5/5
		 * (日) こどもの日 11 5/6 (月) 振替休日 12 7/15 (月) 海の日 13 8/11 (日) 山の日 14 8/12 (月) 振替休日 15
		 * 9/16 (月) 敬老の日 16 9/22 (日) 秋分の日 17 9/23 (月) 振替休日 18 10/14 (月) スポーツの日 19 11/3
		 * (日) 文化の日 20 11/4 (月) 振替休日 21 11/23 (土) 勤労感謝の日
		 */
		Map<Integer, Integer> map = new HashMap<>();
		map.put(1, 1);
		map.put(1, 8);
		map.put(2, 11);
		map.put(2, 12);
		map.put(2, 23);
		map.put(3, 20);
		map.put(4, 29);
		map.put(5, 3);
		map.put(5, 4);
		map.put(5, 5);
		map.put(5, 6);
		map.put(7, 15);
		map.put(8, 11);
		map.put(8, 12);
		map.put(9, 16);
		map.put(9, 22);
		map.put(9, 23);
		map.put(10, 14);
		map.put(11, 3);
		map.put(11, 4);
		map.put(11, 23);

		return map.containsKey(month) && map.containsValue(day);
	}
}

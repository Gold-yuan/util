package sichuan.ytf.calendar.holiday;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 计算年假 输出几月份有几天年假
 * 
 * @author adminytf
 */
public class NewYearHoliday {

	public static void main(String[] args) throws Exception {
//		getSevenDayForNewYear();
//		beforeHoliday20();
		afterHoliday30();
	}

	public static void getSevenDayForNewYear() throws Exception {
		int holiday = 7;
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		for (int i = 2010; i <= 2049; i++) {
			String solarDateStr = CalendarUtil.lunarToSolar(i + "0101", false);
			Calendar Jan = Calendar.getInstance();
			Jan.setTime(sf.parse(solarDateStr));
			Jan.add(Calendar.DAY_OF_MONTH, -1);// 减一天为除夕
			Date solarDate = Jan.getTime();
			//System.out.println("除夕"+sf.format(solarDate));
			
			// 传入日期
			Calendar solarDay = Calendar.getInstance();
			solarDay.setTime(solarDate);

			// 月末
			Calendar monthLastDay = Calendar.getInstance();
			monthLastDay.setTime(solarDate);
			monthLastDay.add(Calendar.MONTH, 1);
			monthLastDay.set(Calendar.DATE, 1);
			monthLastDay.add(Calendar.DAY_OF_MONTH, -1);
			// System.out.print(" 月末：" + sf.format(monthLastDay.getTime()));

			// 月初
			Calendar monthFirstDay = Calendar.getInstance();
			monthFirstDay.setTime(solarDate);
			monthFirstDay.set(Calendar.DATE, 1);
			// System.out.println(" 月初：" + sf.format(monthFirstDay.getTime()));

			// 设置7天前后的区间。1+5 = 6 ，若〉6则至少是7开始。所以加5天
			Calendar betweenStartDay = Calendar.getInstance();
			betweenStartDay.setTime(monthFirstDay.getTime());
			betweenStartDay.add(Calendar.DAY_OF_MONTH, -1);
			Calendar betweenEndDay = Calendar.getInstance();
			betweenEndDay.setTime(monthLastDay.getTime());
			betweenEndDay.add(Calendar.DAY_OF_MONTH, -5);
			// 1.放假7天都在当月。大于上月末，小于这月末-5
			if (solarDay.after(betweenStartDay) && solarDay.before(betweenEndDay)) {
				sout(solarDay, holiday);
			}
			// 2.放假7天一部分在后一月
			else {
				int nowDay = monthLastDay.get(Calendar.DAY_OF_MONTH) - solarDay.get(Calendar.DAY_OF_MONTH) + 1;

				// 后一月几天
				Calendar afterDayCal = Calendar.getInstance();
				afterDayCal.setTime(solarDate);
				afterDayCal.add(Calendar.MONTH, 1);
				int afterDay = holiday - nowDay;
				sout(monthLastDay, nowDay, afterDayCal, afterDay);

			}
		}
	}
	public static void sout(Calendar cal, int day) {
		int month = cal.get(Calendar.MONTH);
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		for (int i = 0; i < 12; i++) {
			c.setTime(cal.getTime());
			c.set(Calendar.MONTH, i);
			if (i == month) {
				System.out.println("农历年,全部航线," + sf.format(cal.getTime()) + "," + day);
			} else {
				System.out.println("农历年,全部航线," + sf.format(c.getTime()) + ",0");
			}
		}
	}

	
	public static void sout(Calendar before, int dayb, Calendar after, int daya) {

		int monthb = before.get(Calendar.MONTH);
		int montha = after.get(Calendar.MONTH);
		Calendar c = Calendar.getInstance();
		c.setTime(before.getTime());
		c.set(Calendar.MONTH, 0);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		for (int i = 0; i < 12; i++) {
			if (i == monthb) {
				System.out.println("农历年,全部航线," + sf.format(before.getTime()) + "," + dayb);
			} else if (i == montha) {
				System.out.println("农历年,全部航线," + sf.format(after.getTime()) + "," + daya);
			} else {
				System.out.println("农历年,全部航线," + sf.format(c.getTime()) + ",0");
			}
			c.add(Calendar.MONTH, 1);
		}
	}

	public static void sout(String ym, int val) {
		System.out.println("农历年前,全部航线," + ym + ","+val);
	}
	/**
	 * 获取春节前20天时间所在月份
	 * @throws Exception
	 */
	public static void beforeHoliday20() throws Exception {

		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		for (int i = 2010; i <= 2049; i++) {
			String solarDateStr = CalendarUtil.lunarToSolar(i + "0101", false);
			Calendar Jan = Calendar.getInstance();
			Jan.setTime(sf.parse(solarDateStr));
			Jan.add(Calendar.DAY_OF_MONTH, -1);// 减一天为除夕
			Date solarDate = Jan.getTime();
			// System.out.println("除夕"+sf.format(solarDate));

			// 除夕
			Calendar solarDay = Calendar.getInstance();
			solarDay.setTime(solarDate);
			// 除夕前20天
			Calendar beforeStartDay = Calendar.getInstance();
			beforeStartDay.setTime(solarDay.getTime());
			beforeStartDay.add(Calendar.DATE, -20);

			int solarMonth = solarDay.get(Calendar.MONTH);
			int beforeStartMonth = beforeStartDay.get(Calendar.MONTH);
			int year = solarDay.get(Calendar.YEAR);

			// 放假前20天都在同一月
			if (solarMonth == beforeStartMonth) {
				for (int j = 0; j < 12; j++) {
					String month = j<9?"0"+(j+1):""+(j+1);
					if (j == solarMonth) {
						sout(year + "-" + month, 20);
					} else {
						sout(year + "-" + month, 0);
					}
				}
			}
			// 放假前20天不在同一月
			else {
				// 月初
				Calendar beginOfMonth = Calendar.getInstance();
				beginOfMonth.setTime(solarDay.getTime());
				beginOfMonth.set(Calendar.DAY_OF_MONTH, 1);
				long beforeMillis = beginOfMonth.getTimeInMillis() - beforeStartDay.getTimeInMillis();
				int beforeday = (int) (beforeMillis / 1000 / 60 / 60 / 24);
				int nowMonthDay = 20 - beforeday;
				for (int j = 0; j < 12; j++) {
					String month = j<9?"0"+(j+1):""+(j+1);
					if (j == beforeStartMonth) {
						sout(year + "-" + month, beforeday);
					} else if (j == solarMonth) {
						sout(year + "-" + month, nowMonthDay);
					} else {
						sout(year + "-" + month, 0);
					}
				}
			}
		}
	}
	/**
	 * 获取春节后30天时间所在月份
	 * @throws Exception
	 */
	public static void afterHoliday30() throws Exception {
		int holiday = 7;
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		for (int i = 2010; i <= 2049; i++) {
			String solarDateStr = CalendarUtil.lunarToSolar(i + "0101", false);
			Calendar Jan = Calendar.getInstance();
			Jan.setTime(sf.parse(solarDateStr));
			Jan.add(Calendar.DAY_OF_MONTH, -1);// 减一天为除夕
			Date solarDate = Jan.getTime();
			// System.out.println("除夕"+sf.format(solarDate));

			// 除夕
			Calendar solarDay = Calendar.getInstance();
			solarDay.setTime(solarDate);
			// 假日最后一天
			Calendar lastDay = Calendar.getInstance();
			lastDay.setTime(solarDate);
			lastDay.add(Calendar.DATE, holiday-1);
			// 假日后30天
			Calendar afterStartDay = Calendar.getInstance();
			afterStartDay.setTime(lastDay.getTime());
			afterStartDay.add(Calendar.DATE, 30);
			
			int lastHolidayMonth = lastDay.get(Calendar.MONTH);
			int afterStartMonth = afterStartDay.get(Calendar.MONTH);
			int year = lastDay.get(Calendar.YEAR);

			// 放假后30天都在同一月
			if (lastHolidayMonth == afterStartMonth) {
				for (int j = 0; j < 12; j++) {
					String month = j < 9 ? "0" + (j + 1) : "" + (j + 1);
					if (j == lastHolidayMonth) {
						sout(year + "-" + month, 30);
					} else {
						sout(year + "-" + month, 0);
					}
				}
			}
			// 放假前20天不在同一月
			else {
				// 月初
				int afterday = afterStartDay.get(Calendar.DATE);
				int nowday = 30 - afterday;
				for (int j = 0; j < 12; j++) {
					String month = j < 9 ? "0" + (j + 1) : "" + (j + 1);
					if (j == lastHolidayMonth) {
						sout(year + "-" + month, nowday);
					} else if (j == afterStartMonth) {
						sout(year + "-" + month, afterday);
					} else {
						sout(year + "-" + month, 0);
					}
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

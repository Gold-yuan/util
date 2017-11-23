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

}

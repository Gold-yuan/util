package sichuan.ytf.calendar.holiday;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ������� ������·��м������
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
			Jan.add(Calendar.DAY_OF_MONTH, -1);// ��һ��Ϊ��Ϧ
			Date solarDate = Jan.getTime();
			//System.out.println("��Ϧ"+sf.format(solarDate));
			
			// ��������
			Calendar solarDay = Calendar.getInstance();
			solarDay.setTime(solarDate);

			// ��ĩ
			Calendar monthLastDay = Calendar.getInstance();
			monthLastDay.setTime(solarDate);
			monthLastDay.add(Calendar.MONTH, 1);
			monthLastDay.set(Calendar.DATE, 1);
			monthLastDay.add(Calendar.DAY_OF_MONTH, -1);
			// System.out.print(" ��ĩ��" + sf.format(monthLastDay.getTime()));

			// �³�
			Calendar monthFirstDay = Calendar.getInstance();
			monthFirstDay.setTime(solarDate);
			monthFirstDay.set(Calendar.DATE, 1);
			// System.out.println(" �³���" + sf.format(monthFirstDay.getTime()));

			// ����7��ǰ������䡣1+5 = 6 ������6��������7��ʼ�����Լ�5��
			Calendar betweenStartDay = Calendar.getInstance();
			betweenStartDay.setTime(monthFirstDay.getTime());
			betweenStartDay.add(Calendar.DAY_OF_MONTH, -1);
			Calendar betweenEndDay = Calendar.getInstance();
			betweenEndDay.setTime(monthLastDay.getTime());
			betweenEndDay.add(Calendar.DAY_OF_MONTH, -5);
			// 1.�ż�7�춼�ڵ��¡���������ĩ��С������ĩ-5
			if (solarDay.after(betweenStartDay) && solarDay.before(betweenEndDay)) {
				sout(solarDay, holiday);
			}
			// 2.�ż�7��һ�����ں�һ��
			else {
				int nowDay = monthLastDay.get(Calendar.DAY_OF_MONTH) - solarDay.get(Calendar.DAY_OF_MONTH) + 1;

				// ��һ�¼���
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
				System.out.println("ũ����,ȫ������," + sf.format(cal.getTime()) + "," + day);
			} else {
				System.out.println("ũ����,ȫ������," + sf.format(c.getTime()) + ",0");
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
				System.out.println("ũ����,ȫ������," + sf.format(before.getTime()) + "," + dayb);
			} else if (i == montha) {
				System.out.println("ũ����,ȫ������," + sf.format(after.getTime()) + "," + daya);
			} else {
				System.out.println("ũ����,ȫ������," + sf.format(c.getTime()) + ",0");
			}
			c.add(Calendar.MONTH, 1);
		}
	}

}

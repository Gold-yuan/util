package sichuan.ytf.calendar.holiday;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ����: ����ת�������ࣺ�������������ڻ���(�������ڷ�Χ19000101~20491229)<br>
 */
public class CalendarUtil {
	// private static final Logger logger =
	// LoggerFactory.getLogger(CalendarUtil.class);
	// �����������ڲ���1900�굽2049��
	private final static int[] LUNAR_INFO = { 0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0,
			0x09ad0, 0x055d2, 0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977,
			0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566, 0x0d4a0,
			0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0,
			0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573,
			0x052d0, 0x0a9a8, 0x0e950, 0x06aa0, 0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950,
			0x05b57, 0x056a0, 0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6,
			0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570, 0x04af5, 0x04970,
			0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0, 0x0c960, 0x0d954, 0x0d4a0, 0x0da50,
			0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0,
			0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260,
			0x0ea65, 0x0d530, 0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45,
			0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0 };

	// �����������С���
	private final static int MIN_YEAR = 1900;
	// ���������������
	private final static int MAX_YEAR = 2049;
	// �����Ƿ�������
	private static boolean isLeapYear;
	// �������ڼ������
	private final static String START_DATE = "19000130";

	/**
	 * �������� {@code year}�����ĸ��� 1-12 , û�򴫻� 0
	 * 
	 * @param year
	 *            ������
	 * @return (int)�·�
	 * @author liu 2015-1-5
	 */
	private static int getLeapMonth(int year) {
		return (int) (LUNAR_INFO[year - 1900] & 0xf);
	}

	/**
	 * ��������{@code year}�����¶�����
	 * 
	 * @param year
	 *            ������
	 * @return (int)����
	 * @author liu 2015-1-5
	 */
	private static int getLeapMonthDays(int year) {
		if (getLeapMonth(year) != 0) {
			if ((LUNAR_INFO[year - 1900] & 0xf0000) == 0) {
				return 29;
			} else {
				return 30;
			}
		} else {
			return 0;
		}
	}

	/**
	 * ��������{@code lunarYeay}��{@code month}�µ�����
	 * 
	 * @param lunarYeay
	 *            ������
	 * @param month
	 *            ������
	 * @return (int)��������
	 * @throws Exception
	 * @author liu 2015-1-5
	 */
	private static int getMonthDays(int lunarYeay, int month) throws Exception {
		if ((month > 31) || (month < 0)) {
			throw (new Exception("�·��д�"));
		}
		// 0X0FFFF[0000 {1111 1111 1111} 1111]�м�12λ����12���£�1Ϊ���£�0ΪС��
		int bit = 1 << (16 - month);
		if (((LUNAR_INFO[lunarYeay - 1900] & 0x0FFFF) & bit) == 0) {
			return 29;
		} else {
			return 30;
		}
	}

	/**
	 * ��������{@code year}���������
	 * 
	 * @param year
	 *            ������
	 * @return (int)������
	 * @author liu 2015-1-5
	 */
	private static int getYearDays(int year) {
		int sum = 29 * 12;
		for (int i = 0x8000; i >= 0x8; i >>= 1) {
			if ((LUNAR_INFO[year - 1900] & 0xfff0 & i) != 0) {
				sum++;
			}
		}
		return sum + getLeapMonthDays(year);
	}

	/**
	 * ��������������������������
	 * 
	 * @param startDate
	 *            ��ʼʱ��
	 * @param endDate
	 *            ����ʱ��
	 * @return (int)����
	 * @author liu 2017-3-2
	 */
	private static int daysBetween(Date startDate, Date endDate) {
		int days = 0;
		// ��ת��������ʱ�����ת����Calendar����
		Calendar can1 = Calendar.getInstance();
		can1.setTime(startDate);
		Calendar can2 = Calendar.getInstance();
		can2.setTime(endDate);
		// �ó��������
		int year1 = can1.get(Calendar.YEAR);
		int year2 = can2.get(Calendar.YEAR);
		// ����

		Calendar can = null;
		// ���can1 < can2
		// ��ȥС��ʱ������һ���Ѿ����˵�����
		// ���ϴ��ʱ���ѹ�������
		if (can1.before(can2)) {
			days -= can1.get(Calendar.DAY_OF_YEAR);
			days += can2.get(Calendar.DAY_OF_YEAR);
			can = can1;
		} else {
			days -= can2.get(Calendar.DAY_OF_YEAR);
			days += can1.get(Calendar.DAY_OF_YEAR);
			can = can2;
		}
		for (int i = 0; i < Math.abs(year2 - year1); i++) {
			// ��ȡС��ʱ�䵱ǰ���������
			days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
			// �ټ�����һ�ꡣ
			can.add(Calendar.YEAR, 1);
		}
		return days;
	}

	/**
	 * ������������Ƿ�Ϸ�
	 * 
	 * @param lunarYear
	 *            ������
	 * @param lunarMonth
	 *            ������
	 * @param lunarDay
	 *            ������
	 * @param leapMonthFlag
	 *            ���±�־
	 * @throws Exception
	 */
	private static void checkLunarDate(int lunarYear, int lunarMonth, int lunarDay, boolean leapMonthFlag)
			throws Exception {
		if ((lunarYear < MIN_YEAR) || (lunarYear > MAX_YEAR)) {
			throw (new Exception("�Ƿ�ũ����ݣ�"));
		}
		if ((lunarMonth < 1) || (lunarMonth > 12)) {
			throw (new Exception("�Ƿ�ũ���·ݣ�"));
		}
		if ((lunarDay < 1) || (lunarDay > 30)) { // �й��������30��
			throw (new Exception("�Ƿ�ũ��������"));
		}

		int leap = getLeapMonth(lunarYear);// �������Ӧ�����ĸ���
		if ((leapMonthFlag == true) && (lunarMonth != leap)) {
			throw (new Exception("�Ƿ����£�"));
		}
	}

	/**
	 * ����ת��Ϊ����
	 * 
	 * @param lunarDate
	 *            ��������,��ʽYYYYMMDD
	 * @param leapMonthFlag
	 *            �Ƿ�Ϊ����
	 * @return ��������,��ʽ��YYYYMMDD
	 * @throws Exception
	 * @author liu 2015-1-5
	 */
	public static String lunarToSolar(String lunarDate, boolean leapMonthFlag) throws Exception {
		int lunarYear = Integer.parseInt(lunarDate.substring(0, 4));
		int lunarMonth = Integer.parseInt(lunarDate.substring(4, 6));
		int lunarDay = Integer.parseInt(lunarDate.substring(6, 8));

		checkLunarDate(lunarYear, lunarMonth, lunarDay, leapMonthFlag);

		int offset = 0;

		for (int i = MIN_YEAR; i < lunarYear; i++) {
			int yearDaysCount = getYearDays(i); // ������ĳ������
			offset += yearDaysCount;
		}
		// �����������
		int leapMonth = getLeapMonth(lunarYear);

		if (leapMonthFlag & leapMonth != lunarMonth) {
			throw (new Exception("����������±�־����"));
		}

		// ����û�����»��·��������»������ͬ�����·�
		if (leapMonth == 0 || (lunarMonth < leapMonth) || (lunarMonth == leapMonth && !leapMonthFlag)) {
			for (int i = 1; i < lunarMonth; i++) {
				int tempMonthDaysCount = getMonthDays(lunarYear, i);
				offset += tempMonthDaysCount;
			}

			// ��������Ƿ���������
			if (lunarDay > getMonthDays(lunarYear, lunarMonth)) {
				throw (new Exception("���Ϸ���ũ�����ڣ�"));
			}
			offset += lunarDay; // ���ϵ��µ�����
		} else {// ���������£����·����ڻ��������
			for (int i = 1; i < lunarMonth; i++) {
				int tempMonthDaysCount = getMonthDays(lunarYear, i);
				offset += tempMonthDaysCount;
			}
			if (lunarMonth > leapMonth) {
				int temp = getLeapMonthDays(lunarYear); // ������������
				offset += temp; // ������������

				if (lunarDay > getMonthDays(lunarYear, lunarMonth)) {
					throw (new Exception("���Ϸ���ũ�����ڣ�"));
				}
				offset += lunarDay;
			} else { // �����Ҫ����������£���Ӧ���ȼ��������¶�Ӧ����ͨ�µ�����
				// ������Ϊ����
				int temp = getMonthDays(lunarYear, lunarMonth); // �������������
				offset += temp;

				if (lunarDay > getLeapMonthDays(lunarYear)) {
					throw (new Exception("���Ϸ���ũ�����ڣ�"));
				}
				offset += lunarDay;
			}
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date myDate = null;
		myDate = formatter.parse(START_DATE);
		Calendar c = Calendar.getInstance();
		c.setTime(myDate);
		c.add(Calendar.DATE, offset);
		myDate = c.getTime();

		return formatter.format(myDate);
	}

	/**
	 * ��������ת��Ϊ��������
	 * 
	 * @param solarDate
	 *            ��������,��ʽYYYYMMDD
	 * @return ��������
	 * @throws Exception
	 * @author liu 2015-1-5
	 */
	public static String solarToLunar(String solarDate) throws Exception {
		int i;
		int temp = 0;
		int lunarYear;
		int lunarMonth; // ũ���·�
		int lunarDay; // ũ�����µڼ���
		boolean leapMonthFlag = false;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date myDate = null;
		Date startDate = null;
		try {
			myDate = formatter.parse(solarDate);
			startDate = formatter.parse(START_DATE);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int offset = daysBetween(startDate, myDate);

		for (i = MIN_YEAR; i <= MAX_YEAR; i++) {
			temp = getYearDays(i); // ����ũ��������
			if (offset - temp < 1) {
				break;
			} else {
				offset -= temp;
			}
		}
		lunarYear = i;

		int leapMonth = getLeapMonth(lunarYear);// ����������ĸ���
		// �趨�����Ƿ�������
		if (leapMonth > 0) {
			isLeapYear = true;
		} else {
			isLeapYear = false;
		}

		for (i = 1; i <= 12; i++) {
			if (i == leapMonth + 1 && isLeapYear) {
				temp = getLeapMonthDays(lunarYear);
				isLeapYear = false;
				leapMonthFlag = true;
				i--;
			} else {
				temp = getMonthDays(lunarYear, i);
			}
			offset -= temp;
			if (offset <= 0) {
				break;
			}
		}

		offset += temp;
		lunarMonth = i;
		lunarDay = offset;

		return "������" + lunarYear + "��" + (leapMonthFlag & (lunarMonth == leapMonth) ? "��" : "") + lunarMonth + "��"
				+ lunarDay + "��";
	}

	public static void main(String[] args) throws Exception {
		System.out.println(CalendarUtil.lunarToSolar("19901204", false));
		System.out.println(CalendarUtil.lunarToSolar("19841021", true));
		System.out.println("************");
		System.out.println(CalendarUtil.solarToLunar("19000923"));
		System.out.println(CalendarUtil.solarToLunar("19000924"));
		System.out.println(CalendarUtil.solarToLunar("19001022"));
		System.out.println(CalendarUtil.solarToLunar("19001023"));

		System.out.println(CalendarUtil.solarToLunar("19900630"));
		System.out.println(CalendarUtil.solarToLunar("19841213"));
		System.out.println(CalendarUtil.solarToLunar("19910119"));
	}
}
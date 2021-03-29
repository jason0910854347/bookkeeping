package com.BookKeeping.V1.Util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DateUtil.
 */
public class DateUtil implements Serializable {
	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

	private static final long serialVersionUID = 1L;

	/** "yyyyMMdd". */
	public final static String PATTEN_YEAR_MONTH_DAY_NO_HYPHEN = "yyyyMMdd";

	/** "yyyyMMddHHmmss". */
	public final static String PATTEN_FULL_TIME_NO_HYPHEN = "yyyyMMddHHmmss";

	/** "yyyy-MM-dd". */
	public final static String PATTEN_YEAR_MONTH_DAY = "yyyy-MM-dd";

	/** yyyy-MM-dd HH:mm:ss. */
	public final static String PATTEN_FULL_TIME = "yyyy-MM-dd HH:mm:ss";

	/** yyyy/MM/dd HH:mm:ss. */
	public final static String PATTEN_FULL_TIME_SLASH = "yyyy/MM/dd HH:mm:ss";

	/** yyyy-MM-dd HH:mm:ss.SSS. */
	public final static String PATTEN_FULL_TIME_MS = "yyyy-MM-dd HH:mm:ss.SSS";

	/** yyyy/MM/dd */
	public final static String PATTEN_YEAR_MONTH_DAY_SLASH = "yyyy/MM/dd";

	/** yyyy */
	public final static String FIELD_YEAR = "yyyy";

	/** yyy/MM/dd */
	public final static String PATTEN_YEAR_MONTH_DAY_SLASH_TW = "yyy/MM/dd";

	/** "yyyMMdd". */
	public final static String PATTEN_YEAR_MONTH_DAY_NO_HYPHEN_TW = "yyyMMdd";

	/**
	 * 毫秒轉成秒.
	 *
	 * @param millsec the millsec
	 * @return the long
	 */
	public static long milliSecondToSec(long millsec) {
		return millsec / 1000;
	}

	/**
	 * 毫秒差值.
	 *
	 * @param millSec1 the mill sec 1
	 * @param millSec2 the mill sec 2
	 * @return the long
	 */
	public static long msDiff(long millSec1, long millSec2) {
		return millSec1 - millSec2;
	}

	/**
	 * 取得目前日期(Timestamp格式)
	 * 
	 * @return
	 */
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * Date轉日期字串
	 * 
	 * @param date
	 * @param patten
	 * @return
	 */
	public static String dateToString(Date date, String patten) {
		String str = "";
		try {
			if (date != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat(patten);
				str = dateFormat.format(date);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		return str;
	}

	/**
	 * Timestamp轉日期字串
	 * 
	 * @param time
	 * @param patten
	 * @return
	 */
	public static String timestampToString(Timestamp time, String patten) {
		String str = "";

		if (time != null) {
			str = DateFormatUtils.format(time, patten);
		}
		return str;
	}

	/**
	 * 日期字串轉Timestamp
	 * 
	 * @param str
	 * @param patten
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp strToTimestamp(String str, String patten) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(patten);
		Date date = dateFormat.parse(str);
		Timestamp timestamp = new Timestamp(date.getTime());

		return timestamp;
	}

	/**
	 * 日期字串轉Date
	 * 
	 * @param str
	 * @param patten
	 * @return
	 * @throws ParseException
	 */
	public static Date strToDate(String str, String patten) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(patten);
		Date date = dateFormat.parse(str);
		return date;
	}

	/**
	 * 字串日期格式轉換
	 * 
	 * @param strDate
	 * @param inPatten(輸入格式)
	 * @param outPatten(輸出格式)
	 * @return
	 * @throws ParseException
	 */
	public static String strDateFormat(String strDate, String inPatten, String outPatten) throws ParseException {
		Timestamp timestamp = null;
		timestamp = strToTimestamp(strDate, inPatten);
		return dateToString(timestamp, outPatten);

	}

	/**
	 * 民國日期格式轉換(yyy/MM/dd)
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String convertTWDate(String strDate, String inPatten, String outPatten) throws ParseException {
		SimpleDateFormat dateIn = new SimpleDateFormat(inPatten);
		SimpleDateFormat dateOut = new SimpleDateFormat(outPatten);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateIn.parse(strDate));
		if (cal.get(Calendar.YEAR) > 1492) {
			cal.add(Calendar.YEAR, -1911);
		}
		return dateOut.format(cal.getTime());
	}

	/**
	 * 取得當年度的最初時刻(ex:2018/01/01 00:00:00)
	 * 
	 * @param dt (年)
	 * @return
	 */
	public static Date getYearFirstTime(Date dt) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(calendar.DAY_OF_MONTH));

		return setDate(dt, 0, 0, 0);
	}

	/**
	 * 設定時間(Timestamp)
	 * 
	 * @param dt     : Date
	 * @param hour   : hour 12小時制
	 * @param minute : minute
	 * @param second :second
	 * @return Date
	 */
	public static Date setDate(Date dt, int hour, int minute, int second) {
		if (null == dt) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 取得當前年度 yyyy, Ex: 2020
	 *
	 * @return the now year
	 */
	public static String getNowYear() {
		// 取得當前年度
		String nowYear = dateToString(new Date(), DateUtil.FIELD_YEAR);
		return nowYear;
	}

	/**
	 * 修改日期(加減年日月)
	 * 
	 * @param timestamp
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp modifyDate(Timestamp timestamp, Integer year, Integer month, Integer day)
			throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp.getTime());
		cal.add(Calendar.YEAR, year);
		cal.add(Calendar.MONTH, month);
		cal.add(Calendar.DAY_OF_MONTH, day);

		return new Timestamp(cal.getTime().getTime());
	}

}

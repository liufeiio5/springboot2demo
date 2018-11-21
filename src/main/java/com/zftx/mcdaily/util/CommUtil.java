package com.zftx.mcdaily.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 公共类
 * 
 * @author qiu
 * 
 */
public class CommUtil {

	private static Calendar calendar = Calendar.getInstance();

	/**
	 * 用户注册时验证码的临时保存
	 */
	private static HashMap<String, String> codeMap = new HashMap<String, String>();

	/**
	 * 保存验证码
	 * 
	 * @param phoneNum
	 * @param code
	 */
	public static void setCode(String phoneNum, String code) {
		codeMap.put(phoneNum, code);
	}

	/**
	 * 获取保存的验证码
	 * 
	 * @param phoneNum
	 * @return
	 */
	public static String getCode(String phoneNum) {
		return codeMap.get(phoneNum);
	}

	/**
	 * 清除验证码
	 * 
	 * @param phoneNum
	 */
	public static void removeCode(String phoneNum) {
		codeMap.remove(phoneNum);
	}

	/**
	 * 获取系统日期
	 * 
	 * @return
	 */
	public static String getDay() {
		return calendar.get(Calendar.DAY_OF_MONTH) + "";
	}

	/**
	 * 获取小时
	 * 
	 * @return
	 */
	public static String getHour() {
		return calendar.get(Calendar.HOUR_OF_DAY) + "";
	}

	/**
	 * 年
	 * 
	 * @return
	 */
	public static String getYear() {
		return calendar.get(Calendar.YEAR) + "";
	}

	/**
	 * 月
	 * 
	 * @return
	 */
	public static String getMonth() {
		return calendar.get(Calendar.MONTH) + 1 + "";
	}

	/**
	 * 分
	 * 
	 * @return
	 */
	public static String getMinute() {
		return calendar.get(Calendar.MINUTE) + "";
	}

	/**
	 * 秒
	 * 
	 * @return
	 */
	public static String getSencond() {
		return calendar.get(Calendar.SECOND) + "";
	}

	/**
	 * 毫秒
	 * 
	 * @return
	 */
	public static String getMesc() {
		return calendar.getTimeInMillis() + "";
	}

	/**
	 * 获取格式化后的时间
	 * 
	 * @return 20180424121011
	 */
	public static String getDateFormat() {
		Date date = new Date(System.currentTimeMillis());
		return new SimpleDateFormat("yyyyMMddHHmmss").format(date);

	}


	/**
	 * 年月日字符串 格式化yyyyMMdd
	 *
	 * @return 2018-11-21
	 */
	public static String getDayStringWithMark(String str) {
		str=str.replaceAll("[[\\s-:punct:]]","");
		return str.toString();
	}

	/**
	 * 获取格式化后的时间(字符串)
	 *
	 * @return 2018-04-02 12:15:55
	 */
	public static String getDateWithMark() {
		Date date = new Date(System.currentTimeMillis());
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

	}

	/**
	 * 年月日
	 * 
	 * @return 20180412
	 */
	public static String getDate() {
		Date d = new Date(System.currentTimeMillis());
		return new SimpleDateFormat("yyyyMMdd").format(d);
	}


	/**
	 * xxxx年xx月xx日
	 * 
	 * @return 2018年4月12日 11时21分55秒
	 */
	public static String getDateWithWord() {
		Date date = new Date(System.currentTimeMillis());
		return new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(date);
	}

	/**
	 * 时分秒
	 * 
	 * @return 111252
	 */
	public static String getTime() {
		Date d = new Date(System.currentTimeMillis());
		return new SimpleDateFormat("HHmmss").format(d);
	}

	/**
	 * 产生Merid
	 * 
	 * @param ucount
	 *            用户总数
	 * @return
	 */
	public static String createMerId(int ucount) {
		Date date = new Date(System.currentTimeMillis());
		int c = 1001 + ucount;// 100000000+
		String merId = "B" + new SimpleDateFormat("yyMMddHHmmss").format(date) + c;
		return merId;
	}


	/**
	 * srcId
	 *
	 * @param
	 * @return
	 */
	public static String createSrcId() {
		Date date = new Date(System.currentTimeMillis());
		String srcId = "M" + new SimpleDateFormat("yyMMddHHmmss").format(date);
		return srcId;
	}

	/**
	 * 产生类目categoryId
	 *
	 * @param
	 * @return
	 */
	public static String createCategoryId() {
		Date date = new Date(System.currentTimeMillis());
		String categoryId = "CAT" + new SimpleDateFormat("yyMMddHHmmss").format(date);
		return categoryId;
	}

	/**
	 * 产生skuId
	 *
	 * @param
	 * @return
	 */
	public static String createSkuId() {
		Date date = new Date(System.currentTimeMillis());
		String skuId = "SKU" + new SimpleDateFormat("yyMMddHHmmss").format(date);
		return skuId;
	}

	/**
	 * 产生productId
	 *
	 * @param
	 * @return
	 */
	public static String createProductId() {
		Date date = new Date(System.currentTimeMillis());
		String productId = "PRO" + new SimpleDateFormat("yyMMddHHmmss").format(date);
		return productId;
	}

	/**
	 * 产生brandId
	 *
	 * @param
	 * @return
	 */
	public static String createBrandId() {
		Date date = new Date(System.currentTimeMillis());
		String brandId = "BRA" + new SimpleDateFormat("yyMMddHHmmss").format(date);
		return brandId;
	}

	/**
	 * 产生商品号
	 * 
	 * @param gcount
	 *            商品总数
	 * @return
	 */
	public static String createGoodsId(int gcount) {
		Date date = new Date(System.currentTimeMillis());
		int g = 1001 + gcount;// 1000/d
		String goodsId = "G" + new SimpleDateFormat("yyMMddHHmmss").format(date) + g;
		return goodsId;
	}

	/**
	 * 产生积分商品订单号
	 * 
	 * @return
	 */
	// 贷款订单号
	public static String createIntegralId() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
		String time = format.format(date);
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			buffer.append(random.nextInt(10));
		}
		return "J" + time + buffer;
	}

	/**
	 * 产生商品订单号
	 * 
	 * @param ocount
	 *            订单数量
	 * @return
	 */
	public static String createOrderId(int ocount) {
		Date date = new Date(System.currentTimeMillis());
		int p = 10001 + ocount;// 10000/d
		String orderId = "P" + new SimpleDateFormat("yyMMddHHmmss").format(date) + p;
		return orderId;
	}

	/**
	 * 产生渠道id
	 * 
	 * @param count
	 * @return
	 */
	public static String createCanalId(int count) {
		Date date = new Date(System.currentTimeMillis());
		int c = 1001 + count;
		String canalId = "C" + new SimpleDateFormat("yyMMddHHmmss").format(date) + c;
		return canalId;
	}

	/**
	 * 产生退款id
	 * 
	 * @param count
	 * @return
	 */
	public static String createRefundId(int count) {
		Date date = new Date(System.currentTimeMillis());
		int t = 1001 + count;
		String refundId = "T" + new SimpleDateFormat("yyMMddHHmmss").format(date) + t;
		return refundId;
	}

	/**
	 * 产快递发货id
	 * 
	 * @param count
	 * @return
	 */
	public static String createSendGoodsId(int count) {
		Date date = new Date(System.currentTimeMillis());
		int t = 1001 + count;
		String refundId = "S" + new SimpleDateFormat("yyMMddHHmmss").format(date) + t;
		return refundId;
	}

	/**
	 * 产生支付记录id
	 * 
	 * @param count
	 * @return
	 */
	public static String createPayforId(int count) {
		Date date = new Date(System.currentTimeMillis());
		int f = 1001 + count;
		String payforId = "F" + new SimpleDateFormat("yyMMddHHmmss").format(date) + f;
		return payforId;
	}

	/**
	 * 验证手机号
	 * 
	 * @param phoneNum
	 * @return
	 */
	public static boolean checkPhoneNum(String phoneNum) {
		String regex = "[1][3456789]\\d{9}";

		if (phoneNum == null || phoneNum.equals("") || phoneNum.length() != 11) {
			return false;
		} else {
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(phoneNum);
			return m.matches();
		}
	}

	/**
	 * 产生验证码
	 * 
	 * @return
	 */
	public static String createCode() {
		Random r = new Random();
		int a = r.nextInt(6000) + 1000;
		// System.out.println(a);
		return a + "";
	}

	/**
	 * 转换字符编码
	 * 
	 * @param str
	 * @return utf-8
	 */
	public static String getStrUTF_8(String str) {
		String s = "";
		try {
			s = new String(str.getBytes("gb2312"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * 获取年月日
	 * 
	 * @return 2018-06-15
	 */
	public static String getDayWithMark() {
		StringBuffer buffer = new StringBuffer(getDate());
		buffer.insert(4, "-");
		buffer.insert(7, "-");
		return buffer.toString();
	}

	/**
	 * 格式化输出时间 2018-04-19 18:52:11
	 * 
	 * @param date
	 *            20180419185211
	 * @return 2018-04-19 18:52:11
	 */
	public static String FormatDate(String date) {
		if (date.length() != 14) {
			return "";
		}
		StringBuilder buf = new StringBuilder();
		buf.append(date.substring(0, 4)).append("-").append(date.substring(4, 6)).append("-")
				.append(date.substring(6, 8)).append(" ").append(date.substring(8, 10)).append(":")
				.append(date.subSequence(10, 12)).append(":").append(date.subSequence(12, 14));
		return buf.toString();
	}

	/**
	 * 获取本月第一天
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getThisMonthFirstDay() {
		// 获取本月第一天
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		// 设置为第一天
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = sf.format(gcLast.getTime());
		return day_first;
	}

	/**
	 * 获取上月第一天
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getLastMonthFirstDay() {
		// 获取上月时间
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String gtimelast = sdf.format(c.getTime()); // 上月
		// System.out.println(gtimelast);
		int lastMonthMaxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		// System.out.println(lastMonthMaxDay);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), lastMonthMaxDay, 23, 59, 59);
		// 按格式输出
		String gtime = sdf.format(c.getTime()); // 上月最后一天
		// System.out.println(gtime);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-01  00:00:00");
		String gtime2 = sdf2.format(c.getTime()); // 上月第一天
		return gtime2;
	}

	/**
	 * 获取上月最后一天
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getLastMonthLastDay() {
		// 获取上月时间
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String gtimelast = sdf.format(c.getTime()); // 上月
		// System.out.println(gtimelast);
		int lastMonthMaxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		// System.out.println(lastMonthMaxDay);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), lastMonthMaxDay, 23, 59, 59);

		// 按格式输出
		String gtime = sdf.format(c.getTime()); // 上月最后一天
		// System.out.println(gtime);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-01  00:00:00");
		String gtime2 = sdf2.format(c.getTime()); // 上月第一天
		return gtime;
	}

	/**
	 * double 进行加法运算
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	// public static double DoubleSum(String a, String b) {//有误差
	// BigDecimal b1 = new BigDecimal(Double.parseDouble(a));
	// BigDecimal b2 = new BigDecimal(Double.parseDouble(b));
	// return b1.add(b2).doubleValue();
	// }
	public static double DoubleSum(String a, String b) {// 无误差
		BigDecimal c = BigDecimal.valueOf(Double.parseDouble(a));
		BigDecimal d = BigDecimal.valueOf(Double.parseDouble(b));
		return c.add(d).doubleValue();
	}

	/**
	 * double 进行减法运算
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double DoubleSub(String a, String b) {// 无误差
		BigDecimal c = BigDecimal.valueOf(Double.parseDouble(a));
		BigDecimal d = BigDecimal.valueOf(Double.parseDouble(b));
		return c.subtract(d).doubleValue();
	}

	/**
	 * double 乘法运算
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double DoubleMul(String a, String b) {
		BigDecimal b1 = BigDecimal.valueOf(Double.parseDouble(a));
		BigDecimal b2 = BigDecimal.valueOf(Double.parseDouble(b));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * double 除法运算
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double DoubleDiv(String a, String b) {
		BigDecimal b1 = BigDecimal.valueOf(Double.parseDouble(a));
		BigDecimal b2 = BigDecimal.valueOf(Double.parseDouble(b));
		return b1.divide(b2).doubleValue();
	}

	/**
	 * double保留两位小数(四舍五入)
	 * 
	 * @param a
	 * @return
	 */
	public static double DoubleFormat(double a) {
		BigDecimal b = new BigDecimal(a);
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	/**
	 * 获取IP
	 * 
	 * @return
	 */
	public static String getIP() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return addr.getHostAddress();
	}

	/**
	 * 删除文件
	 * 
	 * @param path
	 */
	public static void deleteFile(String path) {
		File f = new File(path);
		if (f.exists()) {
			f.delete();
		}
	}

	/**
	 * 删除带有路径的文件
	 * 
	 * @param path
	 */
	public static void deleteFileWithPath(String path) {
		if (path != null && path.contains("path=")) {
			String[] strs = path.split("path=");
			// System.out.println(strs[strs.length-1]);
			File f = new File(strs[strs.length - 1]);
			if (f.exists()) {
				f.delete();
			}
		}
	}

	/**
	 * 获取文件大小
	 * 
	 * @param path
	 * @return
	 */
	public static int getFileLength(String path) {
		File f = new File(path);
		if (f.exists()) {
			return (int) f.length();
		}
		return 0;
	}

	/**
	 * 字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 字符串是否为空 ,为空返回空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String isNull(String str) {
		if (str != null) {
			return str;
		}
		return "";
	}

	/**
	 * date转换为String
	 * 
	 * @see #
	 *      <li>{@link #DateToString}</li>
	 * @param date
	 * @return
	 */
	public static String DateToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		return time;
	}

	public static void main(String[] args) {
		// System.out.println(getDateFormat());
		// // System.out.println(getDate());
		// System.out.println(createMerId(1));
		// System.out.println(createGoodsId(1));
		// System.out.println(createOrderId(1));
		// System.out.println(createCanalId(1));
		// System.out.println(checkPhoneNum("15377038740"));
		// System.out.println(createCode());
		// System.out.println(getDate());
		// System.out.println(getTime());
		// Scanner s = new Scanner(System.in);
		// String a = s.next();
		// System.out.println(getStrUTF_8(a));
		// System.out.println(FormatDate("20180419185211"));
		// System.out.println(DoubleMul("68", " 0.25"));
		// System.out.println(DoubleSum("0.01", "0.09"));
		// System.out.println(DoubleSum("0.01", "0.06"));
		// System.out.println(getIP());
		// System.out.println(getFileLength("E:/McangPartner/APP/Android/McangPartner.rar"));
		// Date d = new Date("1524812207000");
		// System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
		// .format(d));
		// System.out.println(DoubleDiv("0.1", "0.01"));
		// System.out.println(DoubleSub("0.1", "0.01"));
		// System.out.println(DoubleFormat(0.12456));
		// System.out.println(DoubleMul("3", "100"));
		// System.out.println(Integer.parseInt("3") * Integer.parseInt("100"));
		System.out.println(getLastMonthLastDay());
	}
}

 
package com.example.demo.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jianqiang.jiang 2006-12-7
 */
public class ParamUtils {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ParamUtils.class);

	public ParamUtils() {
	}

	public static String getParameter(HttpServletRequest request, String paramName) {
		return getParameter(request, paramName, false);
	}

	public static String getParameter(HttpServletRequest request, String paramName, boolean emptyStringsOK) {
		String temp = request.getParameter(paramName);
		if (temp != null) {
			if (temp.equals("") && !emptyStringsOK)
				return null;
			else
				return temp;
		} else {
			return null;
		}
	}

	public static String getParameter(HttpServletRequest request, String paramName, String defaultStr) {
		String temp = request.getParameter(paramName);
		if (temp != null) {
			if (temp.equals(""))
				return defaultStr;
			else
				return temp;
		} else {
			return defaultStr;
		}
	}

	public static int getIntParameter(HttpServletRequest request, String paramName, int defaultNum) {
		String temp = request.getParameter(paramName);
		if (null != temp && !"".equals(temp)) {
			int num = defaultNum;
			try {
				num = Integer.parseInt(temp);
			} catch (Exception exception) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	public static Integer getIntegerParameter(HttpServletRequest request, String paramName, Integer defaultNum) {
		String temp = request.getParameter(paramName);
		if (!"".equals(temp)) {
			try {
				return Integer.valueOf(temp);
			} catch (NumberFormatException e) {
				// e.printStackTrace();
			}
			return defaultNum;
		} else {
			return defaultNum;
		}
	}

	public static double getDoubleParameter(HttpServletRequest request, String paramName, double defaultNum) {
		String temp = request.getParameter(paramName);
		if (temp != null && !temp.equals("")) {
			double num = defaultNum;
			try {
				num = Double.parseDouble(temp);
			} catch (Exception ignored) {
				double d = defaultNum;
				return d;
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	public static boolean getBooleanParameter(HttpServletRequest request, String paramName, boolean flag) {
		String temp = request.getParameter(paramName);
		if (temp == null) {
			return flag;
		} else {
			return temp.equals("true");
		}
	}

	public static boolean getCheckboxParameter(HttpServletRequest request, String paramName) {
		String temp = request.getParameter(paramName);
		return temp != null && temp.equals("on");
	}

	public static String getAttribute(HttpServletRequest request, String attribName) {
		return getAttribute(request, attribName, false);
	}

	public static String getAttribute(HttpServletRequest request, String attribName, boolean emptyStringsOK) {
		String temp = (String) request.getAttribute(attribName);
		if (temp != null) {
			if (temp.equals("") && !emptyStringsOK)
				return null;
			else
				return temp;
		} else {
			return null;
		}
	}

	public static boolean getBooleanAttribute(HttpServletRequest request, String attribName) {
		String temp = (String) request.getAttribute(attribName);
		return temp != null && temp.equals("true");
	}

	public static int getIntAttribute(HttpServletRequest request, String attribName, int defaultNum) {
		String temp = (String) request.getAttribute(attribName);
		if (temp != null && !temp.equals("")) {
			int num = defaultNum;
			try {
				num = Integer.parseInt(temp);
			} catch (Exception exception) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	public static String Uni2GB(String original) {
		if (original != null) {
			try {
				String s = new String(original.getBytes("ISO8859_1"), "GBK");
				return s;
			} catch (Exception e) {
			}
			String s1 = null;
			return s1;
		} else {
			return null;
		}
	}

	public static String GB2Uni(String original) {
		if (original != null) {
			try {
				String s = new String(original.getBytes("GBK"), "ISO8859_1");
				return s;
			} catch (Exception e) {
			}
			String s1 = null;
			return s1;
		} else {
			return null;
		}
	}
}
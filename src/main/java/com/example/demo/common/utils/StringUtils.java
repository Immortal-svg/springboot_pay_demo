/*
 * Copyright 2004 Linktone.All rights reserved.
 */
package com.example.demo.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.MessageFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);

    /**
     * 比较两个用","分割的字符串，之间的内容是否一样
     *
     * @param firstStr
     * @param secondStr
     * @return
     */
    public static boolean compareStrSplitByComma(String firstStr, String secondStr) {
        if (firstStr == null || secondStr == null) {
            return false;
        }
        String[] firstArray = firstStr.split(",");
        String[] secondArray = secondStr.split(",");
        Map secondStrMap = new HashMap();
        if (firstArray.length != secondArray.length) {
            return false;
        }
        for (int i = 0; i < secondArray.length; i++) {
            secondStrMap.put(secondArray[i], secondArray[i]);
        }
        for (int i = 0; i < firstArray.length; i++) {
            if (!secondStrMap.containsKey(firstArray[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将指定double类型转换为金钱格式字符
     *
     * @param doubleValue
     * @return String
     */
    public static String doubleToCurrency(double doubleValue) {
        Object[] args = {new Double(doubleValue)};
        return MessageFormat.format("{0,number,￥,#,###,###,###,###,###,##0.00}", args);
    }

    /**
     * 将字符串对象按srcEncoding编码转换为destEncoding编码格
     *
     * @param stringValue
     * @param srcEncoding
     * @param destEncoding
     * @return String
     */
    public static String encodeString(String stringValue, String srcEncoding, String destEncoding) {
        // 如果参数为null，返回null
        if (stringValue == null || srcEncoding == null || destEncoding == null) {
            return null;
        }
        String value = null;
        try {
            value = new String(stringValue.getBytes(srcEncoding), destEncoding);
        } catch (UnsupportedEncodingException ex) {
            value = stringValue;
        }
        return value;
    }

    /**
     * 判断是否指定字符串为空字符串(null或者长度为0
     *
     * @param stringValue
     * @return boolean
     */
    public static boolean isEmpty(String stringValue) {
        if (stringValue == null || stringValue.trim().length() < 1 || stringValue.trim().equalsIgnoreCase("null")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否指定字符串为空字符串(null或者长度为0
     *
     * @param stringValue
     * @return boolean
     */
    public static boolean isNotEmpty(String stringValue) {
        if (stringValue == null || stringValue.trim().length() < 1 || stringValue.trim().equalsIgnoreCase("null")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断字符是否数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch < '0' || ch > '9') {
                return false;

            }
        }
        return true;
    }

    public static boolean isLong(String str) {
        try {
            new Long(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 提取指定字符串中的所有的数字字符
     *
     * @param stringValue
     * @return String[]
     */
    public static String[] getNumStringArray(String stringValue) {
        if (stringValue == null) {
            return null;
        }
        ArrayList list = new ArrayList();
        Pattern p = Pattern.compile("([0-9])+");
        Matcher m = p.matcher(stringValue);
        while (m.find()) {
            list.add(m.group());
        }
        return (String[]) list.toArray(new String[0]);
    }

    /**
     * 将十进制数字字符串转换为整型数字，如果转换失败返回-
     *
     * @param stringValue
     * @return int
     */
    public static int stringToInt(String stringValue) {
        return stringToInt(stringValue, -1);
    }

    /**
     * 将十进制数字字符串转换为整型数字，如果转换失败返回默认
     *
     * @param stringValue
     * @param defaultValue
     * @return int
     */
    public static int stringToInt(String stringValue, int defaultValue) {
        int intValue = defaultValue;
        if (stringValue != null) {
            try {
                intValue = Integer.parseInt(stringValue);
            } catch (NumberFormatException ex) {
                intValue = defaultValue;
            }
        }
        return intValue;

    }

    /**
     * 将指定字符串对象默认编码IOS8859_1编码转为GBK编码的字符串对
     *
     * @param stringValue
     * @return String
     */
    public static String toGBKString(String stringValue) {
        return encodeString(stringValue, "ISO8859_1", "GBK");

    }

    public static String Date2String(Date date) {
        if (date == null)
            return null;
        return DateUtil.FORMAT_DATE_TIME.format(date);
    }

    public static String Date2StringDate(Date date) {
        if (date == null)
            return null;
        return DateUtil.FORMAT_DATE.format(date);
    }

    public static Date String2Date(String str) {
        return DateUtil.str2Date(str, DateUtil.FORMAT_DATE_TIME);
    }

    public static String Integer2String(Integer i) {
        if (i == null)
            return null;
        return String.valueOf(i);
    }

    public static Integer String2Integer(String s) {
        if (s == null) {
            return null;
        }
        Integer ret = null;
        try {
            ret = Integer.valueOf(Integer.parseInt(s.trim()));
        } catch (Exception localException) {
        }
        return ret;
    }

    public static String Long2String(Long l) {
        if (l == null)
            return null;
        return String.valueOf(l);
    }

    public static Long String2Long(String s) {
        if (s == null) {
            return null;
        }
        Long ret = null;
        try {
            ret = Long.valueOf(Long.parseLong(s.trim()));
        } catch (Exception localException) {
        }
        return ret;
    }

    public static int str2int(String str) {
        return (int) str2long(str, -1);
    }

    public static int str2int(String str, int defaultValue) {
        return (int) str2long(str, defaultValue);
    }

    public static long str2long(String str) {
        return str2long(str, -1);
    }

    public static long str2long(String str, int defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        long ret = defaultValue;
        try {
            ret = Long.parseLong(str.trim());
        } catch (Exception localException) {
        }
        return ret;
    }

    public static String str2utf8(String string) {
        try {
            return URLDecoder.decode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    public static boolean isNull(String str) {
        return str == null;
    }

    public static boolean isURLValid(String url) {
        boolean ret = false;
        try {
            URL fileURL = new URL(url);
            ret = true;
        } catch (MalformedURLException localMalformedURLException) {
        }
        return ret;
    }

    public static int random(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    public static String getRandom(int length) {
        String ret = "";
        while (ret.length() != length) {
            String s = String.valueOf(random(10));
            if (("".equals(ret)) && ("0".equals(s)))
                continue;
            ret = ret + s;
        }
        return ret;
    }

    public static String getActivationCode() {
        return getRandom(9);
    }

    public static int getAreaLevel(String areaCode) {
        if ((!areaCode.substring(0, 4).equals("0000")) && (areaCode.substring(4, 6) != "00")
                && (!areaCode.substring(4, 6).equals("00"))) {
            return 3;
        }

        if ((!areaCode.substring(0, 2).equals("00")) && (!areaCode.substring(2, 4).equals("00"))
                && (areaCode.substring(4, 6).equals("00"))) {
            return 2;
        }

        if ((!areaCode.substring(0, 2).equals("00")) && (areaCode.substring(2, 6).equals("0000"))) {
            return 1;
        }
        return 0;
    }

    public static Date getNyear(Date d, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int td = c.get(1);
        c.set(1, td + n);
        Date towDay = c.getTime();
        return towDay;
    }

    public static Date getDay(Date d, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int td = c.get(5);
        c.set(5, td + n);
        Date towDay = c.getTime();
        return towDay;
    }

    public static String getFormContent(InputStream requestForm) {
        String buff = new String();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] ba = new byte[1024];
            int read = 0;
            while ((read = requestForm.read(ba)) != -1) {
                baos.write(ba, 0, read);
            }
            if (baos != null) {
                buff = new String(baos.toByteArray());
            }
            baos.close();
        } catch (IOException localIOException) {
        }
        return buff;
    }

    /**
     * 验证邮箱
     */
    public static boolean validateUserEmail(String email) {
        String validateStr = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        if (email == null) {
            return false;
        }
        email = email.trim();
        boolean rs = false;
        rs = matcher(validateStr, email);
        return rs;
    }

    /**
     * 验证用户名，支持中英文（包括全角字符）、数字、下划线和减号 （全角及汉字算两位）,长度为4-20位,中文按二位计数
     */
    public static boolean validateUserName(String userName) {
        String validateStr = "^[\\w\\-－＿[0-9]\u4e00-\u9fa5\uFF21-\uFF3A\uFF41-\uFF5A]+$";
        if (userName == null) {
            return false;
        }
        userName = userName.trim();
        boolean rs = true;
        rs = matcher(validateStr, userName);
        if (rs) {
            int strLenth = getStrLength(userName);
            if (strLenth < 3 || strLenth > 20) {
                rs = false;
            }
        }
        return rs;
    }

    /**
     * 验证用户密码，支持6-16位英文，数字，字符（空格除外）
     */
    public static boolean validatePassword(String password) {
        // String validateStr = "^[a-z0-9A-Z]{6,16}$";
        String validateStr = "^[a-z0-9A-Z!@#$%^&*()]{6,16}$";
        if (password == null) {
            return false;
        }
        password = password.trim();
        boolean rs = false;
        rs = matcher(validateStr, password);
        if (rs) {
            int strLenth = getStrLength(password);
            if (strLenth < 6 || strLenth > 16) {
                rs = false;
            }
        }
        return rs;
    }

    /**
     * 验证用户手机号码，11位
     */
    public static boolean validatePhone(String phone) {
        String validateStr = "^[1][3,4,5,7,8][0-9]{9}$";
        if (phone == null) {
            return false;
        }
        phone = phone.trim();
        boolean rs = false;
        rs = matcher(validateStr, phone);
        return rs;
    }

    /**
     * String regex = "[1-9][0-9]{4,14}"; 验证用户短信验证码
     */
    public static boolean validateMessageCode(String code) {
        String validateStr = "^[0-9]{4}$";
        if (code == null) {
            return false;
        }
        code = code.trim();
        boolean rs = false;
        rs = matcher(validateStr, code);
        return rs;
    }

    /**
     * 验证用户qq格式
     */
    public static boolean validateUserQQ(String qq) {
        String validateStr = "[1-9][0-9]{4,14}";
        if (qq == null) {
            return false;
        }
        qq = qq.trim();
        boolean rs = false;
        rs = matcher(validateStr, qq);
        return rs;
    }

    /**
     * 获取字符串的长度，对双字符（包括汉字）按两位计数
     *
     * @param value
     * @return
     */
    public static int getStrLength(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }

    private static boolean matcher(String reg, String string) {
        boolean tem = false;
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(string);
        tem = matcher.matches();
        return tem;
    }

    /***
     * 获取 request 中 json 字符串的内容
     *
     * @param request
     * @return : <code>byte[]</code>
     * @throws IOException
     */
    public static String getRequestJsonString(HttpServletRequest request) throws IOException {
        String submitMehtod = request.getMethod();
        // GET
        if (submitMehtod.equals("GET")) {
            return new String(request.getQueryString().getBytes("iso-8859-1"), "utf-8").replaceAll("%22", "\"");
            // POST
        } else {
            return getRequestPostStr(request);
        }
    }

    /**
     * 描述:获取 post 请求的 byte[] 数组
     * <p>
     * <pre>
     * 举例：
     * </pre>
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request) throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {

            int readlen = request.getInputStream().read(buffer, i, contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

    /**
     * 描述:获取 post 请求内容
     * <p>
     * <pre>
     * 举例：
     * </pre>
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestPostStr(HttpServletRequest request) throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }

    /**
     * 生产用户的token
     *
     * @return
     */
    public static String creatUUID() {
        return UUID.randomUUID().toString();
    }

    public static String creatMessageCode() {
        Random r = new Random();
        int tag[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        String four = "";
        int temp = 0;
        while (four.length() != 4) {
            temp = r.nextInt(10);// 随机获取0~9的数字
            if (tag[temp] == 0) {
                four += temp;
                tag[temp] = 1;
            }
        }
        return four;
    }

    public static String getStringByType(String type) {
        if ("1".equals(type)) {
            return "pubwin";
        } else if ("2".equals(type)) {
            return "信佑、迅闪";
        } else if ("3".equals(type)) {
            return "网维大师";
        } else if ("4".equals(type)) {
            return "易乐游";
        } else if ("5".equals(type)) {
            return "I8";
        } else if ("6".equals(type)) {
            return "嘟嘟牛";
        } else if ("7".equals(type)) {
            return "网星一卡通";
        } else if ("8".equals(type)) {
            return "旺旺吧";
        } else if ("9".equals(type)) {
            return "净网先锋";
        } else if ("10".equals(type)) {
            return "矮哨兵";
        } else if ("11".equals(type)) {
            return "易游";
        } else if ("12".equals(type)) {
            return "任子行";
        } else if ("13".equals(type)) {
            return "云更新";
        } else if ("14".equals(type)) {
            return "网吧营销大师";
        } else if ("15".equals(type)) {
            return "5636";
        } else if ("16".equals(type)) {
            return "恒信一卡通";
        } else if ("17".equals(type)) {
            return "方格子";
        } else if ("18".equals(type)) {
            return "MZD娱乐平台";
        } else if ("20".equals(type)) {
            return "领航";
        } else if ("22".equals(type)) {
            return "万象";
        } else if ("23".equals(type)) {
            return "锤子大爷";
        } else if ("24".equals(type)) {
            return "比目鱼";
        } else if ("25".equals(type)) {
            return "龙管家";
        } else if ("26".equals(type)) {
            return "佳星计费";
        } else if ("27".equals(type)) {
            return "重庆智多(ikeeper)";
        } else if ("28".equals(type)) {
            return "8圈";
        }
        return "未知";
    }

    public static Map str2Map(String string) {
        Map<String, Object> hashMap = new HashMap<>();
        List<String> stringList = Arrays.asList(string.replace("{", "").replace("}", "").split(","));
        stringList.stream().forEach(line -> {
            String[] value = line.split("=");
            if (value.length == 2) {
                hashMap.put(value[0].replace(" ", ""), value[1].replace(" ", ""));
            } else {
                logger.info("value:{}",value);
                hashMap.put(value[0].replace(" ", ""), "");
            }

        });
        return hashMap;
    }

    public static String getKindByType(String type) {
        if ("1".equals(type) || "6".equals(type) || "7".equals(type) || "8".equals(type) || "16".equals(type)
                || "22".equals(type) || "25".equals(type) || "26".equals(type) || "27".equals(type) || "28".equals(type)) {
            return "charging";
        } else if ("2".equals(type) || "3".equals(type) || "4".equals(type) || "5".equals(type) || "11".equals(type)
                || "13".equals(type) || "17".equals(type) || "18".equals(type)) {
            return "icafe";
        } else if ("14".equals(type) || "20".equals(type) || "23".equals(type)) {
            return "marketing";
        } else if ("15".equals(type) || "24".equals(type)) {
            return "flow";
        } else if ("9".equals(type) || "10".equals(type) || "12".equals(type)) {
            return "audit";
        }
        return "";
    }


//    public static void main(String[] args) {
//    	float tempPercent= (100f / 101f) * 100;
//    	int round = Math.round(tempPercent);
//    	System.out.println(tempPercent);
//    	System.out.println(round);
//    }
    public static String getArea(String area) {
    	if("上海".equals(area)||"北京".equals(area)||"天津".equals(area)||"重庆".equals(area)) {
    		return area+"市";
    	}else if("广西".equals(area)) {
    		return area;
    	}else if("内蒙古".equals(area)) {
    		return area;
    	}else if("宁夏".equals(area)) {
    		return area;
    	}else if("西藏".equals(area)) {
    		return area;
    	}else if("新疆".equals(area)) {
    		return area;
    	}else if("澳门".equals(area)) {
    		return area;
    	}else if("香港".equals(area)) {
    		return area;
    	}else{
    		return area+"省";
    	}

    }

    public static String logStringFormat(String str)
    {
        return str == null ? "" : str.replace("|", "%7c").replaceAll("\\t|\\r|\\n", "");
    }

}
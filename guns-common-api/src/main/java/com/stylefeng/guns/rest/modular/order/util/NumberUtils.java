package com.stylefeng.guns.rest.modular.order.util;

/**
 * Created by IceFloe_Rot
 * Date 2019/7/21 Time 10:33
 */

/**
 * 用于阿拉伯数字和中文数字的转换
 */
public class NumberUtils {
    private NumberUtils() {}
    private static NumberUtils instance;
    public static NumberUtils getInstance() {
        if (null == instance) {
            instance = new NumberUtils();
        }
        return instance;
    }
    static String[] units = { "", "十", "百", "千", "万", "十万", "百万", "千万", "亿", "十亿", "百亿", "千亿", "万亿" };
    static char[] numArray = { '零', '一', '二', '三', '四', '五', '六', '七', '八', '九' };

    /**
     * 将整型数字转换为中文
     * @param num   整型数字
     * @return
     */
    public String formatInteger(int num) {
        char[] val = String.valueOf(num).toCharArray();
        int len = val.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            String m = val[i] + "";
            int n = Integer.valueOf(m);
            boolean isZero = n == 0;
            String unit = units[(len - 1) - i];
            if (isZero) {
                if ('0' == val[i - 1]) {
                    continue;
                } else {
                    sb.append(numArray[n]);
                }
            } else {
                sb.append(numArray[n]);
                sb.append(unit);
            }
        }
        return sb.toString();
    }

    /**
     * 将数字转换为中文
     * @param decimal   带小数位的数字
     * @return
     */
    public String formatDecimal(double decimal) {
        String decimals = String.valueOf(decimal);
        int decIndex = decimals.indexOf(".");
        int integ = Integer.valueOf(decimals.substring(0, decIndex));
        int dec = Integer.valueOf(decimals.substring(decIndex + 1));
        String result = formatInteger(integ) + "." + formatFractionalPart(dec);
        return result;
    }

    /**
     * 将小数位数字转换为中文
     * @param decimal   小数位数字
     * @return
     */
    private String formatFractionalPart(int decimal) {
        char[] val = String.valueOf(decimal).toCharArray();
        int len = val.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int n = Integer.valueOf(val[i] + "");
            sb.append(numArray[n]);
        }
        return sb.toString();
    }
}

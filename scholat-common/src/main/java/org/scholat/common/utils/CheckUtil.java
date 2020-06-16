package org.scholat.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 用于校验或者检查
 */
public class CheckUtil {

    /**
     * 判断一个字符串是否是数字。
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (str == null) return false;
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        return pattern.matcher(str).matches();
    }

    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        String s2="^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$";// 验证手机号
        if(StringUtils.isNotBlank(str)){
            p = Pattern.compile(s2);
            m = p.matcher(str);
            b = m.matches();
        }
        return b;
    }

}

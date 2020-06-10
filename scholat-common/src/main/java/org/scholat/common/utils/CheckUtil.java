package org.scholat.common.utils;

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



}

package studio.geek.util;

import studio.geek.shixiseng.entity.Academic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Liuqi
 * Date: 2017/4/8.
 */
public class RegexUtil {
    /**
     * 正则匹配
     *
     * @param str   输入的内容
     * @param regex 要匹配的正则表达式
     * @return 第一个匹配成功结果
     */
    public static String getStringByRegex(String str, String regex) throws Exception {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        //System.out.println(matcher.groupCount());
        if (matcher.find()) {
            return matcher.group();
        } else {
            throw new Exception("正则匹配错误");
        }
    }

    public static Academic parserEducation(String str) throws Exception {
        String regex = "(>)[^\\s]{2,5}(\\b)";
        String res = getStringByRegex(str, regex).substring(1);
        if (res.equals("本科")) {
            return Academic.bachelor;
        } else if (res.equals("硕士")) {
            return Academic.master;
        } else if (res.equals("博士")) {
            return Academic.doctor;
        } else if (res.equals("不限")) {
            return Academic.any;
        } else if (res.equals("大专")) {
            return Academic.college;
        }
        return Academic.any;
    }

    public static int getMonths(String str) throws Exception {
        String regex = "(实习)[\\d](个月)";
        String result = getStringByRegex(str, regex);
        int length = result.length();
        String s = result.substring(2, length - 2);
        return Integer.parseInt(result.substring(2, length - 2));
    }
}

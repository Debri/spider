package studio.geek.Util;

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
    public static String getStringByRegex(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.groupCount());

        return matcher.group(0);
    }
}

package studio.geek.Util;

import org.junit.Test;

/**
 * Created by Liuqi
 * Date: 2017/4/8.
 */
public class RegexUtilTest {
    @Test
    public void testGetStringByRegex() throws Exception {
       String str= RegexUtil.getStringByRegex("<i class=\"money\">&#xe61b;</i>200-300/天", "[\\d]+(-)[\\d]+");
        System.out.println(str);
    }
}
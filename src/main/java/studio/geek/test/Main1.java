package studio.geek.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Liuqi
 * Date: 2017/4/6.
 */

/**
 * 抓取百度的logo
 */
public class Main1 {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("href=(.+?)");
        String url = "http://www.baidu.com";
        String result = getContent(url);
        // Matcher matcher = pattern.matcher("＜a href=\"index.html\"＞我的主页＜/a＞");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            System.out.println(matcher.group(1) + "-------------------");
        }
    }

    public static String getContent(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        System.out.println(result);
        return result;
    }
}

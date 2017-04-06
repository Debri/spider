package studio.geek.test.jsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Liuqi
 * Date: 2017/4/6.
 * 抓取百度首页信息
 */
public class Test {
    public static void main(String[] args) throws IOException {

        Connection conn = Jsoup.connect("http://www.baidu.com");
        Document doc = conn.post();
        //System.out.println(doc.body());
        Elements pngs = doc.select("img[src$=.gif]");//获取后缀名为gif的文件
        printEles(pngs);
        Elements elements = doc.getElementsByTag("img");
        printEles(elements);
    }

    public static void printEles(Elements elements) {
        for (Element ele : elements) {
            System.out.println(ele);
        }
    }
}

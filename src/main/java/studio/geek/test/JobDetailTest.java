package studio.geek.test;

import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by Liuqi
 * Date: 2017/4/8.
 */
public class JobDetailTest {
    public static void main(String[] args) throws IOException {
       /* Document doc = Jsoup.parse("http://www.shixiseng.com/intern/inn_otolrgm7bovh");
        System.out.println(doc);
        // System.out.println(doc.getElementById("container"));
        System.out.println(doc.body());
        System.out.println(doc.getElementsByClass("job_det_left").get(0));*/
        Document doc = HttpConnection.connect("http://www.shixiseng.com/intern/inn_otolrgm7bovh").get();
        //System.out.println(doc.getElementById("container"));

        System.out.println(doc.getElementsByClass("jb_det_right_top"));
        //System.out.println(doc);
    }

    public static void doTest() {

    }
}

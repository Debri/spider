package studio.geek.shixiseng.task;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import studio.geek.shixiseng.entity.Job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liuqi
 * Date: 2017/4/7.
 */

/**
 * 多线程
 */
public class JobTask  implements Runnable{
    static List<Job> list = new ArrayList<Job>();

    public static void doSpider(String keyWord) {
        int pageNum = 1;
        String url = "http://www.shixiseng.com/interns?k=" + keyWord + "&p=" + pageNum;
        Connection conn = Jsoup.connect(url);
        Document doc = null;
        try {
            doc = conn.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(doc.body());
        Elements elements = doc.getElementsByClass("job_inf_inf");
        print(elements);

    }

    public static void print(Elements eles) {
        for (Element element : eles) {
            //System.out.println(element);
            //Job job = new Job();
            //System.out.println(element);
            // System.out.println(element.getElementsByClass("job_head"));
            Elements jobHead = element.getElementsByClass("job_head");
            for (Element job : jobHead) {
                Element e = job.getElementsByTag("a").get(0);
                System.out.println(e);
                Element element1 = e.getElementsMatchingText("href").get(0);
                System.out.println("----------"+element1);
            }

          /*  Elements elements = element.getAllElements();
            System.out.println(elements);
            System.out.println();
            System.out.println();*/
            // element.getElementsByClass()
        }
    }

    public void run() {

    }
}

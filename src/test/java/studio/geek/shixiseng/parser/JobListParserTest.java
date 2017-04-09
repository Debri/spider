package studio.geek.shixiseng.parser;

import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.junit.Test;
import studio.geek.shixiseng.entity.Job;
import studio.geek.shixiseng.entity.Page;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Liuqi
 * Date: 2017/4/9.
 */
public class JobListParserTest {
    @Test
    public void testParserJobList() throws Exception {
        JobListParser jobListParser = JobListParser.getInstance();
        Document doc = HttpConnection.connect("http://www.shixiseng.com/interns?k=java&p=1").get();
        Page page = new Page();
        List<Job> list = new LinkedList<Job>();
        page.setHtml(doc.html());
        page.setStatusCode(200);
        page.setUrl(doc.baseUri());
        list = jobListParser.parserJobListForDetail(page);
        //list = jobListParser.parserJobList(page);
        for (Job job : list) {
            System.out.println(job);
        }
    }
}
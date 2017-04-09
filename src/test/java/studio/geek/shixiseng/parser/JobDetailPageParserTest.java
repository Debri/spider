package studio.geek.shixiseng.parser;

import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.junit.Test;
import studio.geek.shixiseng.entity.Job;
import studio.geek.shixiseng.entity.Page;

/**
 * Created by Liuqi
 * Date: 2017/4/9.
 */
public class JobDetailPageParserTest {
    @Test
    public void testParserJobDetail() throws Exception {
        JobDetailPageParser jobDetailPageParser = JobDetailPageParser.getInstance();
        Document doc = HttpConnection.connect("http://www.shixiseng.com/intern/inn_otolrgm7bovh").get();
        Page page = new Page();
        page.setUrl(doc.baseUri());
        page.setHtml(doc.toString());
        System.out.println(page.toString());
        Job job = jobDetailPageParser.parserJobDetail(page);
        System.out.println(job);

    }

}
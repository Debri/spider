package studio.geek.shixiseng.parser;

/**
 * Created by Liuqi
 * Date: 2017/4/8.
 */

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import studio.geek.Util.SimpleLogger;
import studio.geek.shixiseng.entity.Company;
import studio.geek.shixiseng.entity.Job;
import studio.geek.shixiseng.entity.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析职位列表页信息
 */
public class JobListParser {
    Logger logger = SimpleLogger.getSimpleLogger(JobListParser.class);
    private static volatile JobListParser jobListParser;

    public static JobListParser getInatance() {
        if (jobListParser == null) {
            synchronized (jobListParser) {
                if (jobListParser == null) {
                    jobListParser = new JobListParser();
                }
            }
        }
        return jobListParser;
    }

    private JobListParser() {
    }

    /**
     * 解析列表页成一个job 的list
     *
     * @param page
     * @return
     */
    public static List<Job> parserJobList(Page page) {
        List<Job> jobList = new ArrayList<Job>();
        if (page.getStatusCode() != 200) {
            System.out.println("-----访问页面出错" + page.getStatusCode());

        }
        Document doc = Jsoup.parse(page.getHtml());
        Elements jobs = doc.getElementsByClass("job_inf_inf");
        for (Element jobElement : jobs) {
            jobList.add(doParser(jobElement));
        }

        return null;
    }

    /**
     * 解析job列表页并且持久化到数据库
     *
     * @param page
     * @return
     */
    public static List<Job> parserJobListAndPresistence(Page page) {
        return null;
    }

    /**
     * 解析一条job element
     *
     * @param jobElement
     * @return
     */
    private static Job doParser(Element jobElement) {
        Job job = new Job();
        Company company = new Company();
        Elements elements = jobElement.getElementsByTag("a");

        // Element jobHead = jobElement.getElementsByClass("job_head").get(0);
        Element jobHead = elements.get(0);
        job.setIdentity(jobHead.attr("href"));
        job.setJobName(jobHead.attr("title"));
        Element companyEle = elements.get(1);
        company.setName(companyEle.attr("title"));
        company.setUrl(companyEle.attr("href"));
        job.setCompany(company);
        Elements detailElements = jobElement.getElementsByTag("span");
        job.setCity(detailElements.get(1).attr("title"));
        int lowSalary = 0;
        int highSalary = 0;

        //job.set


        return job;
    }



}

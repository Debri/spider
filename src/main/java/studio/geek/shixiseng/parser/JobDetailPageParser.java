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
import studio.geek.util.RegexUtil;
import studio.geek.util.SimpleLogger;
import studio.geek.shixiseng.entity.Company;
import studio.geek.shixiseng.entity.Job;
import studio.geek.shixiseng.entity.Page;

/**
 * 职位详情页解析
 */
public class JobDetailPageParser {
    Logger logger = SimpleLogger.getSimpleLogger(JobDetailPageParser.class);
    private static volatile JobDetailPageParser jobDetailPageParser;

    /**
     * 线程安全的单例
     *
     * @return
     */
    public static JobDetailPageParser getInstance() {
        if (jobDetailPageParser == null) {
            synchronized (JobDetailPageParser.class) {
                if (jobDetailPageParser == null) {
                    jobDetailPageParser = new JobDetailPageParser();
                }
            }
        }
        return jobDetailPageParser;
    }

    private JobDetailPageParser() {
    }

    /**
     * 解析职位详情页
     *
     * @param doc 详情页的Document
     * @return
     * @throws Exception
     */
    public Job parserJobDetail(Document doc) throws Exception {
        Job job = new Job();
        Company company = new Company();
        System.out.println(doc);
        Element jobDetLeft = doc.getElementsByClass("jb_det_left").get(0);//页面左侧职位信息

        Element jobDetRightTop = doc.getElementsByClass("jb_det_right_top").get(0);//页面右侧公司信息
        job.setIdentity(jobDetLeft.getElementsByClass("taruuid").attr("value"));
        job.setJobName(jobDetLeft.getElementsByClass("job_name").attr("title"));
        job.setCity(jobDetLeft.getElementsByClass("city").attr("title"));
        job.setAcademic(RegexUtil.parserEducation(jobDetLeft.getElementsByClass("education").get(0).toString()));
        String[] salary = RegexUtil.getStringByRegex(jobDetLeft.getElementsByClass("daymoney").get(0).toString(), "[\\d]+(-)[\\d]+").split("-");
        if (salary.length == 2) {
            job.setLowSalary(Integer.parseInt(salary[0]));
            job.setHighSalary(Integer.parseInt(salary[1]));
        } else if (salary.length == 1) {
            job.setLowSalary(Integer.parseInt(salary[0]));
            job.setHighSalary(Integer.parseInt(salary[0]));
        }
        job.setTime(RegexUtil.getMonths(jobDetLeft.getElementsByClass("month").toString()));

        Elements aEle = jobDetRightTop.getElementsByTag("a");
        company.setUrl(aEle.get(0).attr("href"));
        company.setName(getCompanyName(aEle.get(1).toString()));
        Elements webHref = jobDetRightTop.getElementsByClass("web_href");

        company.setCompanyType(getCompanyType(webHref.get(0).toString()));
        company.setScale(getCompanyScale(webHref.get(1).toString()));
        job.setCompany(company);
        return job;
    }

    /**
     * 解析详情页
     *
     * @param page 详情页的包装类
     * @return
     * @throws Exception
     */
    public Job parserJobDetail(Page page) throws Exception {
        Document doc = Jsoup.parse(page.getHtml());
        return parserJobDetail(doc);
    }

    /**
     * 解析并且持久化到数据库
     *
     * @param page
     */
    public static void parserJobDetailAndPresistence(Page page) {

    }

    /**
     * 通过类似<a href="/company/detail/com_svm4hjs33cmx">人人网</a>获取公司名
     *
     * @param str
     * @return companyName
     * @throws Exception
     */
    public static String getCompanyName(String str) throws Exception {
        //正则表达式
        String regex = "(\">)[^\\d]+(<)";
        String result = RegexUtil.getStringByRegex(str, regex);
        String s = result.substring(2, result.length() - 1);
        return s;
    }

    /**
     * 公司类型   互联网
     *
     * @param str 带有标签字符串
     * @return
     * @throws Exception
     */
    public static String getCompanyType(String str) throws Exception {
        String regex = "(\"\">\\s)[^\\t\\r\\n]+(</span)";
        String result = RegexUtil.getStringByRegex(str, regex);//异常
        String s = result.substring(3, result.length() - 6);
        return s;
    }

    /**
     * 获取公司规模
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static String getCompanyScale(String str) throws Exception {
        String regex = "(\"\">\\s)[^\\n]+(</span)";
        String result = RegexUtil.getStringByRegex(str, regex);
        String s = result.substring(4, result.length() - 7);
        return s;
    }
}

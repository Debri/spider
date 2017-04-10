package studio.geek.shixiseng.task;

import org.apache.log4j.Logger;
import studio.geek.shixiseng.entity.Job;
import studio.geek.shixiseng.entity.Page;
import studio.geek.shixiseng.parser.JobDetailPageParser;
import studio.geek.util.MyInvocationHandler;
import studio.geek.util.SimpleLogger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import static studio.geek.shixiseng.ShixisengHttpClient.parseJobCount;

/**
 * Created by Liuqi
 * Date: 2017/4/9.
 */

/**
 * 职位详情页
 */
public class JobDetailPageTask extends AbstractWebPageTask {
    private static Logger logger = SimpleLogger.getSimpleLogger(JobDetailPageTask.class);
    private static JobDetailPageParser proxyJobDetailPageParser = null;

    static {
        /**
         * 创建代理
         */
        JobDetailPageParser detailPageParser = JobDetailPageParser.getInstance();
        InvocationHandler invocationHandler = new MyInvocationHandler(detailPageParser);
        proxyJobDetailPageParser = (JobDetailPageParser) Proxy.newProxyInstance(detailPageParser.getClass().getClassLoader(),
                detailPageParser.getClass().getInterfaces(), invocationHandler);

    }

    public JobDetailPageTask(String url) {
        super(url);
    }

    void retry() {
        shixisengHttpClient.getDetailPageThreadPool().execute(new JobDetailPageTask(url));
    }

    /**
     * 处理详情页
     *
     * @param page 职位的详情页
     */
    void handle(Page page) {
        JobDetailPageParser parser = null;

        Job job = new Job();
        try {
            parser = proxyJobDetailPageParser;
            job = parser.parserJobDetail(page);
            logger.info("解析职位详情页成功" + job.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //数量加一
        parseJobCount.incrementAndGet();
    }
}

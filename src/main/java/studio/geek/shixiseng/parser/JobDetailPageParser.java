package studio.geek.shixiseng.parser;

/**
 * Created by Liuqi
 * Date: 2017/4/8.
 */

import org.apache.log4j.Logger;
import studio.geek.core.SimpleLogger;
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
            synchronized (jobDetailPageParser) {
                if (jobDetailPageParser == null) {
                    jobDetailPageParser = new JobDetailPageParser();
                }
            }
        }
        return jobDetailPageParser;
    }

    private JobDetailPageParser() {
    }

    public static Job parserJobDetail(Page page) {
        return null;
    }
}

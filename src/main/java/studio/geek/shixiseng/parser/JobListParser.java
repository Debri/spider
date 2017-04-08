package studio.geek.shixiseng.parser;

/**
 * Created by Liuqi
 * Date: 2017/4/8.
 */

import org.apache.log4j.Logger;
import studio.geek.core.SimpleLogger;
import studio.geek.shixiseng.entity.Job;
import studio.geek.shixiseng.entity.Page;

import java.util.List;

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

    public static List<Job> parserJobList(Page page) {
        return null;
    }
}

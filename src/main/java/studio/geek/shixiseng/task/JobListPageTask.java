package studio.geek.shixiseng.task;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.log4j.Logger;
import studio.geek.shixiseng.entity.Page;
import studio.geek.shixiseng.parser.JobListParser;
import studio.geek.util.DBConnectionManager;
import studio.geek.util.MyInvocationHandler;
import studio.geek.util.SimpleLogger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Liuqi
 * Date: 2017/4/9.
 */

/**
 * 职位列表页
 */
public class JobListPageTask extends AbstractWebPageTask {
    private static Logger logger = SimpleLogger.getSimpleLogger(JobListPageTask.class);
    private static JobListParser proxyJobListParser;
    private static Map<Thread, Connection> connectionMap = new ConcurrentHashMap<Thread, Connection>();

    static {

    }

    public JobListPageTask(HttpRequestBase request) {
        super(request);
    }

    void retry() {
        shixisengHttpClient.getListPageThreadPool().execute(new JobListPageTask(request));
    }

    /**
     * 处理列表页
     *
     * @param page 通过关键词搜索得到的职位列表页
     */
    void handle(Page page) {
        //通过关键词查询到的detailPage


    }

    public JobListParser getProxyJobListParser() {
        JobListParser jobListParser = JobListParser.getInstance();
        InvocationHandler invocationHandler = new MyInvocationHandler(jobListParser);
        JobListParser proxyUJobListParser = (JobListParser) Proxy.newProxyInstance(jobListParser.getClass().getClassLoader(), jobListParser.getClass().getInterfaces(), invocationHandler);
        return proxyUJobListParser;
    }

    /**
     * 每一个线程维护一个connection
     *
     * @return
     */
    public Connection getConnection() {
        Thread currentThread = Thread.currentThread();
        Connection conn = null;
        if (!connectionMap.containsKey(currentThread)) {
            conn = DBConnectionManager.createConnection();
            connectionMap.put(currentThread, conn);
        } else {
            conn = connectionMap.get(currentThread);
        }
        return conn;
    }

    public static Map<Thread, Connection> getConnectionMap() {
        return connectionMap;
    }


}

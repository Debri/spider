package studio.geek.shixiseng.task;

/**
 * Created by Liuqi
 * Date: 2017/4/9.
 */

import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.log4j.Logger;
import studio.geek.shixiseng.ShixisengHttpClient;
import studio.geek.shixiseng.dao.JobDao;
import studio.geek.shixiseng.dao.JobDaoImpl;
import studio.geek.shixiseng.entity.Page;
import studio.geek.util.MyInvocationHandler;
import studio.geek.util.SimpleLogger;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 下载网页并并且执行解析
 */
public abstract class AbstractWebPageTask implements Runnable {
    private static Logger logger = SimpleLogger.getSimpleLogger(AbstractWebPageTask.class);
    protected String url;
    protected HttpRequestBase httpRequestBase;
    protected static JobDao jobDao;
    protected static ShixisengHttpClient shixisengHttpClient;

    static {
        jobDao = getJobDao();
    }

    public AbstractWebPageTask(String url) {
        this.url = url;
    }

    public AbstractWebPageTask(HttpRequestBase httpRequestBase) {
        this.httpRequestBase = httpRequestBase;
    }

    /*    public void run() {
            long requestStartTime = 0l;
            HttpGet tempRequest = null;
            try {
                Page page = null;
                if (url != null) {
                    requestStartTime = System.currentTimeMillis();
                    page = shixisengHttpClient.getWebPage(url);
                }else if (httpRequestBase!=null){
                    requestStartTime = System.currentTimeMillis();
                    page = shixisengHttpClient.getWebPage(httpRequestBase);
                }
                long requestEndTime = System.currentTimeMillis();




            } catch (IOException e) {
                logger.error("打开页面错误");
                e.printStackTrace();
            }

        }*/
    public void run() {
        long requestStartTime = 0l;
        HttpGet tempRequest = null;
        try {
            Page page = null;
            if (url != null) {
                requestStartTime = System.currentTimeMillis();
                page = shixisengHttpClient.getWebPage(url);
            } else if (httpRequestBase != null) {
                requestStartTime = System.currentTimeMillis();
                page = shixisengHttpClient.getWebPage(httpRequestBase);
            }
            long requestEndTime = System.currentTimeMillis();

            int status = page.getStatusCode();

            if (status == HttpStatus.SC_OK) {
                if (page.getHtml().contains("zhihu")) {
                    logger.debug("111");

                } else {
                    /**
                     * 代理异常，没有正确返回目标url
                     */
                    logger.warn("proxy exception:" +);
                }

            }
            /**
             * 401--不能通过验证
             */
            else if (status == 404 || status == 401 ||
                    status == 410) {
                logger.warn(logStr);
            } else {
                logger.error(logStr);
                Thread.sleep(100);
                retry();
            }
        } catch (InterruptedException e) {
            logger.error("InterruptedException", e);
        } catch (IOException e) {

            if (!shixisengHttpClient.getDetailListPageThreadPool().isShutdown()) {
                retry();
            }
        } finally {
            if (request != null) {
                request.releaseConnection();
            }
            if (tempRequest != null) {
                tempRequest.releaseConnection();
            }

        }
    }

    /**
     * 统计执行时间
     *
     * @return
     */
    private static JobDao getJobDao() {
        JobDao jobDao = new JobDaoImpl();
        InvocationHandler invocationHandler = new MyInvocationHandler(jobDao);
        jobDao = (JobDao) Proxy.newProxyInstance(jobDao.getClass().getClassLoader(), jobDao.getClass().getInterfaces(), invocationHandler);
        return jobDao;
    }
}

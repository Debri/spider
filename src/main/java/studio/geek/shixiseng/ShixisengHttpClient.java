package studio.geek.shixiseng;

import org.apache.log4j.Logger;
import studio.geek.core.SimpleThreadPoolExecutor;
import studio.geek.core.ThreadPoolMonitor;
import studio.geek.shixiseng.task.JobDetailPageTask;
import studio.geek.util.SimpleLogger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Liuqi
 * Date: 2017/4/8.
 */
public class ShixisengHttpClient {
    private static Logger logger = SimpleLogger.getSimpleLogger(ShixisengHttpClient.class);
    private volatile static ShixisengHttpClient shixisengHttpClient;

    public ShixisengHttpClient getInstance() {

        if (shixisengHttpClient == null) {
            synchronized (ShixisengHttpClient.class) {
                if (shixisengHttpClient == null) {
                    shixisengHttpClient = new ShixisengHttpClient();
                }
            }
        }
        return shixisengHttpClient;
    }

    private ShixisengHttpClient() {
    }

    /**
     * 列表页下载多线程
     */
    ThreadPoolExecutor listPageThreadPool;
    /**
     * 详情页下载线程池
     */
    private ThreadPoolExecutor detailPageThreadPool;

    private void intiThreadPool() {
        detailPageThreadPool = new SimpleThreadPoolExecutor(10,
               10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                "detailPageThreadPool");
        listPageThreadPool = new SimpleThreadPoolExecutor(50, 80,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(5000),
                new ThreadPoolExecutor.DiscardPolicy(), "listPageThreadPool");
        new Thread(new ThreadPoolMonitor(detailPageThreadPool, "DetailPageDownloadThreadPool")).start();
        new Thread(new ThreadPoolMonitor(listPageThreadPool, "ListPageDownloadThreadPool")).start();

    }
    public void startCrawl(String url){
        detailPageThreadPool.execute(new JobDetailPageTask(url));
        manageHttpClient();
    }


    /**
     * 管理知乎客户端
     * 关闭整个爬虫
     */
    public void manageHttpClient(){
        while (true) {
            /**
             * 下载网页数
             */
            long downloadPageCount = detailListPageThreadPool.getTaskCount();
//            if (downloadPageCount >= Config.downloadPageCount &&
//                    ! ZhiHuHttpClient.getInstance().getDetailPageThreadPool().isShutdown()) {
//                isStop = true;
//                /**
//                 * shutdown 下载网页线程池
//                 */
//                ZhiHuHttpClient.getInstance().getDetailPageThreadPool().shutdown();
//            }
//            if(ZhiHuHttpClient.getInstance().getDetailPageThreadPool().isTerminated() &&
//                    !ZhiHuHttpClient.getInstance().getListPageThreadPool().isShutdown()){
//                /**
//                 * 下载网页线程池关闭后，再关闭解析网页线程池
//                 */
//                ZhiHuHttpClient.getInstance().getListPageThreadPool().shutdown();
//            }
//            if(ZhiHuHttpClient.getInstance().getListPageThreadPool().isTerminated()){
//                /**
//                 * 关闭线程池Monitor
//                 */
//                ThreadPoolMonitor.isStopMonitor = true;
//                /**
//                 * 关闭ProxyHttpClient客户端
//                 */
//                ProxyHttpClient.getInstance().getProxyTestThreadExecutor().shutdownNow();
//                ProxyHttpClient.getInstance().getProxyDownloadThreadExecutor().shutdownNow();
//                logger.info("--------------爬取结果--------------");
//                logger.info("获取用户数:" + parseUserCount.get());
//                break;
//            }
            if (downloadPageCount >= Config.downloadPageCount &&
                    !detailListPageThreadPool.isShutdown()) {
                isStop = true;
                detailListPageThreadPool.shutdown();
            }
            if(detailListPageThreadPool.isShutdown()){
                //关闭数据库连接
                Map<Thread, Connection> map = DetailListPageTask.getConnectionMap();
                for(Connection cn : map.values()){
                    try {
                        if (cn != null && !cn.isClosed()){
                            cn.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            double costTime = (System.currentTimeMillis() - startTime) / 1000.0;//单位s
            logger.debug("抓取速率：" + parseUserCount.get() / costTime + "个/s");
//            logger.info("downloadFailureProxyPageSet size:" + ProxyHttpClient.downloadFailureProxyPageSet.size());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        System.exit(0);
    }

    public ThreadPoolExecutor getDetailPageThreadPool() {
        return detailPageThreadPool;
    }

    public ThreadPoolExecutor getListPageThreadPool() {
        return listPageThreadPool;
    }



    /*public void doTask() throws IOException {
        String path = "http://www.shixiseng.com/interns?k=java&p=1";
        //get方法设置参数要？号
        StringBuilder sb = new StringBuilder(path);
        // sb.append("?");
        //sb.append("username=").append(URLEncoder.encode("我是大帅哥HttpClientGET","utf-8"));
        HttpClient httpClient = new DefaultHttpClient();//浏览器
        //2指定请求方式
        HttpGet httpGet = new HttpGet(sb.toString());
        //3.执行请求
        HttpResponse httpResponse = httpClient.execute(httpGet);
        //4 判断请求是否成功
        int status = httpResponse.getStatusLine().getStatusCode();

        if (status == 200) {
            //读取响应内容
            String result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            System.out.println(result);
        }
    }
*/

}

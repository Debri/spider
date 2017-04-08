package studio.geek.shixiseng;

import studio.geek.shixiseng.entity.Page;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Liuqi
 * Date: 2017/4/8.
 */
public class ShixisengHttpClient {
    private volatile static ShixisengHttpClient shixisengHttpClient;

    public ShixisengHttpClient getInstance() {

        if (shixisengHttpClient == null) {
            synchronized (shixisengHttpClient) {
                if (shixisengHttpClient == null) {
                    shixisengHttpClient = new ShixisengHttpClient();
                }
            }
        }
        return shixisengHttpClient;
    }

    /**
     * 列表页下载多线程
     */
    ThreadPoolExecutor listPageThreadPool;

    private ShixisengHttpClient() {
    }

    /**
     * 下载页面
     *
     * @param url
     * @return page 页面封装
     */
    public Page getWebPage(String url) {
        return null;
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

package studio.geek.shixiseng;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;
import studio.geek.shixiseng.entity.Page;
import studio.geek.util.HttpClientUtil;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Liuqi
 * Date: 2017/4/8.
 */
public class ShixisengHttpClient {
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
     * 下载页面
     *
     * @param url
     * @return page 页面封装
     */
    public Page getWebPage(String url) throws IOException {
        return getWebPage(url, "UTF-8");
    }

    public Page getWebPage(String url, String charset) throws IOException {
        Page page = new Page();
        CloseableHttpResponse response = null;
        response = HttpClientUtil.getResponse(url);
        page.setStatusCode(response.getStatusLine().getStatusCode());
        page.setUrl(url);
        try {
            if (page.getStatusCode() == 200) {
                page.setHtml(EntityUtils.toString(response.getEntity(), charset));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return page;
    }

    public Page getWebPage(HttpRequestBase request) throws IOException {
        CloseableHttpResponse response = null;
        response = HttpClientUtil.getResponse(request);
        Page page = new Page();
        page.setStatusCode(response.getStatusLine().getStatusCode());
        page.setHtml(EntityUtils.toString(response.getEntity()));
        page.setUrl(request.getURI().toString());
        return page;
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

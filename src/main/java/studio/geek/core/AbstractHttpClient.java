package studio.geek.core;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import studio.geek.shixiseng.entity.Page;
import studio.geek.util.HttpClientUtil;
import studio.geek.util.SimpleLogger;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Liuqi
 * Date: 2017/4/9.
 */

/**
 * 抽象类
 */
public class AbstractHttpClient {
    private Logger logger = SimpleLogger.getSimpleLogger(AbstractHttpClient.class);

    /**
     * 获得文件流
     * @param url
     * @return
     */
    public InputStream getWebPageInputStream(String url) {
        try {
            CloseableHttpResponse response = HttpClientUtil.getResponse(url);
            return response.getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Page getWebPage(String url) {
        return getWebPage(url, "UTF-8");
    }

    public Page getWebPage(String url, String charset) {
        Page page = new Page();
        CloseableHttpResponse response = null;
        try {
            response = HttpClientUtil.getResponse(url);
            page.setStatusCode(response.getStatusLine().getStatusCode());
            page.setUrl(url);
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

    public Page getWebPage(HttpRequestBase request) {
        CloseableHttpResponse response = null;
        Page page = new Page();
        try {
            response = HttpClientUtil.getResponse(request);
            page.setStatusCode(response.getStatusLine().getStatusCode());
            page.setHtml(EntityUtils.toString(response.getEntity()));
            page.setUrl(request.getURI().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
    }

    /**
     * 反序列化CookiesStore
     *
     * @return
     */
    public boolean deserializeCookieStore(String path) {
        try {
            CookieStore cookieStore = (CookieStore) HttpClientUtil.deserializeObject(path);
            HttpClientUtil.setCookieStore(cookieStore);
        } catch (Exception e) {
            logger.warn("反序列化Cookie失败,没有找到Cookie文件");
            return false;
        }
        return true;
    }
}

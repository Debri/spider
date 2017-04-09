package studio.geek.shixiseng.entity;

/**
 * Created by Liuqi
 * Date: 2017/4/8.
 */

/**
 * 封装一个页面
 */
public class Page {

    private String url;
    private int statusCode;
    private String html;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @Override
    public String toString() {
        return "Page{" +
                "url='" + url + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Page))
            return false;

        Page page = (Page) o;

        if (getStatusCode() != page.getStatusCode())
            return false;
        return getUrl().equals(page.getUrl());

    }

    @Override
    public int hashCode() {
        int result = getUrl().hashCode();
        result = 31 * result + getStatusCode();
        return result;
    }
}

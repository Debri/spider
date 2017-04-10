package studio.geek.shixiseng.task;

/**
 * Created by Liuqi
 * Date: 2017/4/10.
 */

import org.apache.http.client.methods.HttpRequestBase;
import studio.geek.shixiseng.entity.Page;

/**
 * 公司页里面职位列表
 */
public class CompanyJobListPageTask  extends AbstractWebPageTask{
    public CompanyJobListPageTask(HttpRequestBase request) {
        super(request);
    }

    void retry() {

    }

    void handle(Page page) {

    }
}

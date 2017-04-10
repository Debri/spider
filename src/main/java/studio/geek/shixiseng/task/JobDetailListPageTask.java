package studio.geek.shixiseng.task;

import studio.geek.shixiseng.entity.Page;

import java.sql.Connection;
import java.util.Map;

/**
 * Created by Liuqi
 * Date: 2017/4/10.
 */

/**
 * 职位
 */
public class JobDetailListPageTask extends AbstractWebPageTask {
    private static Map<Thread, Connection> connectionMap;

    public JobDetailListPageTask(String url) {
        super(url);
    }

    public static Map<Thread,Connection> getConnectionMap() {
        return connectionMap;
    }

    void retry() {

    }

    void handle(Page page) {

    }
}

package studio.geek.shixiseng.dao;

import studio.geek.Util.DBConnectionManager;
import studio.geek.shixiseng.entity.Company;
import studio.geek.shixiseng.entity.Job;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Liuqi
 * Date: 2017/4/7.
 */

/**
 * 对工作信息持久化，保存到数据库
 */
public class JobDaoImpl implements JobDao {

    /**
     * 插入查询url
     *
     * @param conn
     * @param md5Url
     * @return
     */
    public boolean insertUrl(Connection conn, String md5Url) {
        return false;
    }

    /**
     * 插入一条职位信息
     *
     * @param job
     * @return
     */
    public boolean insertJob(Job job) {
        Connection conn = DBConnectionManager.getConnection();
        return insertJob(conn, job);

    }

    public boolean insertJob(Connection conn, Job job) {
        String sql = "insert into shixiseng () values ()";
        try {
            conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断职位信息是否存在
     * 根据职位名，city和company来判断
     *
     * @param job
     * @return
     */
    public boolean isExistJob(Job job) {
        return false;
    }

    public boolean isExistJob(Connection conn, Job job) {
        return false;
    }

    /**
     * 根据公司删除职位信息
     * @param company
     * @return
     */
    public boolean deleteJob(Company company) {
        return false;
    }
}

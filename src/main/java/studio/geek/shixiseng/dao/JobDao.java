package studio.geek.shixiseng.dao;

import studio.geek.shixiseng.entity.Company;
import studio.geek.shixiseng.entity.Job;

import java.sql.Connection;

/**
 * Created by Liuqi
 * Date: 2017/4/7.
 */

public interface JobDao {
    public boolean insertUrl(Connection conn, String md5Url);

    public boolean insertJob(Job job);

    public boolean insertJob(Connection conn, Job job);

    public boolean isExistJob(Job job);

    public boolean isExistJob(Connection conn, Job job);

    boolean deleteJob(Company company);


}

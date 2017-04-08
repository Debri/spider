package studio.geek.shixiseng.entity;

/**
 * Created by Liuqi
 * Date: 2017/4/7.
 */
public class Job {
    private String identity;//唯一标识，也可以作为访问职位的url后缀
    private String searchKeyword;
    private String jobName;
    private int lowSalary;//职位工资的范围，例如100-150/天   此处表示100，highSalary表示150
    private int highSalary;//此处表示150
    private String jobType;//职位类型，公关，设计 财会，软件，，，，，，，，，，，，，
    private String city;
    private int time;//要求实习时间
    private Company company;


    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getLowSalary() {
        return lowSalary;
    }

    public void setLowSalary(int lowSalary) {
        this.lowSalary = lowSalary;
    }

    public int getHighSalary() {
        return highSalary;
    }

    public void setHighSalary(int highSalary) {
        this.highSalary = highSalary;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}

package studio.geek.shixiseng.entity;

/**
 * Created by Liuqi
 * Date: 2017/4/7.
 */

/**
 * 公司类
 */
public class Company {
    private String scale;//规模
    private String name;
    private String url;//公司在实习生的网址
    private String companyType;//例如 计算机/互联网 ，化工

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    @Override
    public String toString() {
        return "Company{" +
                "scale='" + scale + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", companyType='" + companyType + '\'' +
                '}';
    }
}

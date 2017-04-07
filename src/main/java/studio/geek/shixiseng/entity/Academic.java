package studio.geek.shixiseng.entity;

/**
 * Created by Liuqi
 * Date: 2017/4/7.
 */

/**
 * 学历枚举类
 */
public enum Academic {
    college("大专"),//大专
    bachelor("本科"),//本科
    master("硕士"),//硕士
    doctor("博士"),//博士
    any("不限");//any

    private String acade;

    Academic(String acade) {
        this.acade = acade;
    }

    public String getAcade() {
        return acade;
    }

    public void setAcade(String acade) {
        this.acade = acade;
    }

    @Override
    public String toString() {
        return "Academic{" +
                "acade='" + acade + '\'' +
                '}';
    }
}

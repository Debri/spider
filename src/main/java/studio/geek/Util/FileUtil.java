package studio.geek.Util;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * Created by Liuqi
 * Date: 2017/4/6.
 */
public class FileUtil {
    private final static String BasePath = "";//所有文件保存的根路径

    /**
     * 根据一个文件的URI将文件下载
     *
     * @param uri
     */
    public static void getFileByURI(String uri) {
        String fileName = "";
        File file = new File(fileName);
        URL url = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        byte[] buffer = new byte[1024];
        int n = 0;
        try {
            url = new URL(uri);
            inputStream = url.openConnection().getInputStream();
            outputStream = new FileOutputStream(file);
            while (inputStream.read(buffer) != -1) {
                outputStream.write(buffer, 0, buffer.length);
            }
        } catch (MalformedURLException e) {
            // e.printStackTrace();
            System.out.println("URI 错误");
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("打开链接错误");
        }
    }

    /**
     * 根据文件的URI获取文件名还有后缀名
     *
     * @param url 文件的url
     * @return
     */
    public static String getFileNameByURI(String url) {
        Pattern pattern = Pattern.compile("");
        String[] str = url.split("/");
        return str[str.length - 1];
    }
}

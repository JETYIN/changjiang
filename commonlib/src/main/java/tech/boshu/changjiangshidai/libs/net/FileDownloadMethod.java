package tech.boshu.changjiangshidai.libs.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 文件下载类
 */
public class FileDownloadMethod {

    private static final String TAG = FileDownloadMethod.class.getSimpleName();

    /**
     * 文件保存路径
     */
    private File file;
    /**
     * 文件下载路径
     */
    private URL downloadUrl;

    /**
     * @param downloadUrl:文件下载地址
     * @param file:文件保存路径
     */
    public FileDownloadMethod(URL downloadUrl, File file) {
        this.downloadUrl = downloadUrl;
        this.file = file;
    }

    public boolean download() {
        boolean result = false;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        int length = 0;
        try {
            URLConnection conn = downloadUrl.openConnection();
            conn.connect();
            length = conn.getContentLength();
            if (file != null && file.exists()) {
                result = length == file.length();
                if (result) {
                    return result;
                }
                file.delete();
            }
            file.createNewFile();
            bis = new BufferedInputStream(conn.getInputStream());
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            result = length == file.length();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return result;
    }
}
package tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux;

/**
 * Created by Stone on 2015/11/26.
 * Project:YongHuiProject
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class ErrorBase {
    public ErrorBase(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code;
    public String message;
}

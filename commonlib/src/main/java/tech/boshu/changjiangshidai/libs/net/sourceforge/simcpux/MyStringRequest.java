package tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux;

import tech.boshu.changjiangshidai.libs.net.FakeX509TrustManager;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;


/**
 * Created by Stone on 2015/8/31.
 * Project:YongHuiProject
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class MyStringRequest extends StringRequest {
    public MyStringRequest(int method, String url, Response.Listener<String> listener, Response
            .ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        FakeX509TrustManager.allowAllSSL();
    }
}

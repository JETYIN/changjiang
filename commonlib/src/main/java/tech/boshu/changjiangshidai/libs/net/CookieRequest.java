package tech.boshu.changjiangshidai.libs.net;


import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by allipper on 2015-12-10.
 */
public class CookieRequest extends JsonObjectRequest {
    private final int SOCKET_TIMEOUT = 30000;
    private Map<String, String> mHeaders = new HashMap<String, String>(1);
    private String mHeader;
    public String cookieFromResponse;

    public CookieRequest(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener,
                         Response.ErrorListener errorListener) {
        super(url, jsonRequest, listener, errorListener);
    }

    public CookieRequest(int method, String url, JSONObject jsonRequest, Response
            .Listener<JSONObject> listener,
                         Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
        setSocketTimeout(SOCKET_TIMEOUT);
    }

    /**
     * 设置请求延迟
     *
     * @param timeOut
     */
    public void setSocketTimeout(int timeOut) {
        setRetryPolicy(new DefaultRetryPolicy(timeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public void setCookie(String cookie) {
        mHeaders.put("Cookie", cookie);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString =
                    new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            JSONObject jsonObject = new JSONObject(jsonString);
            mHeader = response.headers.toString();
            Log.w("LOG", "get headers in parseNetworkResponse " + response.headers.toString());
            //使用正则表达式从reponse的头中提取cookie内容的子串
            Pattern pattern = Pattern.compile("Set-Cookie.*?;");
            Matcher m = pattern.matcher(mHeader);
            if (m.find()) {
                cookieFromResponse = m.group();
                Log.w("LOG", "cookie from server " + cookieFromResponse);

                //去掉cookie末尾的分号
                cookieFromResponse = cookieFromResponse.substring(11, cookieFromResponse.length()
                        - 1);
                Log.w("LOG", "cookie substring " + cookieFromResponse);
                //将cookie字符串添加到jsonObject中，该jsonObject会被deliverResponse递交，调用请求时则能在onResponse
                // 中得到jsonObject = new JSONObject(jsonString);
                jsonObject.put("Cookie", cookieFromResponse);

            }
            Log.w("Header", "jsonObject " + jsonObject.toString());
            return Response.success(jsonObject,
                    HttpHeaderParser.parseCacheHeaders(response));


        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeaders;
    }


}
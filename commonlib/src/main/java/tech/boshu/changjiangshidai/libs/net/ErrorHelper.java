package tech.boshu.changjiangshidai.libs.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import tech.boshu.changjiangshidai.libs.R;
import tech.boshu.changjiangshidai.libs.bean.ResponseBase;
import tech.boshu.changjiangshidai.libs.manager.Contextor;


/**
 * Created by Administrator on 2015/7/8.
 */
public class ErrorHelper {

    public static String getMessage(VolleyError error) {
        if (error instanceof TimeoutError) {
            return Contextor.getInstance().getContext().getResources().getString(R.string.network_error_timeout);
        } else if (isNetworkProblem(error)) {
            return Contextor.getInstance().getContext().getResources().getString(R.string.network_error_timeout);
        } else if (isServerProblem(error)) {
            String message = "";
            Gson gson = new Gson();
            try {
                ResponseBase baseError = gson.fromJson(new String(error.networkResponse.data,
                        "UTF-8"), ResponseBase.class);
                message = baseError.msg;
            } catch (Exception e) {
                message = Contextor.getInstance().getContext().getResources().getString(R.string.network_error_generic);
                e.printStackTrace();
            } finally {
                return message;
            }
        } else if (error instanceof ParseError) {
            return Contextor.getInstance().getContext().getResources().getString(R.string.network_error_generic);
        } else {
            return Contextor.getInstance().getContext().getResources().getString(R.string.network_error_generic);
        }
    }

    private static boolean isNetworkProblem(VolleyError error) {
        return (error instanceof NetworkError || error instanceof NoConnectionError);
    }

    private static boolean isServerProblem(VolleyError error) {
        return (error instanceof AuthFailureError || error instanceof ServerError);
    }
}

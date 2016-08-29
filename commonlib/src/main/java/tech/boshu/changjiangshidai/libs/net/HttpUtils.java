package tech.boshu.changjiangshidai.libs.net;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import tech.boshu.changjiangshidai.libs.manager.Contextor;

/**
 * Created by Administrator on 2015/7/8.
 */
public class HttpUtils {
    private static final String TAG = HttpUtils.class.getSimpleName();

    private static HttpUtils mInstance;
    private static RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private HttpUtils() {
        // 创建所有请求的队列
        mRequestQueue = getRequestQueue();
        // 初始化图片加载器，添加图片内存缓存
        mImageLoader = new ImageLoader(mRequestQueue, new LruBitmapCache(Contextor.getInstance().getContext()));
    }

    /**
     * 创建网络请求队列
     *
     * @return
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // 设置是否打印日志
            // VolleyLog.DEBUG = Constant.IS_VOLLEY_DEBUG_MODE;
            // 取得Application的Context，防止因为Activity的重新加载导致网络请求和图片加载的抖动
            mRequestQueue = Volley.newRequestQueue(Contextor.getInstance().getContext());
        }
        return mRequestQueue;
    }

    /**
     * 清除网络请求的缓存
     */
    public void clearCache() {
        getRequestQueue().getCache().clear();
    }

    public static synchronized HttpUtils getInstance() {
        if (mInstance == null) {
            mInstance = new HttpUtils();
        }
        return mInstance;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    /**
     * JSON数据请求
     *
     * @param tag
     * @param request
     * @param <T>
     */
    public <T> void request(Object tag, Request<T> request) {
        if (request != null) {
            if (tag != null) {
                request.setTag(tag);
            } else {
                request.setTag(TAG);
            }
            getRequestQueue().add(request);
        }
    }

    /**
     * 消设置对应Tag的网络请求
     *
     * @param tag 为空时，取消所有默认请求
     */
    public static void cancelAll(String tag) {
        if (tag != null) {
            HttpUtils.getInstance().getRequestQueue().cancelAll(tag);
        } else {
            cancelAll(TAG);
        }
    }
}

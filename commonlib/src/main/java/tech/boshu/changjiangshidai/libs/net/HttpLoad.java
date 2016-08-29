package tech.boshu.changjiangshidai.libs.net;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import tech.boshu.changjiangshidai.libs.utils.Constant;
import tech.boshu.changjiangshidai.libs.utils.Utils;


/**
 * HTTP所有请求方法管理类
 * Created by Administrator on 2015/7/8.
 */
public class HttpLoad {

    //获取图片，并指定默认图片的资源ID
    public static void getImage(String url, int defaultRes, ImageView
            imageView, int width, int height) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(Utils.getDefaultImage(defaultRes));
            return;
        }
        if (!url.startsWith("http")) {
            url = Constant.BASE_HOST + url;
        }

        HttpUtils.getInstance()
                .getImageLoader()
                .get(url,
                        ImageLoader.getImageListener(imageView,
                                Utils.getDefaultImage(defaultRes),
                                Utils.getDefaultImage(defaultRes)),
                        width,
                        height);
    }

    //获取图片，用控件的尺寸
    public static void getImage(String url, int defaultRes, ImageView
            imageView) {
        getImage(url, defaultRes, imageView, imageView.getWidth(), imageView.getHeight());
    }

    //获取图片，并使用特定的默认图片
    public static void getImage(String url, ImageView imageView) {
        getImage(url, -1, imageView);
    }

    //获取图片，指定特定的默认图片，指定高宽
    public static void getImage(String url, ImageView imageView, int width,
                                int height) {
        getImage(url, -1, imageView, width, height);
    }

    public static void downloadImage(String url, final String filePath, final
    ImageDownload imageDownload) {
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(filePath)) {
            return;
        }
        HttpUtils.getInstance().getImageLoader().get(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                if (imageContainer.getBitmap() != null) {
                    File file = new File(filePath);
                    if (file.exists()) {
                        file.delete();
                    }
                    try {
                        FileOutputStream out = new FileOutputStream(file);
                        imageContainer.getBitmap().compress(Bitmap.CompressFormat.PNG, 90, out);
                        out.flush();
                        out.close();
                        imageDownload.onSuccess();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }

    public interface ImageDownload {
        void onSuccess();
    }

    //首页相关模块
    public static abstract class IndexModule {

//        /**
//         * 获取可配置模板主题
//         *
//         * @param context
//         * @param tag
//         * @param callback 回调
//         */
//        public static void getIndexTheme(Context context,
//                                         String tag,
//                                         ResponseCallback<ResponseIndexTheme> callback) {
//            String url = String.format(Constant.API_GET_INDEX_THEME_HOME,
//                    Utils.getStoreCode(context));
//            GsonRequest<ResponseIndexTheme> request = new GsonRequest<>(Request.Method.GET,
//                    url, ResponseIndexTheme.class, null, null, callback, callback);
//            HttpUtils.getInstance(context).request(tag, request);
//        }
    }

    //启动页相关模块
    public static abstract class StartModule {
//        /**
//         * 获取启动页配置主题
//         *
//         * @param context
//         * @param tag
//         * @param callback 回调
//         */
//        public static void getStartTheme(Context context,
//                                         String tag,
//                                         ResponseCallback<ResponseStartTheme> callback) {
//            GsonRequest<ResponseStartTheme> request = new GsonRequest<>(Request.Method.GET,
//                    Constant.API_GET_STARTA_THEME, ResponseStartTheme.class, null, null,
//                    callback, callback);
//            HttpUtils.getInstance(context).request(tag, request);
//        }


    }

}

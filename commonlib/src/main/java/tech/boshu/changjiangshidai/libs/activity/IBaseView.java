package tech.boshu.changjiangshidai.libs.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.AdapterView;

/**
 * 主要对于页面
 * Created by allipper on 2015-12-31.
 */
public interface IBaseView {

    /**
     * 返回事件
     *
     * @param v 返回按钮View
     */
    void back(View v);

    /**
     * 设置title
     */
    void initTitle();

    /**
     * 设置Title
     *
     * @param title
     */
    void initTitle(String title);

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    void setText(int viewId, String text);

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param object
     * @return
     */
    void setText(int viewId, Object object);


    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    void setImageResource(int viewId, int drawableId);

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    void setImageBitmap(int viewId, Bitmap bm);

    /**
     * 为ImageView设置图片
     *
     * @param url
     * @param viewId
     * @return
     */
    void setImageByUrl(String url, int viewId);

    /**
     * 为ImageView设置图片
     *
     * @param url
     * @param defaultResId
     * @param viewId
     * @return
     */
    void setImageByUrl(String url, int defaultResId, int viewId);

    /**
     * 为ImageView设置图片
     *
     * @param url
     * @param viewId
     * @return
     */
    void setImageByLocalUrl(String url, int viewId);

    /**
     * 为View设置点击事件
     *
     * @param resId
     * @param listener
     * @return
     */
    void setOnclickListener(int resId, View.OnClickListener listener);


    void setOnItemClickListener(int resId, AdapterView.OnItemClickListener listener);

    void showToast(String toast);

    void showDialog();

    void hideDialog();
}

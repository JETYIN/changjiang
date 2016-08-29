package tech.boshu.changjiangshidai.libs.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by nuuneoi on 12/15/2015.
 */
public interface IBasePresenter {


    /**
     * 返回事件
     *
     * @param v 返回按钮View
     */
    void back(View v);

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    void setText(int viewId, String text);


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

    void onSaveInstanceState(@NonNull Bundle outState);

    void onRestoreInstanceState(@NonNull Bundle savedInstanceState);

    void setOnclickListener(int right, View.OnClickListener onClickListener);

    void onResume();

    void onNewIntent(Intent intent);

    void onPause();

    void processExit();

    void getDatas();
}

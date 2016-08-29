package tech.boshu.changjiangshidai.libs.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * 处理业务逻辑,更新view等
 * Created by allipper on 12/15/2015.
 */
public class BasePresenter implements IBasePresenter {

    protected IBaseView iBaseView;
    protected IBaseInteractor iBaseInteractor;

    public BasePresenter(IBaseView iBaseView) {
        this.iBaseView = iBaseView;
        this.iBaseInteractor = new BaseInteractorImpl();
    }

    public BasePresenter(IBaseView iBaseView, IBaseInteractor iBaseInteractor) {
        this.iBaseView = iBaseView;
        this.iBaseInteractor = iBaseInteractor;
    }

    @Override
    public void back(View v) {
        iBaseView.back(v);
    }

    @Override
    public void setText(int viewId, String text) {
        iBaseView.setText(viewId, text);
    }

    @Override
    public void setImageResource(int viewId, int drawableId) {
        iBaseView.setImageResource(viewId, drawableId);
    }

    @Override
    public void setImageBitmap(int viewId, Bitmap bm) {
        iBaseView.setImageBitmap(viewId, bm);
    }

    @Override
    public void setImageByUrl(String url, int viewId) {
        iBaseView.setImageByUrl(url, viewId);
    }

    @Override
    public void setImageByUrl(String url, int defaultResId, int viewId) {
        iBaseView.setImageByUrl(url, defaultResId, viewId);
    }

    @Override
    public void setImageByLocalUrl(String url, int viewId) {
        iBaseView.setImageByLocalUrl(url, viewId);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

    }

    @Override
    public void setOnclickListener(int resId, View.OnClickListener onClickListener) {
        iBaseView.setOnclickListener(resId, onClickListener);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @Override
    public void onPause() {
    }

    @Override
    public void processExit() {
        if (iBaseInteractor.processExitBusiness()) {
            iBaseView.back(null);
        }
    }

    @Override
    public void getDatas() {
        iBaseInteractor.getDatas();
    }

}

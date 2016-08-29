package tech.boshu.changjiangshidai.libs.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import tech.boshu.changjiangshidai.libs.R;
import tech.boshu.changjiangshidai.libs.net.HttpLoad;
import tech.boshu.changjiangshidai.libs.net.HttpUtils;
import tech.boshu.changjiangshidai.libs.utils.DialogUtils;
import tech.boshu.changjiangshidai.libs.utils.LocalImageLoader;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;

/**
 * 所有Acitivity的基础类
 * Created by allipper on 2015-12-31.
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener, IBaseView {

    /**
     * TAG用于网络请求处理
     */
    public String TAG;
    protected Context mContext;

    // 布局资源id
    protected int layoutResId;
    // 基础展示层
    protected BasePresenter presenter;

    protected Dialog dialog;

    protected boolean isPullData = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        TAG = mContext.getClass().getSimpleName();
        setLayoutResId();
        setContentView(layoutResId);
        setPresenter();
        initTitle();
        setOnclickListener(R.id.back, new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                presenter.back(view);
            }
        });
        enterAnimation();
    }

    /**
     * 设置文件布局
     */
    protected abstract void setLayoutResId();

    /**
     * 设置展示层
     */
    protected void setPresenter() {
        presenter = new BasePresenter(BaseActivity.this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { //监控/拦截/屏蔽返回键
            presenter.processExit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        if (view != null && view.getId() == R.id.back) {
            presenter.back(view);
        }
    }

    @Override
    protected void onStop() {
        HttpUtils.cancelAll(TAG);
        super.onStop();
    }

    @Override
    public void initTitle() {
        initTitle(getString(R.string.app_name));
    }

    @Override
    public void initTitle(String title) {
        setText(R.id.title, title);
    }

    @Override
    public void setText(int viewId, String text) {
        TextView view = (TextView) findViewById(viewId);
        if (view == null) {
            return;
        }
        if (!TextUtils.isEmpty(text)) {
            view.setText(text);
        } else {
            if(!(view instanceof EditText)){
                view.setText("");
            }
        }
    }

    @Override
    public void setText(int viewId, Object text) {
        TextView view = (TextView) findViewById(viewId);
        if (view == null) {
            return;
        }
        if (!TextUtils.isEmpty(String.valueOf(text))) {
            view.setText(String.valueOf(text));
        } else {
            if(!(view instanceof EditText)){
                view.setText("");
            }
        }
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public void setImageResource(int viewId, int drawableId) {
        ImageView view = (ImageView) findViewById(viewId);
        if (view == null) {
            return;
        }
        view.setImageResource(drawableId);
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    public void setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = (ImageView) findViewById(viewId);
        if (view == null) {
            return;
        }
        view.setImageBitmap(bm);
    }

    /**
     * 为ImageView设置图片
     *
     * @param url
     * @param viewId
     * @return
     */
    public void setImageByUrl(String url, int viewId) {
        ImageView view = (ImageView) findViewById(viewId);
        if (view == null) {
            return;
        }
        HttpLoad.getImage(url, 0, view);
    }

    /**
     * 为ImageView设置图片
     *
     * @param url
     * @param defaultResId
     * @param viewId
     * @return
     */
    public void setImageByUrl(String url, int defaultResId, int viewId) {
        ImageView view = (ImageView) findViewById(viewId);
        if (view == null) {
            return;
        }
        HttpLoad.getImage(url, defaultResId, view);
    }

    /**
     * 为ImageView设置图片
     *
     * @param url
     * @param viewId
     * @return
     */
    public void setImageByLocalUrl(String url, int viewId) {
        ImageView view = (ImageView) findViewById(viewId);
        if (view == null) {
            return;
        }
        LocalImageLoader.getInstance(3, LocalImageLoader.Type.LIFO).loadImage(url, view);
    }

    @Override
    public void setOnclickListener(int resId, View.OnClickListener listener) {
        View view = findViewById(resId);
        if (view == null) {
            return;
        }
        view.setOnClickListener(listener);
    }

    @Override
    public void setOnItemClickListener(int resId, AdapterView.OnItemClickListener listener) {
        ListView view = (ListView) findViewById(resId);
        if (view == null) {
            return;
        }
        view.setOnItemClickListener(listener);
    }

    public void back(View v) {
        onBackPressed();
        exitAnimation();
    }

    public void exit() {
        finish();
        exitAnimation();
    }

    public void enterAnimation() {
        overridePendingTransition(R.anim.in_from_right, R.anim.hold);
    }

    public void exitAnimation() {
        overridePendingTransition(R.anim.hold, R.anim.out_to_right);
    }


    public void showToast(String msg) {
        ToastUtils.show(mContext, msg);
    }

    @Override
    public void showDialog() {
        if(isPullData){
            isPullData = false;
            return;
        }
        if(dialog == null){
            dialog = DialogUtils.createLoadingDialog(mContext);
        }
        dialog.show();
    }

    @Override
    public void hideDialog() {
        if(dialog == null) return;
        dialog.dismiss();
    }
}

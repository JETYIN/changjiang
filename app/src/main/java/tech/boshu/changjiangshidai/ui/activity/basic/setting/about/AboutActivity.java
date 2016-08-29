package tech.boshu.changjiangshidai.ui.activity.basic.setting.about;

import android.os.Bundle;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)AboutActivity.java
 */


public class AboutActivity extends BaseActivity implements IAboutView {
    private AboutPresenter aboutPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_about;
    }

    @Override
    protected void setPresenter() {
        aboutPresenter = (AboutPresenter) (presenter = new AboutPresenter(AboutActivity.this));
    }

    @Override
    public void setView() {

    }

    @Override
    public void initView() {

    }
}
package tech.boshu.changjiangshidai.ui.activity.start;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;

/**
 * Created by allipper on 2015-12-31.
 */
public class StartActivity extends BaseActivity implements IStartView {


    private ImageView loadingImageView;

    private StartPresenter startPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingImageView = (ImageView) findViewById(R.id.loading);
        AlphaAnimation animation = new AlphaAnimation(0.8f, 1.0f);
        animation.setDuration(2000);
        animation.setAnimationListener(startPresenter);
        loadingImageView.startAnimation(animation);
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_start;
    }

    @Override
    protected void setPresenter() {
        startPresenter = (StartPresenter) (presenter = new StartPresenter(StartActivity.this));
    }

    @Override
    public void setLoadingView(String url) {

    }

    @Override
    public void hideLoadingView() {
        loadingImageView.setVisibility(View.GONE);
    }
}

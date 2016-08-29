package tech.boshu.changjiangshidai.ui.activity.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.activity.IBaseView;
import tech.boshu.changjiangshidai.ui.activity.home.HomeActivity;
import tech.boshu.changjiangshidai.utils.SharePre;
import tech.boshu.changjiangshidai.utils.SharePreferenceUtils;

/**
 * Created by allipper on 2016-01-01.
 */
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener, IBaseView {
    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_guide;
    }


    /**
     * ViewPager
     */
    private ViewPager viewPager;

    /**
     * 装点点的ImageView数组
     */
    private ImageView[] tips;
    /**
     * 装置layout的list
     */

    private ArrayList<View> viewContainter = new ArrayList<>();

    public static final int USE_HELPER = 0;
    private int type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 1);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        View view1 = LayoutInflater.from(this).inflate(R.layout.layout_guide_pageone, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.layout_guide_pagetwo, null);
        viewContainter.add(view1);
        viewContainter.add(view2);
        //将导航点加入到ViewGroup中
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enter(view);
            }
        });
        //设置Adapter
        viewPager.setAdapter(new MyAdapter());
        //设置监听，主要是设置导航点点的背景
        viewPager.addOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return viewContainter.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewContainter.get(position));

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewContainter.get(position));
            return viewContainter.get(position);
        }

    }

    public void enter(View view) {
        if (type == USE_HELPER) {
            onBackPressed();
        } else {
            Intent intentIndexActivity = new Intent(this, HomeActivity.class);
            startActivity(intentIndexActivity);
            SharePreferenceUtils.putBoolean(SharePre.App.ISGUIDE, true);
            onBackPressed();
        }
    }

}

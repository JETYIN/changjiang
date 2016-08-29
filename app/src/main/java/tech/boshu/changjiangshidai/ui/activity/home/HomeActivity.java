package tech.boshu.changjiangshidai.ui.activity.home;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.application.ApplicationInit;
import tech.boshu.changjiangshidai.ui.fragment.BasicPageFragment;
import tech.boshu.changjiangshidai.ui.fragment.HomePageFragment;
import tech.boshu.changjiangshidai.ui.fragment.SaledPageFragment;
import tech.boshu.changjiangshidai.ui.fragment.SummaryPageFragment;
import tech.boshu.changjiangshidai.ui.fragment.WarehoursePageFragment;

/**
 * Created by allipper on 2016-01-01.
 */
public class HomeActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {
    public static RadioGroup mBottomGroup;
    ViewPager mMainViewPager;
    MainPagerAdapter mMainPagerAdapter;
    Handler mHandler;
    BroadcastReceiver mMessageCountReceiver;
    private final int[] ButtonIds = {R.id.tab_home, R.id.tab_saled, R.id.tab_warehourse, R.id
            .tab_basic, R.id.tab_summary};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler();
        init();
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    public void gotoHomePage() {
        this.onCheckedChanged(null, R.id.tab_home);
    }


    private void init() {

        mMainViewPager = (ViewPager) findViewById(R.id.tab_content);
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new HomePageFragment());
        fragments.add(new SaledPageFragment());
        fragments.add(new WarehoursePageFragment());
        fragments.add(new BasicPageFragment());
        fragments.add(new SummaryPageFragment());
        mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        mMainViewPager.setAdapter(mMainPagerAdapter);
        mMainViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                mBottomGroup.check(ButtonIds[i]);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mBottomGroup = (RadioGroup) findViewById(R.id.main_tab_group);
        mBottomGroup.setOnCheckedChangeListener(this);
        mBottomGroup.clearCheck();
        mBottomGroup.check(ButtonIds[getIntent().getIntExtra("count", 0)]);
    }

    public void showExitDialog() {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setTitle(getString(R.string.app_name));
        localBuilder.setMessage(getString(R.string.dialog_sure_quit));
        localBuilder.setPositiveButton(getString(android.R.string.ok), new DialogInterface
                .OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int paramAnonymousInt) {
                try {
                    ApplicationInit.getApp().exit();
                    return;
                } catch (Exception e) {

                    System.exit(0);
                }

            }
        });
        localBuilder.setNegativeButton(getString(android.R.string.cancel), new DialogInterface
                .OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int paramAnonymousInt) {
                dialog.dismiss();
            }
        });
        localBuilder.show();
    }

    @Override
    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
        if ((paramInt == 4) && (paramKeyEvent.getRepeatCount() == 0)) {
            showExitDialog();
            return true;
        }
        return super.onKeyDown(paramInt, paramKeyEvent);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent
                .ACTION_DOWN && event.getRepeatCount() == 1) {
            try {
                showExitDialog();
            } catch (Exception e) {
                System.exit(0);
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < ButtonIds.length; i++) {
            if (checkedId == ButtonIds[i]) {
                if (mMainViewPager.getCurrentItem() != i) {
                    onPageSelected(mMainViewPager.getCurrentItem(), false);
                    mMainViewPager.setCurrentItem(i);
                    onPageSelected(i, true);
                }
            }
        }
    }

    private void onPageSelected(int index, boolean flag) {
//        if (index >=0) {
//            for (BaseFragment fragment : mMainPagerAdapter.getList()) {
//                fragment.setChecked(false);
//            }
//            ((BaseFragment) mMainPagerAdapter.getItem(index)).setChecked(flag);
//        }
    }

    private class MainPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments = new ArrayList<Fragment>();

        public MainPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentsList) {
            super(fm);
            this.fragments = fragmentsList;
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public List<Fragment> getList() {
            return fragments;
        }
    }
}
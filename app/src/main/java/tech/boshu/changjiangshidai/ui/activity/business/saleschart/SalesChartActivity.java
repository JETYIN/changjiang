package tech.boshu.changjiangshidai.ui.activity.business.saleschart;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)SalesChartActivity.java
 */


public class SalesChartActivity extends BaseActivity implements ISalesChartView {

    private RelativeLayout title_topRelativeLayout;
    private ImageView backImageView;
    private ImageButton moreImageButton;
    private ImageButton choose_dateImageButton;
    private TextView dateTextView;

    private SalesChartPresenter salesChartPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initTitle("统计图表");
        salesChartPresenter.setText(R.id.right, "");
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_sales_chart;
    }

    @Override
    protected void setPresenter() {
        salesChartPresenter = (SalesChartPresenter) (presenter = new SalesChartPresenter(SalesChartActivity.this));
    }

    public void setView() {
        setText(R.id.date, "2015/11/29到2015/12/02");
    }

    public void initView() {
        title_topRelativeLayout = (RelativeLayout) findViewById(R.id.title_top);
        backImageView = (ImageView) findViewById(R.id.back);
        moreImageButton = (ImageButton) findViewById(R.id.more);
        choose_dateImageButton = (ImageButton) findViewById(R.id.choose_date);
        dateTextView = (TextView) findViewById(R.id.date);

    }
}
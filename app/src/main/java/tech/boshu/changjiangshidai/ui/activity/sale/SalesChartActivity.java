package tech.boshu.changjiangshidai.ui.activity.sale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.ui.activity.business.saleschart.ISalesChartView;

/**
 * Created by Administrator on 2016/1/8.
 */
public class SalesChartActivity extends BaseActivity implements ISalesChartView{
    private ImageButton ibMore;
    private ImageButton ibChooseDate;

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_sales_chart;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ibMore = (ImageButton) findViewById(R.id.more);
        ibChooseDate = (ImageButton) findViewById(R.id.choose_date);
        ibMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalesChartActivity.this, GrossMarginAnalyzeActivity
                        .class);
                startActivity(intent);
            }
        });
        ibChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalesChartActivity.this, UserDefinedDateActivity
                        .class);
                startActivity(intent);
            }
        });
    }
}

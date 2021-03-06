package tech.boshu.changjiangshidai.ui.activity.business;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.SaleDetailAdapter;
import tech.boshu.changjiangshidai.bean.SaleDetail;
import tech.boshu.changjiangshidai.interf.PopupMenuClickListener;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;
import tech.boshu.changjiangshidai.ui.widget.MyPopupSaleDetail;

/**
 * 销售明细 P7-3销售明细
 */
public class SaleDetailSearchActivity extends BaseActivity{

    private TextView tv_StartTime;// 开始时间
    private TextView tv_EndTime;// 结束时间
    private EditText et_BillNo;//单号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setText(R.id.right, "清空");
        initTitle("查询");
        presenter.setOnclickListener(R.id.right, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clean();
            }
        });
        findViews();
        initData();
    }

    /**
     * 清空
     */
    private void clean() {
        ToastUtils.show(this,"clean");
    }

    private void initData() {
    }

    private void findViews() {
        tv_StartTime = (TextView) findViewById(R.id.tv_start_time);
        tv_EndTime = (TextView) findViewById(R.id.tv_end_time);
        et_BillNo = (EditText) findViewById(R.id.et_bill_no);

        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });
    }

    /**
     * 清空
     */
    private void search() {
        ToastUtils.show(this,"search");
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_sale_detail_search;
    }

    @Override
    public void onClick(View view) {
    }

}


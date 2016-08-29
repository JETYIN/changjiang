package tech.boshu.changjiangshidai.ui.activity.business;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;

/**
 * 采购明细 P7-4采购明细
 */
public class PurchaseDetailSearchActivity extends BaseActivity{

    private TextView tv_StartTime;// 开始时间
    private TextView tv_EndTime;// 结束时间
    private EditText et_BillNo;//单号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setText(R.id.right, "清空");
        initTitle("查询");
        setOnclickListener(R.id.right, new View.OnClickListener() {
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
        layoutResId = R.layout.activity_purchase_detail_search;
    }

    @Override
    public void onClick(View view) {
    }

}


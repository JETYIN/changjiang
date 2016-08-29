package tech.boshu.changjiangshidai.ui.activity.stockinfo.checkdetail;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.CheckDetailAdapter;
import tech.boshu.changjiangshidai.bean.result.CheckDetailDto;
import tech.boshu.changjiangshidai.bean.result.CheckOrder;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)CheckDetailActivity.java
 */


public class CheckDetailActivity extends BaseActivity implements ICheckDetailView {
    private TextView tvOrderId;
    private TextView tvStoreName;
    private TextView tvOperator;
    private TextView tvTotalNum;
    private TextView tvRemark;
    private TextView tvTotalPlMoney;
    private ListView listListView;

    private ImageView icon1ImageView;
    private ImageView icon2ImageView;
    private ImageView icon3ImageView;
    private ImageView icon_rightImageView;
    private ImageView icon_right2ImageView;

    private CheckDetailAdapter adapter;
    private CheckDetailPresenter checkDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_check_detail;
    }

    @Override
    protected void setPresenter() {
        checkDetailPresenter = new CheckDetailPresenter(this);
        presenter = checkDetailPresenter;
    }

    public void initView() {
        checkDetailPresenter.setOrderId(getIntent().getStringExtra("orderId"));
        tvOrderId = (TextView) findViewById(R.id.tv_order_id);
        tvOperator = (TextView) findViewById(R.id.tv_operator);
        tvStoreName = (TextView) findViewById(R.id.tv_store_name);
        tvTotalNum = (TextView) findViewById(R.id.tv_total_num);
        tvRemark = (TextView) findViewById(R.id.tv_remark);
        tvTotalPlMoney = (TextView) findViewById(R.id.tv_total_pl_money);
        listListView = (ListView) findViewById(R.id.list);

        icon1ImageView = (ImageView) findViewById(R.id.icon1);
        icon2ImageView = (ImageView) findViewById(R.id.icon2);
        icon3ImageView = (ImageView) findViewById(R.id.icon3);
        icon_rightImageView = (ImageView) findViewById(R.id.icon_right);
        icon_right2ImageView = (ImageView) findViewById(R.id.icon_right2);
        adapter = new CheckDetailAdapter(mContext, null);
        listListView.setAdapter(adapter);

        initTitle("盘点单");
        checkDetailPresenter.setText(R.id.right, "");
        checkDetailPresenter.setCheckDetail();
    }

    @Override
    public void setCheckDetailInfo(CheckOrder checkOrder) {
        tvOrderId.setText(checkOrder.billNo);
        tvOperator.setText(checkOrder.operator);
        tvStoreName.setText(checkOrder.stroe);
        tvRemark.setText(checkOrder.memo);
        tvTotalNum.setText("合计：" + checkOrder.totalCheckNum);
        tvTotalPlMoney.setText("盈亏合计：￥" + checkOrder.totalPlMoney);
    }

    @Override
    public void setCheckDetailList(List<CheckDetailDto> items) {
        adapter.setData(items);
    }
}
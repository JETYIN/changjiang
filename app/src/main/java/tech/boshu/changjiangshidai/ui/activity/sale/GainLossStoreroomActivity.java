package tech.boshu.changjiangshidai.ui.activity.sale;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.OrderDetailAdapter;
import tech.boshu.changjiangshidai.adapter.ProductAdapter;
import tech.boshu.changjiangshidai.bean.api.SalesRequest;
import tech.boshu.changjiangshidai.bean.mode.OrderDetailDto;
import tech.boshu.changjiangshidai.bean.mode.ResponseLossInfor;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;
import tech.boshu.changjiangshidai.libs.view.AutoHeightListView;

/**
 * Created by Administrator on 2016/1/8.
 */
public class GainLossStoreroomActivity extends BaseActivity {
    private String action;
    private String orderId;
    private TextView tvCustomer;
    private TextView tvStore;
    private TextView tvPayWay;
    private TextView tvTotalPrice;
    private TextView tvOrderCodeValue;
    private TextView tvCreateManValue;
    private TextView tvOrderTimeValue;
    private TextView tvRemark;
    private OrderDetailAdapter adapter;
    private List<OrderDetailDto> datas = new ArrayList<>();
    private AutoHeightListView ahGainLossList;


    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_gain_loss_storeroom;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        action = intent.getStringExtra("action");
        orderId = intent.getStringExtra("orderId");
        setView();
        getData();


    }

    private void getData() {
        SalesRequest.editGainOrLoss(action, orderId, new ResponseCallback<ResponseLossInfor>() {
            @Override
            public void onRequestSuccess(ResponseLossInfor result) {
                if (result != null) {
                    setData(result);
                    datas = result.data.plDetailList;
                    adapter.setData(datas);
                }
                ToastUtils.show(mContext, "成功");
            }

            @Override
            public void onReuquestFailed(ErrorBase error) {
                ToastUtils.show(mContext, error.message);
            }
        });
    }

    private void setView() {
        tvCustomer = (TextView) findViewById(R.id.customer);
        tvPayWay = (TextView) findViewById(R.id.pay_way);
        tvStore = (TextView) findViewById(R.id.store);
        tvTotalPrice = (TextView) findViewById(R.id.total_price);
        tvOrderCodeValue = (TextView) findViewById(R.id.order_code_value);
        tvCreateManValue = (TextView) findViewById(R.id.create_man_value);
        tvOrderTimeValue = (TextView) findViewById(R.id.order_time_value);
        tvRemark = (TextView) findViewById(R.id.remark);
        ahGainLossList = (AutoHeightListView) findViewById(R.id.gain_loss_list);


        adapter = new OrderDetailAdapter(mContext, datas);
        ahGainLossList.setAdapter(adapter);

    }

    private void setData(ResponseLossInfor result) {
        tvCustomer.setText(result.data.profitLoss.customer);
        tvStore.setText(result.data.profitLoss.store);
        tvPayWay.setText(result.data.profitLoss.setAccount);
        tvTotalPrice.setText(String.valueOf(result.data.profitLoss.totalMoney));
        tvOrderCodeValue.setText(result.data.profitLoss.billNo);
        tvOrderTimeValue.setText(result.data.profitLoss.createTime);
        tvRemark.setText(result.data.profitLoss.memo);
        tvCreateManValue.setText(result.data.profitLoss.account);
    }

}

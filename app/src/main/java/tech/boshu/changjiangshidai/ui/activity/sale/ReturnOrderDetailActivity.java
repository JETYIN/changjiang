package tech.boshu.changjiangshidai.ui.activity.sale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.ReturnProductAdapter;
import tech.boshu.changjiangshidai.bean.api.SalesRequest;
import tech.boshu.changjiangshidai.bean.mode.OrderDetailDto;
import tech.boshu.changjiangshidai.bean.mode.ResponseSaleOrderInfor;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;
import tech.boshu.changjiangshidai.libs.utils.Utils;
import tech.boshu.changjiangshidai.libs.view.AutoHeightListView;
import tech.boshu.changjiangshidai.utils.DateUtils;

/**
 * Created by Administrator on 2016/1/8.
 */
public class ReturnOrderDetailActivity extends BaseActivity {
    private AutoHeightListView alReturnProduct;
    private ReturnProductAdapter adapter;
    private List<OrderDetailDto> datas = new ArrayList<>();
    private ImageButton ibAddReturnOrder;
    private String orderId;
    private TextView tvCustomer;
    private TextView tvWarehouse;
    private TextView tvPayWay;
    private TextView tvPayableValue;
    private TextView tvActuallyPaidValue;
    private TextView tvOrderid;
    private TextView tvOrderCreatePeople;
    private TextView tvOrderCreateTime;
    private TextView tvRemark;
    private int type;
    private ImageView ivStatusIcon;

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_return_order;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
        type = intent.getIntExtra("type", -1);

        setView();
        getData();
    }

    public void setView() {
        alReturnProduct = (AutoHeightListView) findViewById(R.id.return_product);
        ibAddReturnOrder = (ImageButton) findViewById(R.id.add_return_order);
        tvCustomer = (TextView) findViewById(R.id.customer);
        tvPayWay = (TextView) findViewById(R.id.pay_way);
        tvWarehouse = (TextView) findViewById(R.id.warehouse);
        tvActuallyPaidValue = (TextView) findViewById(R.id.actually_paid_value);
        tvPayableValue = (TextView) findViewById(R.id.payable_value);
        tvOrderid = (TextView) findViewById(R.id.orderid);
        tvOrderCreatePeople = (TextView) findViewById(R.id.order_create_people);
        tvOrderCreateTime = (TextView) findViewById(R.id.order_create_time);
        tvRemark = (TextView) findViewById(R.id.remark);
        ivStatusIcon = (ImageView) findViewById(R.id.status_icon);
        if (type == 1) {
            ivStatusIcon.setVisibility(View.GONE);
        } else if (type == 2) {
            ivStatusIcon.setVisibility(View.VISIBLE);
        }


        adapter = new ReturnProductAdapter(mContext, datas);
        alReturnProduct.setAdapter(adapter);
        ibAddReturnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReturnOrderDetailActivity.this, AddReturnOrderActivity
                        .class);
                intent.putExtra("action","add");
                startActivity(intent);
                finish();
            }
        });
    }


    private void getData() {
        if (Utils.isNetworkConnected(this)) {
            SalesRequest.queryReturnOrderIfor("edit", orderId, new
                    ResponseCallback<ResponseSaleOrderInfor>() {

                        @Override
                        public void onRequestSuccess(ResponseSaleOrderInfor result) {
                            if (result != null) {
                                datas = result.data.orderDetailDtoList;
                                adapter.setData(datas);
                                setData(result);
                            }
                        }

                        @Override
                        public void onReuquestFailed(ErrorBase error) {
                            ToastUtils.show(mContext, error.message);
                        }
                    });
        }
    }

    private void setData(ResponseSaleOrderInfor result) {
        tvCustomer.setText(result.data.order.customer);
        tvActuallyPaidValue.setText("￥" + result.data.order.getMoney);
        tvWarehouse.setText(result.data.order.store);
        tvPayWay.setText(result.data.order.setAccount);
        tvOrderCreatePeople.setText(result.data.order.account);
        tvOrderCreateTime.setText(DateUtils.DataNormalFormat(result.data.order.createTime));
        tvPayableValue.setText("￥" + result.data.order.totalMoney);
        tvOrderid.setText(result.data.order.id);
        tvRemark.setText(result.data.order.memo);

    }
}

package tech.boshu.changjiangshidai.ui.activity.sale;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.OrderDetailAdapter;
import tech.boshu.changjiangshidai.bean.api.SalesRequest;
import tech.boshu.changjiangshidai.bean.mode.Arrears;
import tech.boshu.changjiangshidai.bean.mode.OrderDetailDto;
import tech.boshu.changjiangshidai.bean.mode.ResponseSaleOrderInfor;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.libs.utils.Utils;
import tech.boshu.changjiangshidai.libs.view.AutoHeightListView;

/**
 * Created by Administrator on 2016/1/7.
 */
public class FinishedOrderDetailActivity extends BaseActivity {
    private ImageButton ibRedact;
    private String orderId;
    private String action = "edit";
    private TextView tvCustomer;
    private TextView tvWarehouse;
    private TextView tvPayWay;
    private TextView tvPayMoneyValue;
    private TextView tvTotalMoneyValue;
    private TextView tvOrderCodeValue;
    private TextView tvTimeValue;
    private TextView tvDeliverWayValue;
    private TextView tvRemark;
    private TextView tvDebtValue;
    private TextView tvAddPeopleValue;
    private TextView tvTotalPrice;
    private TextView tvTotalNumber;
    private ImageButton ibAdd;
    private AutoHeightListView ahOrderList;
    private OrderDetailAdapter orderDetailAdapter;
    private List<OrderDetailDto> datas = new ArrayList<>();
    private int totalnum = 0;
    private int type;
    private ImageView ivStatusIcon;

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_finish_order_detail;

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
        ibRedact = (ImageButton) findViewById(R.id.redact);
        tvCustomer = (TextView) findViewById(R.id.customer);
        tvWarehouse = (TextView) findViewById(R.id.warehouse);
        tvPayWay = (TextView) findViewById(R.id.pay_way);
        tvTotalMoneyValue = (TextView) findViewById(R.id.total_money_value);
        tvPayMoneyValue = (TextView) findViewById(R.id.pay_money_value);
        tvOrderCodeValue = (TextView) findViewById(R.id.order_code_value);
        tvTimeValue = (TextView) findViewById(R.id.time_value);
        tvDeliverWayValue = (TextView) findViewById(R.id.deliver_way_value);
        tvRemark = (TextView) findViewById(R.id.remark);
        tvDebtValue = (TextView) findViewById(R.id.debt_value);
        tvAddPeopleValue = (TextView) findViewById(R.id.add_people_value);
        tvTotalPrice = (TextView) findViewById(R.id.total_price);
        tvTotalNumber = (TextView) findViewById(R.id.total_number);
        ahOrderList = (AutoHeightListView) findViewById(R.id.order_list);
        ibAdd = (ImageButton) findViewById(R.id.add);
        ivStatusIcon = (ImageView) findViewById(R.id.status_icon);
        if (type == 1) {
            ivStatusIcon.setVisibility(View.GONE);
        } else if (type == 2) {
            ivStatusIcon.setVisibility(View.VISIBLE);
        }

        orderDetailAdapter = new OrderDetailAdapter(this, datas);
        ahOrderList.setAdapter(orderDetailAdapter);


        ibRedact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(mContext).inflate(R.layout
                        .dialog_choose, null);
                final AlertDialog dialog = new AlertDialog.Builder(mContext).setView(view)
                        .show();
                Button btnCopySalesOrder = (Button) view.findViewById(R.id.copy_sales_order);
                Button btnCopyReturnOrder = (Button) view.findViewById(R.id.copy_return_order);
                btnCopyReturnOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(FinishedOrderDetailActivity.this,
                                AddReturnOrderActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                btnCopySalesOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(FinishedOrderDetailActivity.this,
                                CopyOrderActivity.class);
                        intent.putExtra("orderId",orderId);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
            }
        });
        ibAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinishedOrderDetailActivity.this,
                        AddSalesOrderActivity.class);
                intent.putExtra("action", "add");
                startActivity(intent);
                onBackPressed();
            }
        });
    }

    private void getData() {
        if (Utils.isNetworkConnected(this)) {
            final Dialog mDialog = new Dialog(this, R.style.DialogStyle);
            mDialog.show();
            SalesRequest.querySalesOrderIfor(action, orderId, new
                    ResponseCallback<ResponseSaleOrderInfor>() {

                        @Override
                        public void onRequestSuccess(ResponseSaleOrderInfor result) {
                            if (result != null) {
                                setData(result);
                                datas = result.data.orderDetailDtoList;
                                orderDetailAdapter.setData(datas);
                            }
                            mDialog.dismiss();
                        }

                        @Override
                        public void onReuquestFailed(ErrorBase error) {
                            mDialog.dismiss();
                        }
                    });
        }
    }

    private void setData(ResponseSaleOrderInfor result) {

        if (result.data.order != null) {
            tvCustomer.setText(result.data.order.customer);
            tvWarehouse.setText(result.data.order.store);
            tvPayWay.setText(result.data.order.setAccount);
            tvTotalMoneyValue.setText("￥" + String.valueOf(result.data.order.totalMoney));
            tvOrderCodeValue.setText(orderId);
            tvPayMoneyValue.setText("￥" + String.valueOf(result.data.order.payMoney));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (result.data.order.createTime != null) {
                Date d1 = new Date(Long.valueOf(result.data.order.createTime));
                tvTimeValue.setText(format.format(d1));
            }
            tvRemark.setText(result.data.order.memo);
            tvDeliverWayValue.setText(result.data.order.sendtypeName);
            tvAddPeopleValue.setText(result.data.order.account);
            tvTotalPrice.setText("￥" + String.valueOf(result.data.order.totalMoney));

            for (int i = 0; i < result.data.orderDetailDtoList.size(); i++) {
                totalnum = totalnum + result.data.orderDetailDtoList.get(i).num;
            }
            tvTotalNumber.setText("(" + String.valueOf(totalnum) + ")");

            getArrears(result.data.order.customerId);
        }

    }

    private void getArrears(String customerId) {
        if (Utils.isNetworkConnected(this)) {
            final Dialog mDialog = new Dialog(this, R.style.DialogStyle);
            mDialog.show();
            SalesRequest.queruArrears(customerId, new ResponseCallback<Arrears>() {
                @Override
                public void onRequestSuccess(Arrears result) {
                    tvDebtValue.setText(String.valueOf(result.data));
                    mDialog.dismiss();
                }

                @Override
                public void onReuquestFailed(ErrorBase error) {
                    mDialog.dismiss();
                }
            });
        }
    }
}

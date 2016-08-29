package tech.boshu.changjiangshidai.ui.activity.stockinfo.finishpurchaseorderdetail;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.FinishedPurchaseOrderDetailAdapter;
import tech.boshu.changjiangshidai.bean.api.SalesRequest;
import tech.boshu.changjiangshidai.bean.api.ServerRequest;
import tech.boshu.changjiangshidai.bean.mode.Arrears;
import tech.boshu.changjiangshidai.bean.mode.OrderDetailDto;
import tech.boshu.changjiangshidai.bean.mode.PrOrder;
import tech.boshu.changjiangshidai.constants.ParameterConstants;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.bean.ResponseBase;
import tech.boshu.changjiangshidai.libs.net.GsonRequest;
import tech.boshu.changjiangshidai.libs.net.HttpUtils;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.libs.utils.Utils;
import tech.boshu.changjiangshidai.libs.view.AutoHeightListView;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchaseorder.AddPurchaseOrderActivity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchaseorder.AddPurchaseOrderParam;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchaseorder.AddPurchaseOrderRequest;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchaseorder.ResponseAddPurchaseQureyBean;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.checkdetail.CheckDetailActivity;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)FinishPurchaseOrderDetailActivity.java
 */


public class FinishPurchaseOrderDetailActivity extends BaseActivity implements IFinishPurchaseOrderDetailView {

    private RelativeLayout title_topRelativeLayout;
    private ImageButton backImageButton;
    private ImageButton addImageButton;
    private ImageButton redactImageButton;
    private ImageView icon_customerImageView;
    private TextView supplierTextView;
    private ImageView icon_warehouseImageView;
    private TextView stockTextView;
    private ImageView icon_pay_wayImageView;
    private TextView pay_accountTextView;
    private ImageView icon_rightImageView;
    private TextView total_priceTextView;
    private TextView total_countTextView;
    private AutoHeightListView listAutoHeightListView;
    private TextView dueTextView;
    private TextView due_valueTextView;
    private TextView disbursementsTextView;
    private TextView disbursements_valueTextView;
    private TextView debtTextView;
    private TextView debt_valueTextView;
    private TextView order_codeTextView;
    private TextView order_valueTextView;
    private TextView add_peopleTextView;
    private TextView maker_valueTextView;
    private TextView timeTextView;
    private TextView time_valueTextView;
    private ImageView icon_right2ImageView;
    private TextView remarkTextView;
    private ImageView ivStatusIcon;


    private AddPurchaseOrderParam addPurchaseOrderParam = new AddPurchaseOrderParam();

    private FinishedPurchaseOrderDetailAdapter adapter;
    private ArrayList<OrderDetailDto> datas = new ArrayList<>();
    private PrOrder prOrder;

    @Override
    public void setView() {

        setText(R.id.supplier, prOrder.provider);
        setText(R.id.stock, prOrder.store);
        setText(R.id.pay_account, prOrder.setAccount);
        setText(R.id.total_price, "￥" + prOrder.totalMoney);
        setText(R.id.total_count, "合计：" + prOrder.totalNum);
        setText(R.id.due_value, "￥" + prOrder.totalMoney);
        setText(R.id.disbursements_value, "￥" + prOrder.payMoney);
        setText(R.id.order_value, prOrder.id);
        setText(R.id.maker_value, prOrder.createAccount);
        setText(R.id.time_value, prOrder.createTime);
        setText(R.id.remark, prOrder.memo);
        if (prOrder.status == 3) {
            ivStatusIcon.setVisibility(View.VISIBLE);
        } else {
            ivStatusIcon.setVisibility(View.GONE);
        }
        adapter = new FinishedPurchaseOrderDetailAdapter(mContext, datas);
        listAutoHeightListView.setAdapter(adapter);
        listAutoHeightListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mContext, CheckDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    //获取客户尚欠款
    private void getArrears(String customerId) {
        if (Utils.isNetworkConnected(this)) {
            showDialog();
            SalesRequest.queruArrears(customerId, new ResponseCallback<Arrears>() {
                @Override
                public void onRequestSuccess(Arrears result) {
                    setText(R.id.debt_value, "￥" + result.data);
                    hideDialog();
                }

                @Override
                public void onReuquestFailed(ErrorBase error) {
                    showToast(error.message);
                    hideDialog();
                }
            });
        }
    }

    private void getDatas() {
        showDialog();
        AddPurchaseOrderRequest.queryPurchaseOrderInfor(addPurchaseOrderParam, new ResponseCallback<ResponseAddPurchaseQureyBean>() {
            @Override
            public void onRequestSuccess(ResponseAddPurchaseQureyBean result) {
                hideDialog();
                prOrder = result.data.order;
                getArrears(String.valueOf(prOrder.settlement));
                datas = result.data.orderDetailDtoList;
                setView();
            }

            @Override
            public void onReuquestFailed(ErrorBase error) {
                hideDialog();
                showToast(error.message);
            }
        });
    }

    @Override
    public void initView() {
        title_topRelativeLayout = (RelativeLayout) findViewById(R.id.title_top);
        backImageButton = (ImageButton) findViewById(R.id.back);
        addImageButton = (ImageButton) findViewById(R.id.add);
        redactImageButton = (ImageButton) findViewById(R.id.redact);
        icon_customerImageView = (ImageView) findViewById(R.id.icon_customer);
        supplierTextView = (TextView) findViewById(R.id.supplier);
        icon_warehouseImageView = (ImageView) findViewById(R.id.icon_warehouse);
        stockTextView = (TextView) findViewById(R.id.stock);
        icon_pay_wayImageView = (ImageView) findViewById(R.id.icon_pay_way);
        pay_accountTextView = (TextView) findViewById(R.id.pay_account);
        icon_rightImageView = (ImageView) findViewById(R.id.icon_right);
        total_priceTextView = (TextView) findViewById(R.id.total_price);
        total_countTextView = (TextView) findViewById(R.id.total_count);
        listAutoHeightListView = (AutoHeightListView) findViewById(R.id.list);
        dueTextView = (TextView) findViewById(R.id.due);
        due_valueTextView = (TextView) findViewById(R.id.due_value);
        disbursementsTextView = (TextView) findViewById(R.id.disbursements);
        disbursements_valueTextView = (TextView) findViewById(R.id.disbursements_value);
        debtTextView = (TextView) findViewById(R.id.debt);
        debt_valueTextView = (TextView) findViewById(R.id.debt_value);
        order_codeTextView = (TextView) findViewById(R.id.order_code);
        order_valueTextView = (TextView) findViewById(R.id.order_value);
        add_peopleTextView = (TextView) findViewById(R.id.add_people);
        maker_valueTextView = (TextView) findViewById(R.id.maker_value);
        timeTextView = (TextView) findViewById(R.id.time);
        time_valueTextView = (TextView) findViewById(R.id.time_value);
        icon_right2ImageView = (ImageView) findViewById(R.id.icon_right2);
        remarkTextView = (TextView) findViewById(R.id.remark);
        ivStatusIcon = (ImageView) findViewById(R.id.status_icon);

    }

    private FinishPurchaseOrderDetailPresenter finishPurchaseOrderDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finishPurchaseOrderDetailPresenter.initView();
        initTitle("采购单");
        finishPurchaseOrderDetailPresenter.setText(R.id.right, "");
        setOnclickListener(R.id.redact, new View.OnClickListener() {
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
                        Intent intent = new Intent(FinishPurchaseOrderDetailActivity.this,
                                AddPurchaseOrderActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                btnCopySalesOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        showDialog();
                        final HashMap<String, String> params = new HashMap<>();
                        params.put("orderId",prOrder.id);
                        GsonRequest<ResponseFinishPurchaseOrderDetail> request = new GsonRequest<>(Request.Method.POST,
                                ServerRequest.apiHost + "/pr_copy.action", ResponseFinishPurchaseOrderDetail.class, null, params,
                                callback, callback);
                        HttpUtils.getInstance().request(FinishPurchaseOrderDetailActivity.class.getSimpleName(), request);

                    }
                });
            }
        });

        setOnclickListener(R.id.add, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinishPurchaseOrderDetailActivity.this,
                        AddPurchaseOrderActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        addPurchaseOrderParam.order.id = getIntent().getStringExtra(ParameterConstants.PARAM_ORDER_ID);
        addPurchaseOrderParam.action = ParameterConstants.ACTION_TYPE_EDIT;
        getDatas();
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_finish_purchase_order_detail;
    }

    @Override
    protected void setPresenter() {
        finishPurchaseOrderDetailPresenter = (FinishPurchaseOrderDetailPresenter) (presenter =
                new FinishPurchaseOrderDetailPresenter(FinishPurchaseOrderDetailActivity.this));
    }

    private ResponseCallback<ResponseFinishPurchaseOrderDetail> callback = new ResponseCallback<ResponseFinishPurchaseOrderDetail>() {
        @Override
        public void onRequestSuccess(ResponseFinishPurchaseOrderDetail result) {
            hideDialog();
            Intent intent = new Intent(FinishPurchaseOrderDetailActivity.this,
                    AddPurchaseOrderActivity.class);
            intent.putExtra(ParameterConstants.PRAMA_COMMON_VALUE, new Gson().toJson(result.data));
            intent.putExtra(ParameterConstants.PARAM_ACTION_TYPE, ParameterConstants.ACTION_TYPE_COPY);
            startActivity(intent);
            finish();
        }

        @Override
        public void onReuquestFailed(ErrorBase error) {
            showToast(error.message);
            hideDialog();
        }
    };
}
package tech.boshu.changjiangshidai.ui.activity.sale;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.AccountListAdapter;
import tech.boshu.changjiangshidai.adapter.CustomerListAdapter;
import tech.boshu.changjiangshidai.adapter.DeliveryModeAdapter;
import tech.boshu.changjiangshidai.adapter.ProductAdapter;
import tech.boshu.changjiangshidai.adapter.WareHouseListAdapter;
import tech.boshu.changjiangshidai.bean.api.SalesRequest;
import tech.boshu.changjiangshidai.bean.mode.Account;
import tech.boshu.changjiangshidai.bean.mode.Arrears;
import tech.boshu.changjiangshidai.bean.mode.DeliveryMode;
import tech.boshu.changjiangshidai.bean.mode.OrderDetailDto;
import tech.boshu.changjiangshidai.bean.mode.PrOrder;
import tech.boshu.changjiangshidai.bean.mode.ResponseSaleOrderInfor;
import tech.boshu.changjiangshidai.bean.mode.Store;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.bean.ResponseBase;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;
import tech.boshu.changjiangshidai.libs.utils.Utils;
import tech.boshu.changjiangshidai.libs.view.AutoHeightListView;
import tech.boshu.changjiangshidai.ui.activity.common.search.CustomerListEntity;
import tech.boshu.changjiangshidai.utils.DateUtils;

/**
 * Created by Administrator on 2016/1/7.
 */
public class OrderDraftActivity extends BaseActivity implements ProductAdapter
        .ProductAdapterLisListener {
    private AutoHeightListView lvProductList;
    private ProductAdapter adapter;
    private List<OrderDetailDto> datas = new ArrayList<>();
    private String orderId;
    private Button btnCustomervalue;
    private Button btnWarehouseValue;
    private Button btnPayWayValue;
    private Button btnDiscount;
    private TextView tvAccounts;
    private TextView tvOrderCodeValue;
    private TextView tvCreateManValue;
    private Button btnCreateTimeValue;
    private Button btnDeliverWayValue;
    private EditText etRemark;
    private CustomerListAdapter customerListAdapter;
    private WareHouseListAdapter wareHouseListAdapter;
    private AccountListAdapter accountListAdapter;
    private DeliveryModeAdapter deliveryModeAdapter;
    private List<CustomerListEntity> customerListEntities = new ArrayList<>();
    private List<Store> stores = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private List<DeliveryMode> deliveryModes = new ArrayList<>();
    private PrOrder prOrder = new PrOrder();
    private TextView tvDebtValue;
    private int storeId;
    private Double totalprice;
    private TextView tvTotalPriceValue;
    private Double orderRate = 100.00;
    private ImageButton ibAdd;
    private Gson gson = new Gson();
    private RelativeLayout rtlDiscount;
    private Button btnSell;
    private Button btnDraft;
    private EditText etGetMoney;
    private String customerId;

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_order_draft;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
        setView();
        getData();
    }

    public void setView() {
        lvProductList = (AutoHeightListView) findViewById(R.id.product_list);
        btnCustomervalue = (Button) findViewById(R.id.customer_value);
        btnWarehouseValue = (Button) findViewById(R.id.warehouse_value);
        btnPayWayValue = (Button) findViewById(R.id.pay_way_value);
        btnDiscount = (Button) findViewById(R.id.discount);
        tvAccounts = (TextView) findViewById(R.id.accounts);
        tvOrderCodeValue = (TextView) findViewById(R.id.order_code_value);
        tvCreateManValue = (TextView) findViewById(R.id.create_man_value);
        btnCreateTimeValue = (Button) findViewById(R.id.create_time_value);
        btnDeliverWayValue = (Button) findViewById(R.id.deliver_way_value);
        etRemark = (EditText) findViewById(R.id.remark);
        tvDebtValue = (TextView) findViewById(R.id.debt_value);
        tvTotalPriceValue = (TextView) findViewById(R.id.total_price_value);
        ibAdd = (ImageButton) findViewById(R.id.add);
        rtlDiscount = (RelativeLayout) findViewById(R.id.rtldiscount);

        etGetMoney = (EditText) findViewById(R.id.getmoney);
        btnDraft = (Button) findViewById(R.id.draft);
        btnSell = (Button) findViewById(R.id.sell);

        adapter = new ProductAdapter(mContext, datas, this);
        lvProductList.setAdapter(adapter);

        btnCustomervalue.setOnClickListener(this);
        btnWarehouseValue.setOnClickListener(this);
        btnPayWayValue.setOnClickListener(this);
        btnCreateTimeValue.setOnClickListener(this);
        btnDeliverWayValue.setOnClickListener(this);
        btnDiscount.setOnClickListener(this);
        ibAdd.setOnClickListener(this);
        btnSell.setOnClickListener(this);
        btnDraft.setOnClickListener(this);
    }


    @Override
    public void amendProduct(final int position) {
        View v = LayoutInflater.from(this).inflate(R.layout
                .dialog_amend_product, null);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(v)
                .show();
        final EditText unit_price_value = (EditText) v.findViewById(R.id.unit_price_value);
        final EditText discount_value = (EditText) v.findViewById(R.id.discount_value);
        final EditText discount_price_value = (EditText) v.findViewById(R.id.discount_price_value);
        Button cancel = (Button) v.findViewById(R.id.cancel);
        Button confirm = (Button) v.findViewById(R.id.confirm);
        unit_price_value.setText(String.valueOf(datas.get(position).price));
        discount_value.setText(String.valueOf(datas.get(position).discount * 100));
        discount_price_value.setText(String.valueOf(datas.get(position).price *
                datas.get(position).discount));
        final TextWatcher unit_price_valueTw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1,
                                          int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int
                    i2) {
                String curStr = charSequence.toString();
                if (TextUtils.isEmpty(curStr)) {
                    curStr = "0";
                    unit_price_value.setText("0");
                }
                discount_price_value.setText(String.valueOf(Float.valueOf(String.valueOf
                        (curStr)) * datas.get(position).discount));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        final TextWatcher discount_valueTw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1,
                                          int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int
                    i2) {
                String curStr = charSequence.toString();
                if (TextUtils.isEmpty(curStr)) {
                    curStr = "0";
                    discount_value.setText("0");
                    datas.get(position).discount = 0.00;
                }
                datas.get(position).discount = Double.valueOf(curStr) / 100;
                discount_price_value.setText(String.valueOf(Float.valueOf(unit_price_value
                        .getText().toString()) * datas.get(position).discount));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        final TextWatcher discount_price_valueTw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1,
                                          int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int
                    i2) {
                String curStr = charSequence.toString();
                if (TextUtils.isEmpty(curStr)) {
                    curStr = "0";
                    discount_price_value.setText("0");
                }
                unit_price_value.setText(String.valueOf(Float.valueOf(curStr) / datas.get
                        (position).discount));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        discount_price_value.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    discount_price_value.addTextChangedListener(discount_price_valueTw);
                    unit_price_value.removeTextChangedListener(unit_price_valueTw);
                    discount_value.removeTextChangedListener(discount_valueTw);
                }
            }
        });

        unit_price_value.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    unit_price_value.addTextChangedListener(unit_price_valueTw);
                    discount_price_value.removeTextChangedListener(discount_price_valueTw);
                    discount_value.removeTextChangedListener(discount_valueTw);
                }
            }
        });
        discount_value.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    discount_value.addTextChangedListener(discount_valueTw);
                    unit_price_value.removeTextChangedListener(unit_price_valueTw);
                    discount_price_value.removeTextChangedListener(discount_price_valueTw);
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datas.get(position).price = Double.valueOf(discount_price_value.getText
                        ().toString());
                adapter.setData(datas);
                getotalPriceValue();
                dialog.dismiss();

            }
        });

    }

    @Override
    public void deleteProduct(int position) {
        datas.remove(position);
        adapter.setData(datas);
        if (datas != null && datas.size() > 0) {
            rtlDiscount.setVisibility(View.VISIBLE);
        } else {
            rtlDiscount.setVisibility(View.GONE);
        }
        getotalPriceValue();
    }

    private void getData() {
        SalesRequest.querySalesOrderIfor("edit", orderId, new
                ResponseCallback<ResponseSaleOrderInfor>() {

                    @Override
                    public void onRequestSuccess(ResponseSaleOrderInfor result) {
                        if (result != null) {
                            datas = result.data.orderDetailDtoList;
                            adapter.setData(datas);
                            customerListEntities = result.data.customerList;
                            stores = result.data.stroeList;
                            accounts = result.data.setAccountList;
                            deliveryModes = result.data.sendTypeList;
                            prOrder = result.data.order;
                            setData(result);
                        }
                    }

                    @Override
                    public void onReuquestFailed(ErrorBase error) {
                        ToastUtils.show(mContext, error.message);
                    }
                });
    }

    private void setData(ResponseSaleOrderInfor result) {
        btnCustomervalue.setText(result.data.order.customer);
        btnWarehouseValue.setText(result.data.order.store);
        btnPayWayValue.setText(result.data.order.setAccount);
        if (result.data.orderDetailDtoList != null && result.data.orderDetailDtoList.size() > 0) {
            rtlDiscount.setVisibility(View.VISIBLE);
            btnDiscount.setText("整单折扣(" + String.valueOf(result.data.order.discount * 100) + "%)");
        } else {
            btnDiscount.setVisibility(View.GONE);
        }

        tvAccounts.setText(String.valueOf(result.data.order.totalMoney));
        tvOrderCodeValue.setText(result.data.order.id);
        tvCreateManValue.setText(result.data.order.account);
        btnCreateTimeValue.setText(DateUtils.DataNormalFormat(result.data.order.createTime));
        btnDeliverWayValue.setText(result.data.order.sendtypeName);
        etRemark.setText(result.data.order.memo);
        customerId = result.data.order.customerId;
        storeId = result.data.order.storeId;
        etGetMoney.setText(String.valueOf(result.data.order.getMoney));
        getArrears(customerId);
        getotalPriceValue();


    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.customer_value) {
            View v = LayoutInflater.from(this).inflate(R.layout
                    .dialog_listview, null);
            final AlertDialog dialog = new AlertDialog.Builder(this).setView(v)
                    .show();
            ListView listView = (ListView) v.findViewById(R.id.delivery_detail_list);
            customerListAdapter = new CustomerListAdapter(this, customerListEntities);
            listView.setAdapter(customerListAdapter);
            customerListAdapter.setData(customerListEntities);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    dialog.dismiss();
                    btnCustomervalue.setText(customerListEntities.get(i).name);
                    String customerId = String.valueOf(customerListEntities.get(i).id);
                    prOrder.customerId = String.valueOf(customerListEntities.get(i).id);
                    getArrears(customerId);

                }
            });
        } else if (id == R.id.warehouse_value) {
            View v = LayoutInflater.from(this).inflate(R.layout
                    .dialog_listview, null);
            final AlertDialog dialog = new AlertDialog.Builder(this).setView(v)
                    .show();
            ListView listView = (ListView) v.findViewById(R.id.delivery_detail_list);
            wareHouseListAdapter = new WareHouseListAdapter(this, stores);
            listView.setAdapter(wareHouseListAdapter);
            wareHouseListAdapter.setData(stores);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    dialog.dismiss();
                    btnWarehouseValue.setText(stores.get(i).name);
                    storeId = stores.get(i).id;
                    prOrder.storeId = stores.get(i).id;
                }
            });
        } else if (id == R.id.pay_way_value) {
            View v = LayoutInflater.from(this).inflate(R.layout
                    .dialog_listview, null);
            final AlertDialog dialog = new AlertDialog.Builder(this).setView(v)
                    .show();
            ListView listView = (ListView) v.findViewById(R.id.delivery_detail_list);
            accountListAdapter = new AccountListAdapter(this, accounts);
            listView.setAdapter(accountListAdapter);
            accountListAdapter.setData(accounts);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    dialog.dismiss();
                    btnPayWayValue.setText(accounts.get(i).name);
                    prOrder.settlement = accounts.get(i).id;
                }
            });
        } else if (id == R.id.create_time_value) {
            datePick();
        } else if (id == R.id.deliver_way_value) {
            View v = LayoutInflater.from(this).inflate(R.layout
                    .dialog_listview, null);
            final AlertDialog dialog = new AlertDialog.Builder(this).setView(v)
                    .show();
            ListView listView = (ListView) v.findViewById(R.id.delivery_detail_list);
            deliveryModeAdapter = new DeliveryModeAdapter(this, deliveryModes);
            listView.setAdapter(deliveryModeAdapter);
            deliveryModeAdapter.setData(deliveryModes);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    dialog.dismiss();
                    btnDeliverWayValue.setText(deliveryModes.get(i).codeName);
                }
            });
        } else if (id == R.id.discount) {
            View v = LayoutInflater.from(this).inflate(R.layout
                    .dialog_discount, null);
            final AlertDialog dialog = new AlertDialog.Builder(this).setView(v)
                    .show();
            final EditText discount_value = (EditText) v.findViewById(R.id.discount_value);
            Button cancel = (Button) v.findViewById(R.id.cancel);
            Button confirm = (Button) v.findViewById(R.id.confirm);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String discount = discount_value.getText().toString();
                    if (TextUtils.isEmpty(discount)) {
                        dialog.dismiss();
                    } else {
                        btnDiscount.setText("整单折扣（" + discount + "%）");
                        orderRate = Double.valueOf(discount);
                        for (int i = 0; i < datas.size(); i++) {
                            datas.get(i).price = datas.get(i).price * orderRate
                                    / 100;
                        }
                        adapter.setData(datas);
                        getotalPriceValue();
                        dialog.dismiss();
                    }
                }
            });
        } else if (id == R.id.add) {
            if (!TextUtils.isEmpty(btnWarehouseValue.getText().toString())) {
                String orderData = gson.toJson(datas);
                Intent intent = new Intent(this, AddProductActivity.class);
                intent.putExtra("storeId", storeId);
                intent.putExtra("orderData", orderData);
                startActivityForResult(intent, 1000);
            } else {
                ToastUtils.show(mContext, "请选择仓库");
            }
        } else if (id == R.id.sell) {
            sell();
            Intent intent = new Intent(this, SalesHistoryActivity.class);
            setResult(RESULT_OK, intent);
            finish();

        } else if (id == R.id.draft) {
            setDraft();
            Intent intent = new Intent(this, SalesHistoryActivity.class);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    //获取客户尚欠款
    private void getArrears(String customerId) {
        if (Utils.isNetworkConnected(this)) {
            final Dialog mDialog = new Dialog(this, R.style.DialogStyle);
            mDialog.show();
            SalesRequest.queruArrears(customerId, new ResponseCallback<Arrears>() {
                @Override
                public void onRequestSuccess(Arrears result) {
                    setOrderInfor(result);
                    mDialog.dismiss();
                }

                @Override
                public void onReuquestFailed(ErrorBase error) {
                    mDialog.dismiss();
                }
            });
        }
    }

    private void setOrderInfor(Arrears result) {
        tvDebtValue.setText(String.valueOf(result.data));
    }

    //时间选择器
    private void datePick() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                btnCreateTimeValue.setText(new StringBuilder()
                        .append(year)
                        .append("-")
                        .append((month + 1) < 10 ? "0" + (month + 1)
                                : (month + 1)).append("-")
                        .append((day < 10) ? "0" + day : day));
            }
        }, year, monthOfYear, dayOfMonth).show();
    }

    //更新总价
    private void getotalPriceValue() {
        totalprice = 0.00;
        if (datas != null && datas.size() > 0) {
            for (int j = 0; j < datas.size(); j++) {
                double money = datas.get(j).num * datas.get(j).price;
                totalprice = totalprice + money;
            }
        }
        tvTotalPriceValue.setText("￥" + String.valueOf(totalprice));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1000) {
                String j = data.getStringExtra("orderDetailDto");
                datas = gson.fromJson(j, new TypeToken<ArrayList<OrderDetailDto>>() {
                }.getType());
                adapter.setData(datas);
                getotalPriceValue();
                if (datas.size() > 0) {
                    rtlDiscount.setVisibility(View.VISIBLE);
                } else {
                    rtlDiscount.setVisibility(View.GONE);
                }
            }
        }
    }

    private void sell() {
        prOrder.totalNum = 0;
        prOrder.discount = orderRate;
        if (TextUtils.isEmpty(etGetMoney.getText().toString())) {
            prOrder.getMoney = 0.00;
        } else {
            prOrder.getMoney = Double.valueOf(etGetMoney.getText().toString());
        }
        prOrder.totalMoney = totalprice;

        for (int i = 0; i < datas.size(); i++) {
            prOrder.totalNum = prOrder.totalNum + datas.get(i).num;
        }
        prOrder.createTime = btnCreateTimeValue.getText().toString();
        prOrder.memo = etRemark.getText().toString();
        prOrder.id = orderId;
        if (Utils.isNetworkConnected(this)) {
            final Dialog mDialog = new Dialog(this, R.style.DialogStyle);
            mDialog.show();
            SalesRequest.sellOrder("2222", "1", "add", prOrder, datas, new
                    ResponseCallback<ResponseBase>() {


                        @Override
                        public void onRequestSuccess(ResponseBase result) {
                            mDialog.dismiss();
                            ToastUtils.show(mContext, "成功");
                        }

                        @Override
                        public void onReuquestFailed(ErrorBase error) {
                            mDialog.dismiss();
                            ToastUtils.show(mContext, error.message);
                        }
                    });

        }
    }

    private void setDraft() {
        prOrder.totalNum = 0;
        prOrder.discount = orderRate / 100;
        if (TextUtils.isEmpty(etGetMoney.getText().toString())) {
            prOrder.getMoney = 0.00;
        } else {
            prOrder.getMoney = Double.valueOf(etGetMoney.getText().toString());
        }
        prOrder.totalMoney = totalprice;

        for (int i = 0; i < datas.size(); i++) {
            prOrder.totalNum = prOrder.totalNum + datas.get(i).num;
        }
        prOrder.createTime = btnCreateTimeValue.getText().toString();
        prOrder.memo = etRemark.getText().toString();
        prOrder.id = orderId;
        if (Utils.isNetworkConnected(this)) {
            final Dialog mDialog = new Dialog(this, R.style.DialogStyle);
            mDialog.show();
            SalesRequest.saveEditDraft("2222", "1", prOrder, datas, new
                    ResponseCallback<ResponseBase>() {


                        @Override
                        public void onRequestSuccess(ResponseBase result) {
                            mDialog.dismiss();
                            ToastUtils.show(mContext, "成功");
                        }

                        @Override
                        public void onReuquestFailed(ErrorBase error) {
                            mDialog.dismiss();
                            ToastUtils.show(mContext, error.message);
                        }
                    });
        }
    }
}

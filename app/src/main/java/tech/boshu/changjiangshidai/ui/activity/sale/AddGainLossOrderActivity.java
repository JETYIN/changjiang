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
import tech.boshu.changjiangshidai.adapter.ProductAdapter;
import tech.boshu.changjiangshidai.adapter.WareHouseListAdapter;
import tech.boshu.changjiangshidai.bean.api.SalesRequest;
import tech.boshu.changjiangshidai.bean.mode.Account;
import tech.boshu.changjiangshidai.bean.mode.OrderDetailDto;
import tech.boshu.changjiangshidai.bean.mode.PrOrder;
import tech.boshu.changjiangshidai.bean.mode.ResponseLossInfor;
import tech.boshu.changjiangshidai.bean.mode.Store;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.bean.ResponseBase;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;
import tech.boshu.changjiangshidai.libs.utils.Utils;
import tech.boshu.changjiangshidai.libs.view.AutoHeightListView;
import tech.boshu.changjiangshidai.ui.activity.common.search.CustomerListEntity;

/**
 * Created by Administrator on 2016/1/8.
 */
public class AddGainLossOrderActivity extends BaseActivity implements ProductAdapter
        .ProductAdapterLisListener {
    private String action;
    private Button btnCustomerNameValue;
    private Button btnWareHouseValue;
    private Button btnPayWayValue;
    private CustomerListAdapter customerListAdapter;
    private WareHouseListAdapter wareHouseListAdapter;
    private AccountListAdapter accountListAdapter;
    private List<CustomerListEntity> customerListEntities = new ArrayList<>();
    private List<Store> stores = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private PrOrder prOrder = new PrOrder();
    private Button btnOrderTimeValue;
    private ImageButton ibAddProduct;
    private List<OrderDetailDto> datas = new ArrayList<>();
    private Gson gson = new Gson();
    private ProductAdapter adapter;
    private AutoHeightListView ahChooseProductList;
    private double totalprice;
    private int totalNum;
    private TextView tvTotalPriceValue;
    private RelativeLayout rltDiscount;
    private Button btnEditDiscount;
    private double orderRate = 100;
    private Button btnSell;
    private Button btnDraft;
    private EditText etRemarkValue;

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_add_gain_loss_order;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        action = intent.getStringExtra("action");
        setView();
        getData();
    }

    private void getData() {
        SalesRequest.editGainOrLoss(action, "", new ResponseCallback<ResponseLossInfor>() {
            @Override
            public void onRequestSuccess(ResponseLossInfor result) {
                if (result != null) {
                    customerListEntities = result.data.customerList;
                    stores = result.data.stroeList;
                    accounts = result.data.setAccountList;
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
        btnCustomerNameValue = (Button) findViewById(R.id.customernamevalue);
        btnWareHouseValue = (Button) findViewById(R.id.warehousevalue);
        btnPayWayValue = (Button) findViewById(R.id.pay_way_value);
        btnOrderTimeValue = (Button) findViewById(R.id.order_time_value);
        ibAddProduct = (ImageButton) findViewById(R.id.add_product);
        ahChooseProductList = (AutoHeightListView) findViewById(R.id.choose_product_list);
        tvTotalPriceValue = (TextView) findViewById(R.id.total_price_value);
        rltDiscount = (RelativeLayout) findViewById(R.id.discount);
        btnEditDiscount = (Button) findViewById(R.id.edit_discount);
        btnSell = (Button) findViewById(R.id.sell);
        btnDraft = (Button) findViewById(R.id.draft);
        etRemarkValue = (EditText) findViewById(R.id.remark_value);


        adapter = new ProductAdapter(this, datas, this);
        ahChooseProductList.setAdapter(adapter);

        ibAddProduct.setOnClickListener(this);
        btnCustomerNameValue.setOnClickListener(this);
        btnWareHouseValue.setOnClickListener(this);
        btnPayWayValue.setOnClickListener(this);
        btnOrderTimeValue.setOnClickListener(this);
        btnEditDiscount.setOnClickListener(this);
        btnSell.setOnClickListener(this);
        btnDraft.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.customernamevalue) {
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
                    btnCustomerNameValue.setText(customerListEntities.get(i).name);
                    String customerId = String.valueOf(customerListEntities.get(i).id);
                    prOrder.customerId = customerId;

                }
            });

        } else if (id == R.id.warehousevalue) {
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
                    btnWareHouseValue.setText(stores.get(i).name);
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
        } else if (id == R.id.order_time_value) {
            datePick(btnOrderTimeValue);
        } else if (id == R.id.add_product) {
            if (!TextUtils.isEmpty(btnWareHouseValue.getText().toString())) {
                String orderData = gson.toJson(datas);
                Intent intent = new Intent(this, AddProductActivity.class);
                intent.putExtra("storeId", prOrder.storeId);
                intent.putExtra("orderData", orderData);
                startActivityForResult(intent, 10);
            } else {
                ToastUtils.show(mContext, "请选择仓库");
            }
        } else if (id == R.id.edit_discount) {
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
                        btnEditDiscount.setText("整单折扣（" + discount + "%）");
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

        } else if (id == R.id.sell) {
            if (datas.size() < 0) {
                ToastUtils.show(mContext, "请选择商品");
            } else {
                sell();
                Intent intent = new Intent(this, SalesHistoryActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            }

        } else if (id == R.id.draft) {
            if (datas.size() < 0) {
                ToastUtils.show(mContext, "请选择商品");
            } else {
                setDraft();
                Intent intent = new Intent(this, SalesHistoryActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    //时间选择器
    private void datePick(final Button button) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                button.setText(new StringBuilder()
                        .append(year)
                        .append("-")
                        .append((month + 1) < 10 ? "0" + (month + 1)
                                : (month + 1)).append("-")
                        .append((day < 10) ? "0" + day : day));
            }
        }, year, monthOfYear, dayOfMonth).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 10) {
                String j = data.getStringExtra("orderDetailDto");
                datas = gson.fromJson(j, new TypeToken<ArrayList<OrderDetailDto>>() {
                }.getType());
                adapter.setData(datas);
                getotalPriceValue();
                if (datas.size() > 0) {
                    rltDiscount.setVisibility(View.VISIBLE);
                } else {
                    rltDiscount.setVisibility(View.GONE);
                }
            }
        }
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
        if (datas.size() > 0) {
            rltDiscount.setVisibility(View.VISIBLE);
        } else {
            rltDiscount.setVisibility(View.GONE);
        }
        adapter.setData(datas);
        getotalPriceValue();
    }

    //更新总价
    private void getotalPriceValue() {
        totalprice = 0;
        totalNum = 0;
        if (datas != null && datas.size() > 0) {
            for (int j = 0; j < datas.size(); j++) {

                double money = datas.get(j).num * datas.get(j).price;
                totalprice = totalprice + money;
                totalNum = totalNum + datas.get(j).num;
            }
        }
        tvTotalPriceValue.setText("￥" + String.valueOf(totalprice));
    }

    private void setDraft() {
        prOrder.totalMoney = totalprice;
        prOrder.totalNum = totalNum;
        prOrder.memo = etRemarkValue.getText().toString();
        if (Utils.isNetworkConnected(this)) {
            final Dialog mDialog = new Dialog(this, R.style.DialogStyle);
            mDialog.show();
            SalesRequest.addLossDraft("2222", "1", prOrder, datas, new
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

    private void sell() {
        prOrder.totalMoney = totalprice;
        prOrder.totalNum = totalNum;
        prOrder.memo = etRemarkValue.getText().toString();
        if (Utils.isNetworkConnected(this)) {
            final Dialog mDialog = new Dialog(this, R.style.DialogStyle);
            mDialog.show();
            SalesRequest.sellLossOrder(action, prOrder, datas, new
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

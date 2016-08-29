package tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchaseorder;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.ProductAdapter;
import tech.boshu.changjiangshidai.bean.api.SalesRequest;
import tech.boshu.changjiangshidai.bean.mode.Arrears;
import tech.boshu.changjiangshidai.bean.mode.OrderDetailDto;
import tech.boshu.changjiangshidai.constants.ParameterConstants;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;
import tech.boshu.changjiangshidai.libs.utils.Utils;
import tech.boshu.changjiangshidai.libs.view.AutoHeightListView;
import tech.boshu.changjiangshidai.ui.activity.sale.AddProductActivity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.finishpurchaseorderdetail.ResponseFinishPurchaseOrderDetail;
import tech.boshu.changjiangshidai.utils.ValidateUtils;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)AddPurchaseOrderActivity.java
 */


public class AddPurchaseOrderActivity extends BaseActivity implements IAddPurchaseOrderView,
        ProductAdapter
                .ProductAdapterLisListener {

    private static final int ACTIVITY_ADD_PRODUCT = 100;
    private static final int ACTIVITY_CHOOSE_STORE = ACTIVITY_ADD_PRODUCT + 1;
    private static final int ACTIVITY_CHOOSE_SETTLEMENT = ACTIVITY_CHOOSE_STORE + 1;


    private RelativeLayout bottomRelativeLayout;
    private TextView total_priceTextView;
    private TextView total_price_valueTextView;
    private TextView supplierTextView;
    private TextView supplier_valueTextView;
    private TextView warehouseTextView;
    private TextView warehousevalueTextView;
    private TextView pay_wayTextView;
    private TextView pay_way_valueTextView;
    private ImageView icon_rightImageView;
    private ImageButton scanProductImageButton;
    private ImageButton addProductImageButton;
    private TextView should_money_titleTextView;
    private TextView should_moneyTextView;
    private TextView real_money_titleTextView;
    private EditText real_moneyEditText;
    private TextView own_money_titleTextView;
    private TextView own_moneyTextView;
    private TextView start_timeTextView;
    private TextView start_time_valueTextView;
    private TextView end_time_valueTextView;
    private EditText remarkEditText;
    private AutoHeightListView listView;
    private RelativeLayout rltDiscount;
    private Button btnEditDiscount;
    private int selectedDateType = -1;// 0,业务日期 1,截止日期


    private AddPurchaseOrderParam addPurchaseOrderParam = new AddPurchaseOrderParam();
    private ProductAdapter adapter;
    private String actionType = ParameterConstants.ACTION_TYPE_ADD;
    private String orderId = null;

    private ValidateUtils validateUtils = new ValidateUtils();


    public void setView() {
        if (actionType.equals(ParameterConstants.ACTION_TYPE_EDIT) || actionType.equals(ParameterConstants.ACTION_TYPE_COPY)) {
            setText(R.id.total_price_value, addPurchaseOrderParam.order.totalMoney);
            setText(R.id.should_money, addPurchaseOrderParam.order.totalMoney);
            setText(R.id.should_money, addPurchaseOrderParam.order.payMoney);
            setText(R.id.supplier_value, addPurchaseOrderParam.order.provider);
            setText(R.id.warehousevalue, addPurchaseOrderParam.order.store);
            setText(R.id.pay_way_value, addPurchaseOrderParam.order.setAccount);
            setText(R.id.remark, addPurchaseOrderParam.order.memo);
            if(addPurchaseOrderParam.order.getCreateTimeStr().length()>10){
                addPurchaseOrderParam.order.setCreateTimeStr(addPurchaseOrderParam.order.getCreateTimeStr().substring(0,9));
            }
            if(addPurchaseOrderParam.order.getPayEndTimeStr().length()>10){
                addPurchaseOrderParam.order.setPayEndTimeStr(addPurchaseOrderParam.order.getPayEndTimeStr().substring(0,9));
            }
            setText(R.id.start_time_value, addPurchaseOrderParam.order.getCreateTimeStr());
            setText(R.id.end_time_value, addPurchaseOrderParam.order.getPayEndTimeStr());
            if (addPurchaseOrderParam.products != null) {
                adapter.setData(addPurchaseOrderParam.products);
            }
            getArrears(addPurchaseOrderParam.order.providerId);
        }

    }

    @Override
    public void setParam(AddPurchaseOrderParam param) {
        this.addPurchaseOrderParam = param;
    }

    public void initView() {
        bottomRelativeLayout = (RelativeLayout) findViewById(R.id.bottom);
        total_priceTextView = (TextView) findViewById(R.id.total_price);
        total_price_valueTextView = (TextView) findViewById(R.id.total_price_value);
        supplierTextView = (TextView) findViewById(R.id.supplier);
        supplier_valueTextView = (TextView) findViewById(R.id.supplier_value);
        warehouseTextView = (TextView) findViewById(R.id.warehouse);
        warehousevalueTextView = (TextView) findViewById(R.id.warehousevalue);
        pay_wayTextView = (TextView) findViewById(R.id.pay_way);
        pay_way_valueTextView = (TextView) findViewById(R.id.pay_way_value);
        icon_rightImageView = (ImageView) findViewById(R.id.icon_right);
        scanProductImageButton = (ImageButton) findViewById(R.id.scanProduct);
        addProductImageButton = (ImageButton) findViewById(R.id.addProduct);
        should_money_titleTextView = (TextView) findViewById(R.id.should_money_title);
        should_moneyTextView = (TextView) findViewById(R.id.should_money);
        real_money_titleTextView = (TextView) findViewById(R.id.real_money_title);
        real_moneyEditText = (EditText) findViewById(R.id.real_money);
        own_money_titleTextView = (TextView) findViewById(R.id.own_money_title);
        own_moneyTextView = (TextView) findViewById(R.id.own_money);
        start_timeTextView = (TextView) findViewById(R.id.start_time);
        start_time_valueTextView = (TextView) findViewById(R.id.start_time_value);
        end_time_valueTextView = (TextView) findViewById(R.id.end_time_value);
        remarkEditText = (EditText) findViewById(R.id.remark);
        listView = (AutoHeightListView) findViewById(R.id.list);
        rltDiscount = (RelativeLayout) findViewById(R.id.discount);
        btnEditDiscount = (Button) findViewById(R.id.edit_discount);
        adapter = new ProductAdapter(mContext, addPurchaseOrderParam.products, this);
        listView.setAdapter(adapter);
    }

    private AddPurchaseOrderPresenter addPurchaseOrderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        actionType = getIntent().getStringExtra(ParameterConstants.PARAM_ACTION_TYPE);
        if (TextUtils.isEmpty(actionType)) {
            initTitle("新建采购单");
            actionType = ParameterConstants.ACTION_TYPE_ADD;
            addPurchaseOrderPresenter.setText(R.id.right, "");
        } else if (actionType.equals(ParameterConstants.ACTION_TYPE_EDIT)) {
            initTitle("采购单");
            orderId = getIntent().getStringExtra(ParameterConstants.PARAM_ORDER_ID);
            addPurchaseOrderParam.order.id = orderId;
            addPurchaseOrderPresenter.setText(R.id.right, "撤销");
            setOnclickListener(R.id.right, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addPurchaseOrderPresenter.cancel();
                }
            });
        } else if (actionType.equals(ParameterConstants.ACTION_TYPE_COPY)) {
            ResponseFinishPurchaseOrderDetail.DataEntity dataEntity =
                    new Gson().fromJson(getIntent().getStringExtra(ParameterConstants.PRAMA_COMMON_VALUE),
                            new TypeToken<ResponseFinishPurchaseOrderDetail.DataEntity>() {
                            }.getType());
            addPurchaseOrderParam.action = dataEntity.action;
            addPurchaseOrderParam.order = dataEntity.order;
            addPurchaseOrderParam.order.createTime = dataEntity.order.getCreateTimeStr().substring(0,9);
            addPurchaseOrderParam.order.payEndTime = dataEntity.order.payEndTimeStr.substring(0,9);
            addPurchaseOrderParam.products = dataEntity.orderDetailList;
            addPurchaseOrderPresenter.setPrepareData(dataEntity.providerList, dataEntity.stroeList, dataEntity.setAccountList);
            setView();
        }
        if (Utils.isNetworkConnected(this) && !actionType.equals(ParameterConstants.ACTION_TYPE_COPY)) {
            addPurchaseOrderParam.action = actionType;
            addPurchaseOrderPresenter.setParam(addPurchaseOrderParam);
            showDialog();
            addPurchaseOrderPresenter.queryPurchaseOrderInfor(addPurchaseOrderParam);
        } else {

        }
        setOnclickListener(R.id.supplier_value, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPurchaseOrderPresenter.selectSupplier();
            }
        });
        setOnclickListener(R.id.purchase, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPurchaseOrderPresenter.purchase();
            }
        });
        setOnclickListener(R.id.warehousevalue, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPurchaseOrderPresenter.selectStock();
            }
        });
        setOnclickListener(R.id.pay_way_value, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPurchaseOrderPresenter.selectPayWay();
            }
        });
        setOnclickListener(R.id.draft, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = validateUtils.validat(validateInterface,
                        supplier_valueTextView, warehousevalueTextView,
                        pay_way_valueTextView, start_time_valueTextView, end_time_valueTextView);
                if (result) {
                    addPurchaseOrderPresenter.setParam(addPurchaseOrderParam);
                    addPurchaseOrderPresenter.add();
                }

            }
        });
        setOnclickListener(R.id.addProduct, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPurchaseOrderPresenter.addProduct();
            }
        });
        setOnclickListener(R.id.start_time_value, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDateType = 0;
                addPurchaseOrderPresenter.selectDate();
            }
        });
        setOnclickListener(R.id.end_time_value, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDateType = 1;
                addPurchaseOrderPresenter.selectDate();
            }
        });
        setOnclickListener(R.id.edit_discount, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = LayoutInflater.from(mContext).inflate(R.layout
                        .dialog_discount, null);
                final AlertDialog dialog = new AlertDialog.Builder(mContext).setView(v)
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
                            Double orderRate = Double.valueOf(discount);
                            for (int i = 0; i < addPurchaseOrderParam.products.size(); i++) {
                                addPurchaseOrderParam.products.get(i).price =
                                        addPurchaseOrderParam.products.get(i).price * orderRate
                                                / 100;
                            }
                            adapter.setData(addPurchaseOrderParam.products);
                            addPurchaseOrderParam.order.discount = Double.valueOf(discount);
                            getotalPriceValue();
                            dialog.dismiss();
                        }
                    }
                });
            }
        });
        real_moneyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String curStr = editable.toString();
                if (TextUtils.isEmpty(curStr)) {
                    curStr = "0";
                }
                addPurchaseOrderParam.order.payMoney = Double.valueOf(curStr);
            }
        });
        remarkEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                addPurchaseOrderParam.order.memo = editable.toString();
            }
        });
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_add_purchase_order;
    }

    @Override
    protected void setPresenter() {
        addPurchaseOrderPresenter = (AddPurchaseOrderPresenter) (presenter =
                new AddPurchaseOrderPresenter(AddPurchaseOrderActivity.this
                ));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ACTIVITY_ADD_PRODUCT) {
                String i = data.getStringExtra("orderDetailDto");
                addPurchaseOrderParam.products = new Gson().fromJson(i, new
                        TypeToken<ArrayList<OrderDetailDto>>() {
                        }.getType());
                adapter.setData(addPurchaseOrderParam.products);
                getotalPriceValue();
                if (addPurchaseOrderParam.products.size() > 0) {
                    rltDiscount.setVisibility(View.VISIBLE);
                } else {
                    rltDiscount.setVisibility(View.GONE);
                }

            } else if (requestCode == ACTIVITY_CHOOSE_STORE) {
                String storeId = data.getStringExtra(ParameterConstants
                        .PARAM_COMMON_SEARCH_RETURN_VALUE);
                addPurchaseOrderParam.order.storeId = Integer.valueOf(storeId);
                warehousevalueTextView.setText(data.getStringExtra(ParameterConstants
                        .PARAM_COMMON_SEARCH_RETURN_NAME));
            } else if (requestCode == ACTIVITY_CHOOSE_SETTLEMENT) {
                String settleMentId = data.getStringExtra(ParameterConstants
                        .PARAM_COMMON_SEARCH_RETURN_VALUE);
                addPurchaseOrderParam.order.settlement = Integer.valueOf(settleMentId);
                pay_way_valueTextView.setText(data.getStringExtra(ParameterConstants
                        .PARAM_COMMON_SEARCH_RETURN_NAME));
            }
        }

    }

    @Override
    public void selectSupplier(final List<ResponseProviderListEntity> supplierList) {
        String[] suppliers = new String[supplierList.size()];
        int i = 0;
        for (ResponseProviderListEntity ppl : supplierList) {
            suppliers[i++] = ppl.name;
//            if (ppl.id.equals(addPurchaseOrderParam.order.providerId)) {
//                index = i - 1;
//            }
        }
        AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        //设置对话框的图标
//        builder.setIcon(R.drawable.header);
        //设置对话框的标题
//        builder.setTitle("选择供应商");
        //0: 默认第一个单选按钮被选中
        builder.setItems(suppliers, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                addPurchaseOrderParam.order.providerId = supplierList.get(which).id;
                supplier_valueTextView.setText(supplierList.get(which).name);
                getArrears(supplierList.get(which).id);
                dialog.dismiss();
            }
        }).show();

//        //添加一个确定按钮
//        builder.setPositiveButton(" 确 定 ", new DialogInterface.OnClickListener(){
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });

    }

    @Override
    public void selectStock(final List<ReponseStroeListEntity> stockList) {
        String[] suppliers = new String[stockList.size()];
        int i = 0;
//        int index = 0;
        for (ReponseStroeListEntity stroeListEntity : stockList) {
            suppliers[i++] = stroeListEntity.name;
//            if (stroeListEntity.id == addPurchaseOrderParam.order.storeId) {
//                index = i - 1;
//            }
        }
        AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        //设置对话框的图标
//        builder.setIcon(R.drawable.header);
        //设置对话框的标题
//        builder.setTitle("选择供应商");
        //0: 默认第一个单选按钮被选中
        builder.setItems(suppliers, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                addPurchaseOrderParam.order.storeId = stockList.get(which).id;
                warehousevalueTextView.setText(stockList.get(which).name);
                dialog.dismiss();
            }
        }).show();
    }

    @Override
    public void selectPayWay(final List<ReponseSetAccountListEntity> setAccountList) {
        String[] suppliers = new String[setAccountList.size()];
        int i = 0;
//        int index = 0;
        for (ReponseSetAccountListEntity stroeListEntity : setAccountList) {
            suppliers[i++] = stroeListEntity.name;
//            if (stroeListEntity.id == addPurchaseOrderParam.order.storeId) {
//                index = i - 1;
//            }
        }
        AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        //设置对话框的图标
//        builder.setIcon(R.drawable.header);
        //设置对话框的标题
//        builder.setTitle("选择供应商");
        //0: 默认第一个单选按钮被选中
        builder.setItems(suppliers, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                addPurchaseOrderParam.order.settlement = setAccountList.get(which).id;
                pay_way_valueTextView.setText(setAccountList.get(which).name);
                dialog.dismiss();
            }
        }).show();
    }

    @Override
    public void scanProduct() {

    }

    private ValidateUtils.ValidateInterface validateInterface = new ValidateUtils
            .ValidateInterface() {
        @Override
        public void onFailed(String message) {
            showToast(message);
        }
    };

    @Override
    public void addProduct() {
        if (validateUtils.validat(validateInterface, warehousevalueTextView)) {
            String orderData = new Gson().toJson(addPurchaseOrderParam.products);
            Intent intent = new Intent(this, AddProductActivity.class);
            intent.putExtra("storeId", addPurchaseOrderParam.order.storeId);
            intent.putExtra("orderData", orderData);
            startActivityForResult(intent, ACTIVITY_ADD_PRODUCT);
        } else {
            ToastUtils.show(mContext, "请选择仓库");
        }
    }

    @Override
    public void selectDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String time = new StringBuilder()
                        .append(year)
                        .append("-")
                        .append((month + 1) < 10 ? "0" + (month + 1)
                                : (month + 1)).append("-")
                        .append((day < 10) ? "0" + day : day).toString();
                if (selectedDateType == 0) {
                    addPurchaseOrderParam.order.createTime = time;
                    start_time_valueTextView.setText(time);
                } else if (selectedDateType == 1) {
                    addPurchaseOrderParam.order.payEndTime = time;
                    end_time_valueTextView.setText(time);
                }
                selectedDateType = -1;

            }
        }, year, monthOfYear, dayOfMonth).show();
    }

    //获取客户尚欠款
    private void getArrears(String customerId) {
        if (Utils.isNetworkConnected(this)) {
            showDialog();
            SalesRequest.queruArrears(customerId, new ResponseCallback<Arrears>() {
                @Override
                public void onRequestSuccess(Arrears result) {
                    own_moneyTextView.setText(String.valueOf(result.data));
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
        unit_price_value.setText(String.valueOf(addPurchaseOrderParam.products.get(position)
                .price));
        discount_value.setText(String.valueOf(addPurchaseOrderParam.products.get(position)
                .discount));
        discount_price_value.setText(String.valueOf(addPurchaseOrderParam.products.get(position)
                .price *
                addPurchaseOrderParam.products.get(position).discount /
                100));
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
                        (curStr)) * addPurchaseOrderParam.products.get(position).discount / 100));

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
                    addPurchaseOrderParam.products.get(position).discount = 0.00;
                }
                addPurchaseOrderParam.products.get(position).discount = Double.valueOf(curStr);
                discount_price_value.setText(String.valueOf(Float.valueOf(unit_price_value
                        .getText().toString()) * addPurchaseOrderParam.products.get(position)
                        .discount / 100));

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
                unit_price_value.setText(String.valueOf(Float.valueOf(curStr) /
                        addPurchaseOrderParam.products.get
                                (position).discount * 100));

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
                addPurchaseOrderParam.products.get(position).price = Double.valueOf
                        (discount_price_value.getText
                                ().toString());
                adapter.setData(addPurchaseOrderParam.products);
                btnEditDiscount.setText("整单折扣（" + discount_value.getText().toString() + "%）");
                addPurchaseOrderParam.order.discount = Double.valueOf(discount_value.getText().toString());
                getotalPriceValue();
                dialog.dismiss();

            }
        });
    }

    @Override
    public void deleteProduct(int position) {
        addPurchaseOrderParam.products.remove(position);
        if (addPurchaseOrderParam.products.size() > 0) {
            rltDiscount.setVisibility(View.VISIBLE);
        } else {
            rltDiscount.setVisibility(View.GONE);
        }
        adapter.setData(addPurchaseOrderParam.products);
        getotalPriceValue();
    }

    //更新总价
    private void getotalPriceValue() {
        double totalprice = 0;
        if (addPurchaseOrderParam.products != null && addPurchaseOrderParam.products.size() > 0) {
            for (int j = 0; j < addPurchaseOrderParam.products.size(); j++) {
                double money = addPurchaseOrderParam.products.get(j).num * addPurchaseOrderParam
                        .products.get(j).price;
                totalprice = totalprice + money;
            }
        }
        total_price_valueTextView.setText("￥" + String.valueOf(totalprice));
        should_moneyTextView.setText("￥" + String.valueOf(totalprice));
        addPurchaseOrderParam.order.totalMoney = totalprice;
    }
}
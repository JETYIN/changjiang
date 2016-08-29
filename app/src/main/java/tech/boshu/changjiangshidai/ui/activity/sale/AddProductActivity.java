package tech.boshu.changjiangshidai.ui.activity.sale;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.AddProductAdapter;
import tech.boshu.changjiangshidai.adapter.SelectedProductAdapter;
import tech.boshu.changjiangshidai.bean.api.SalesRequest;
import tech.boshu.changjiangshidai.bean.mode.OrderDetailDto;
import tech.boshu.changjiangshidai.bean.mode.ProDetail;
import tech.boshu.changjiangshidai.bean.mode.Product;
import tech.boshu.changjiangshidai.bean.mode.ResponseAddProuct;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;
import tech.boshu.changjiangshidai.libs.utils.Utils;

/**
 * Created by Administrator on 2016/1/19.
 */
public class AddProductActivity extends BaseActivity implements SelectedProductAdapter
        .SelectedProductListener {
    private ListView lvProductList;
    private AddProductAdapter adapter;
    private List<OrderDetailDto> orderData = new ArrayList<>();
    private List<Product> datas = new ArrayList<>();
    private Product product = new Product();
    private String companyId = "2222";
    private String strId;
    private String catalogId;
    private String brandId;
    private String status;
    private int storeId;
    private String action = "edit";
    private int number;
    private int position;
    private Button btnSelectedProduct;
    private SelectedProductAdapter selectedProductAdapter;
    private List<Product> list = new ArrayList<>();
    private static final int ADD_NUM = 0;
    private static final int SUB_NUM = 1;
    private Gson gson = new Gson();
    private Button btnAlreadyChoose;
    private List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();


    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_new_commodity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storeId = getIntent().getIntExtra("storeId", 0);
        orderData = gson.fromJson(getIntent().getStringExtra("orderData"), new
                TypeToken<ArrayList<OrderDetailDto>>() {
                }.getType());
        setView();
        getData();
        setData();
    }

    public void setView() {
        lvProductList = (ListView) findViewById(R.id.product_list);
        btnSelectedProduct = (Button) findViewById(R.id.selected_product);
        btnAlreadyChoose = (Button) findViewById(R.id.already_choose);

        selectedProductAdapter = new SelectedProductAdapter(mContext, list, this);
        adapter = new AddProductAdapter(mContext, datas);
        lvProductList.setAdapter(adapter);
        lvProductList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = datas.get(i).id;
                position = i;
                getProductDetils(id);
            }
        });
        btnSelectedProduct.setOnClickListener(this);
        btnAlreadyChoose.setOnClickListener(this);
    }

    private void setData() {
        btnSelectedProduct.setText("已选商品（" + list.size() + "）");
    }

    private void getData() {
        if (Utils.isNetworkConnected(this)) {
            final Dialog mDialog = new Dialog(this, R.style.DialogStyle);
            mDialog.show();
            SalesRequest.queruProductList(companyId, strId, catalogId, brandId, status,
                    new ResponseCallback<ResponseAddProuct>() {

                        @Override
                        public void onRequestSuccess(ResponseAddProuct result) {
                            if (result != null) {
                                datas = result.data.pdList;
                                if (orderData != null) {
                                    for (int i = 0; i < orderData.size(); i++) {
                                        for (int j = 0; j < datas.size(); j++) {
                                            if (orderData.get(i).productId == datas.get(j).id) {
                                                datas.get(j).selected_number = orderData.get(i).num;
                                            }
                                        }
                                    }
                                }
                                if (datas != null && datas.size() > 0) {
                                    list.clear();
                                    for (int i = 0; i < datas.size(); i++) {
                                        if (datas.get(i).selected_number > 0) {
                                            list.add(datas.get(i));
                                        }
                                    }
                                }
                                setData();

                                adapter.setData(datas);
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

    private void getProductDetils(int id) {
        if (Utils.isNetworkConnected(this)) {
            final Dialog mDialog = new Dialog(this, R.style.DialogStyle);
            mDialog.show();
            SalesRequest.queruProductDetils(action, id, storeId, new
                    ResponseCallback<ProDetail>() {


                        @Override
                        public void onRequestSuccess(ProDetail result) {
                            if (result != null) {
                                showProductDetils(result);
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

    private void showProductDetils(ProDetail result) {
        product = result.data.product;
        View v = LayoutInflater.from(mContext).inflate(R.layout
                .dialog_product_details, null);
        final AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setView(v).show();
        TextView product_name = (TextView) v.findViewById(R.id
                .product_name);
        TextView goods_number = (TextView) v.findViewById(R.id
                .goods_number);
        TextView repertory = (TextView) v.findViewById(R.id.repertory);
        TextView size = (TextView) v.findViewById(R.id.size);
        TextView address = (TextView) v.findViewById(R.id.address);
        final EditText product_choose_number_value = (EditText) v.findViewById(R.id
                .product_choose_number_value);
        if (TextUtils.isEmpty(product.name)) {
            product_name.setText(product.name);
        }
        goods_number.setText(product.goodsNo);
        repertory.setText("当前库存：" + product.storeNum);
        address.setText(product.address);
        size.setText(product.spec);
        Button add = (Button) v.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(product_choose_number_value.getText().toString())) {
                    number = Integer.valueOf(product_choose_number_value.getText().toString());
                } else {
                    number = 0;
                }
                if (number > 0 && number <= Integer.valueOf(product.storeNum)) {
                    datas.get(position).selected_number = datas.get(position).selected_number +
                            number;
                    adapter.setData(datas);
                    if (datas != null && datas.size() > 0) {
                        list.clear();
                        for (int i = 0; i < datas.size(); i++) {
                            if (datas.get(i).selected_number > 0) {
                                list.add(datas.get(i));
                            }
                        }
                    }
                    setData();
                    dialog.dismiss();
                } else if (number > Integer.valueOf(product.storeNum)) {
                    ToastUtils.show(mContext, "库存不足");
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.selected_product) {
            if (list.size() > 0) {
                View v = LayoutInflater.from(mContext).inflate(R.layout
                        .dialog_selected_product, null);
                ListView lvSelectedProductList = (ListView) v.findViewById(R.id
                        .selected_product_list);
                lvSelectedProductList.setAdapter(selectedProductAdapter);
                selectedProductAdapter.setData(list);
                final AlertDialog dialog = new AlertDialog.Builder(mContext)
                        .setView(v).show();
                Button continue_add = (Button) v.findViewById(R.id.continue_add);
                Button affirm = (Button) v.findViewById(R.id.affirm);
                continue_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                affirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectedFinish();
                        dialog.dismiss();
                    }
                });
            }
        }
        if (id == R.id.already_choose) {
            selectedFinish();
        }

    }

    @Override
    public void deleteProduct(int position) {
        for (int i = 0; i < datas.size(); i++) {
            if (list.get(position).id == datas.get(i).id) {
                datas.get(i).selected_number = 0;
                adapter.setData(datas);
            }
        }
        list.remove(position);
        selectedProductAdapter.setData(list);


    }

    @Override
    public void addProductNum(int position) {
        productNumChange(ADD_NUM, position);

    }

    @Override
    public void subProductNum(int position) {
        productNumChange(SUB_NUM, position);
    }

    public void productNumChange(int type, int position) {
        int num = list.get(position).selected_number;
        if (type == ADD_NUM) {
            num++;
        } else if (type == SUB_NUM) {
            num--;
        }
        list.get(position).selected_number = num;
        selectedProductAdapter.setData(list);
        for (int i = 0; i < datas.size(); i++) {
            if (list.get(position).id == datas.get(i).id) {
                datas.get(i).selected_number = num;
                adapter.setData(datas);
            }
        }

    }

    private void selectedFinish() {
        Intent intent = new Intent(AddProductActivity.this, AddSalesOrderActivity.class);
        for (int i = 0; i < list.size(); i++) {
            OrderDetailDto orderDetailDto = new OrderDetailDto();
            orderDetailDto.product = list.get(i).name;
            orderDetailDto.price = list.get(i).retailPrice;
            orderDetailDto.place = list.get(i).address;
            orderDetailDto.spec = list.get(i).spec;
            orderDetailDto.num = list.get(i).selected_number;
            orderDetailDto.productId = list.get(i).id;
            orderDetailDtoList.add(orderDetailDto);
        }
        String date = gson.toJson(list);
        String orderDetailDto = gson.toJson(orderDetailDtoList);
        intent.putExtra("list", date);
        intent.putExtra("orderDetailDto", orderDetailDto);
        setResult(RESULT_OK, intent);
        finish();
    }
}

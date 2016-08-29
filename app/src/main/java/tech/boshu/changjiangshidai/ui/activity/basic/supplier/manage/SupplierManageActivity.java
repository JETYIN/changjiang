package tech.boshu.changjiangshidai.ui.activity.basic.supplier.manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.SupplierManageAdapter;
import tech.boshu.changjiangshidai.constants.ParameterConstants;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.libs.utils.Utils;
import tech.boshu.changjiangshidai.ui.activity.basic.customer.CustomerSearchActivity;
import tech.boshu.changjiangshidai.ui.activity.basic.supplier.addoredit.AddOrEditSupplierActivity;

/**
 * Created by Stone on 2016/1/6.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class SupplierManageActivity extends BaseActivity implements
        SupplierManageAdapter.SupplierListener {

    private ImageButton ibAdd;
    private ImageButton ibSearch;
    private ListView listView;
    private SupplierManageAdapter adapter;
    private List<CustomerBean> datas = new ArrayList<>();
    private CustomerManageParam customerManageParam = new CustomerManageParam();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ibAdd = (ImageButton) findViewById(R.id.add);
        ibSearch = (ImageButton) findViewById(R.id.search);
        listView = (ListView) findViewById(R.id.list);
        adapter = new SupplierManageAdapter(mContext, datas, this);
        listView.setAdapter(adapter);
        ibAdd.setOnClickListener(this);
        ibSearch.setOnClickListener(this);
        getDatas();
    }

    public void getDatas() {
        showDialog();
        CustomerManageRequest.query(customerManageParam, new ResponseCallback<ResponseCustomer>() {
            @Override
            public void onRequestSuccess(ResponseCustomer result) {
                hideDialog();
                datas = result.data.providerList;
                adapter.setData(datas);
            }

            @Override
            public void onReuquestFailed(ErrorBase error) {
                hideDialog();
                showToast(error.message);
            }
        });
    }

    @Override
    protected void setLayoutResId() {
        this.layoutResId = R.layout.activity_supplier_manage;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.add) {
            Intent intent = new Intent(mContext, AddOrEditSupplierActivity.class);
            intent.putExtra(ParameterConstants.PARAM_ACTION_TYPE, ParameterConstants.ACTION_TYPE_ADD);
            startActivityForResult(intent, ParameterConstants.ACTIVITY_ADD_OR_EDIT_RESULT);
        } else if (id == R.id.search) {
            startActivityForResult(new Intent(mContext, CustomerSearchActivity.class), ParameterConstants.ACTIVITY_SEARCH_RESULT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == ParameterConstants.ACTIVITY_ADD_OR_EDIT_RESULT) {//编辑或者新增员工
                getDatas();
            } else if (requestCode == ParameterConstants.ACTIVITY_SEARCH_RESULT) {//搜索
                customerManageParam = new Gson().fromJson(data.getStringExtra(
                        ParameterConstants.PRAMA_COMMON_VALUE), CustomerManageParam.class);
                getDatas();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void itemClick(int position) {
        Intent intent = new Intent(mContext, AddOrEditSupplierActivity.class);
        intent.putExtra(ParameterConstants.PARAM_ACTION_TYPE, ParameterConstants.ACTION_TYPE_EDIT);
        intent.putExtra(ParameterConstants.PRAMA_COMMON_VALUE, datas.get(position).id);
        startActivityForResult(intent, ParameterConstants.ACTIVITY_ADD_OR_EDIT_RESULT);
    }

    @Override
    public void callPhone(int position) {
        Utils.callPhone(mContext, "13000000000");
    }

    @Override
    public void senMessage(int position) {
        Utils.sendMessage(mContext, "13000000000");
    }
}

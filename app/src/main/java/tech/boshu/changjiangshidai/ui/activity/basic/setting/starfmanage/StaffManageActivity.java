package tech.boshu.changjiangshidai.ui.activity.basic.setting.starfmanage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.StaffManageAdapter;
import tech.boshu.changjiangshidai.constants.ParameterConstants;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.libs.utils.Utils;
import tech.boshu.changjiangshidai.ui.activity.basic.setting.addoreditstaff.AddOrEditStaffActivity;
import tech.boshu.changjiangshidai.ui.activity.basic.setting.search.StaffSearchActivity;

/**
 * Created by Stone on 2016/1/6.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class StaffManageActivity extends BaseActivity implements
        StaffManageAdapter.StaffManageListener {
    private static final int REQUST_ADD_OR_EDIT_STAFF = 100;
    private static final int REQUST_SEARCH_STAFF = REQUST_ADD_OR_EDIT_STAFF + 1;

    private ImageButton ibAdd;
    private ImageButton ibSearch;
    private ListView listView;
    private StaffManageAdapter adapter;
    private List<StaffBean> datas = new ArrayList<>();
    private StaffManageParam staffManageParam = new StaffManageParam();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ibAdd = (ImageButton) findViewById(R.id.add);
        ibSearch = (ImageButton) findViewById(R.id.search);
        listView = (ListView) findViewById(R.id.list);
        adapter = new StaffManageAdapter(mContext, datas, this);
        listView.setAdapter(adapter);
        ibAdd.setOnClickListener(this);
        ibSearch.setOnClickListener(this);
        getDatas();
    }

    public void getDatas() {
        showDialog();
        StaffManageRequest.query(staffManageParam, new ResponseCallback<ResponseStaff>() {
            @Override
            public void onRequestSuccess(ResponseStaff result) {
                hideDialog();
                datas = result.data.accountList;
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
        this.layoutResId = R.layout.activity_staff_manage;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.add) {
            Intent intent = new Intent(mContext, AddOrEditStaffActivity.class);
            intent.putExtra(ParameterConstants.PARAM_ACTION_TYPE, ParameterConstants.ACTION_TYPE_ADD);
            startActivityForResult(intent, REQUST_ADD_OR_EDIT_STAFF);
        } else if (id == R.id.search) {
            startActivityForResult(new Intent(mContext, StaffSearchActivity.class), REQUST_SEARCH_STAFF);
        }
    }

    @Override
    public void itemClick(int position) {
        Intent intent = new Intent(mContext, AddOrEditStaffActivity.class);
        intent.putExtra(ParameterConstants.PARAM_ACTION_TYPE, ParameterConstants.ACTION_TYPE_EDIT);
        intent.putExtra(ParameterConstants.PRAMA_COMMON_VALUE, datas.get(position).accountId);
        startActivityForResult(intent, REQUST_ADD_OR_EDIT_STAFF);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUST_ADD_OR_EDIT_STAFF) {//编辑或者新增员工
                getDatas();
            } else if (requestCode == REQUST_SEARCH_STAFF) {//搜索
                staffManageParam = new Gson().fromJson(data.getStringExtra(
                        ParameterConstants.PRAMA_COMMON_VALUE), StaffManageParam.class);
                getDatas();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void callPhone(int position) {
        Utils.callPhone(mContext, datas.get(position).phone);
    }

    @Override
    public void senMessage(int position) {
        Utils.sendMessage(mContext, datas.get(position).phone);
    }
}

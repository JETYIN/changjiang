package tech.boshu.changjiangshidai.ui.activity.business;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.ClientsAdapter;
import tech.boshu.changjiangshidai.bean.Client;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.utils.UIHelper;

/**
 * 5经营概况 P7-5经营概况
 */
public class ManagerSurveyActivity extends BaseActivity {

    private TextView tv_Today;
    private TextView tv_Yesterday;
    private TextView tv_Week;
    private TextView tv_Month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitle("经营概况");
        findViews();
        initData();
    }

    private void initData() {
    }

    private void findViews() {
        tv_Today = (TextView) findViewById(R.id.tv_today);
        tv_Yesterday = (TextView) findViewById(R.id.tv_yesterday);
        tv_Week = (TextView) findViewById(R.id.tv_week);
        tv_Month = (TextView) findViewById(R.id.tv_month);

        tv_Today.setOnClickListener(this);
        tv_Yesterday.setOnClickListener(this);
        tv_Week.setOnClickListener(this);
        tv_Month.setOnClickListener(this);
        findViewById(R.id.right).setOnClickListener(this);
    }


    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_manager_survey;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.tv_today){
            if(!tv_Today.isSelected()){
                tv_Today.setSelected(true);
                tv_Yesterday.setSelected(false);
                tv_Week.setSelected(false);
                tv_Month.setSelected(false);
            }
        }else  if(id == R.id.tv_yesterday){
            if(!tv_Yesterday.isSelected()){
                tv_Today.setSelected(false);
                tv_Yesterday.setSelected(true);
                tv_Week.setSelected(false);
                tv_Month.setSelected(false);
            }
        }else  if(id == R.id.tv_week){
            if(!tv_Week.isSelected()){
                tv_Today.setSelected(false);
                tv_Yesterday.setSelected(false);
                tv_Week.setSelected(true);
                tv_Month.setSelected(false);
            }
        }else  if(id == R.id.tv_month){
            if(!tv_Month.isSelected()){
                tv_Today.setSelected(false);
                tv_Yesterday.setSelected(false);
                tv_Week.setSelected(false);
                tv_Month.setSelected(true);
            }
        }else  if(id == R.id.right){
            UIHelper.showManagerSurveySearchActivity(this);
        }
    }
}


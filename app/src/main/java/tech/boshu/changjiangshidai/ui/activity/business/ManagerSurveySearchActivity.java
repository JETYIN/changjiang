package tech.boshu.changjiangshidai.ui.activity.business;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;

/**
 * 查询 P7-7-1查询
 */
public class ManagerSurveySearchActivity extends BaseActivity {


    private TextView tv_StartTime;// 开始时间
    private TextView tv_EndTime;// 结束时间
    private TextView currentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitle("筛选");
        presenter.setText(R.id.right,"关闭");
        setOnclickListener(R.id.right, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        findViews();
        initData();
    }

    private void initData() {
    }

    private void findViews() {
        tv_StartTime = (TextView) findViewById(R.id.tv_start_time);
        tv_EndTime = (TextView) findViewById(R.id.tv_end_time);

        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });
        findViewById(R.id.ll_clean).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clean();
            }
        });
        tv_StartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentView = (TextView) view;
                datePick();
            }
        });
        tv_EndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentView = (TextView) view;
                datePick();
            }
        });
    }

    /**
     * 时间选择对话框
     */
    private void datePick() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                // 更新EditText控件日期 小于10加0
                currentView.setText(new StringBuilder()
                        .append(year)
                        .append("-")
                        .append((month + 1) < 10 ? "0" + (month + 1)
                                : (month + 1)).append("-")
                        .append((day < 10) ? "0" + day : day));
            }
        }, year, monthOfYear, dayOfMonth).show();
    }
    /**
     * 搜索
     */
    private void search() {
        ToastUtils.show(this,"search");
    }
    /**
     * 清空输入
     */
    private void clean() {
        tv_StartTime.setText("");
        tv_EndTime.setText("");
    }


    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_manager_survey_search;
    }
}


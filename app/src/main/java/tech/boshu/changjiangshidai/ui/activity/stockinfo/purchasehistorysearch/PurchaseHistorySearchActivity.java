package tech.boshu.changjiangshidai.ui.activity.stockinfo.purchasehistorysearch;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.constants.ParameterConstants;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;
import tech.boshu.changjiangshidai.ui.activity.common.search.ChooseDialogActivity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.purchasehistory.PurchaseRequestParam;
import tech.boshu.changjiangshidai.utils.SubUtils;

public class PurchaseHistorySearchActivity extends BaseActivity {

    public final static int ACTIVITY_CHOOSE_FILTER = 100;

    private final static int TYPE_BEGIN_TIME = 0;
    private final static int TYPE_END_TIME = TYPE_BEGIN_TIME + 1;

    private TextView chooseStockTextView;
    private EditText orderIdEditText;
    private TextView chooseStartTextView;
    private TextView chooseEndTextView;
    private PurchaseRequestParam purchaseRequestParam = new PurchaseRequestParam();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();
        setText(R.id.right, "清空");
        initTitle("查询");
        setOnclickListener(R.id.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.back(view);
            }
        });
        setOnclickListener(R.id.right, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.back(view);
            }
        });
        setOnclickListener(R.id.chooseStock, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ChooseDialogActivity.class);
                intent.putExtra(ParameterConstants.PARAM_COMMON_SEARCH_TYPE, ChooseDialogActivity.TYPE_PROVIDER);
                startActivityForResult(intent, ACTIVITY_CHOOSE_FILTER);
            }
        });
        setOnclickListener(R.id.chooseStart, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePick((TextView) view, TYPE_BEGIN_TIME);
            }
        });
        setOnclickListener(R.id.chooseEnd, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePick((TextView) view, TYPE_END_TIME);
            }
        });
        setOnclickListener(R.id.search, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(ParameterConstants.PARAM_COMMON_SEARCH_RETURN_VALUE, purchaseRequestParam.toString());
                setResult(RESULT_OK, intent);

                finish();
            }
        });

    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_purchase_history_search;
    }


    private void findViews() {
        chooseStockTextView = (TextView) findViewById(R.id.chooseStock);
        orderIdEditText = (EditText) findViewById(R.id.orderId);
        chooseStartTextView = (TextView) findViewById(R.id.chooseStart);
        chooseEndTextView = (TextView) findViewById(R.id.chooseEnd);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_CHOOSE_FILTER) {
            if (resultCode == RESULT_OK) {
                String providerId = data.getStringExtra(ParameterConstants.PARAM_COMMON_SEARCH_RETURN_VALUE);
                purchaseRequestParam.providerId = providerId;
                chooseStockTextView.setText(data.getStringExtra(ParameterConstants.PARAM_COMMON_SEARCH_RETURN_NAME));
            }
        }
    }

    private void datePick(final TextView button, final int type) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String timeStr = new StringBuilder()
                        .append(year)
                        .append("-")
                        .append((month + 1) < 10 ? "0" + (month + 1)
                                : (month + 1)).append("-")
                        .append((day < 10) ? "0" + day : day).toString();
                button.setText(timeStr);
                if (type == TYPE_BEGIN_TIME) {
                    purchaseRequestParam.beginTime = timeStr;
                } else {
                    long startTime = SubUtils.getTimeSeconds(purchaseRequestParam.beginTime, null);
                    long endTime = SubUtils.getTimeSeconds(timeStr, null);
                    if (endTime <= startTime) {
                        ToastUtils.show(mContext, "结束日期不得小于开始时间");
                        chooseEndTextView.performClick();
                    } else {
                        purchaseRequestParam.endTime = timeStr;
                    }
                }
            }
        }, year, monthOfYear, dayOfMonth).show();
    }
}


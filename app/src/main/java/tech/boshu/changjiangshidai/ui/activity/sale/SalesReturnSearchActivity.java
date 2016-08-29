package tech.boshu.changjiangshidai.ui.activity.sale;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;

public class SalesReturnSearchActivity extends BaseActivity {

    private TextView chooseStockTextView;
    private EditText orderIdEditText;
    private TextView chooseStartTextView;
    private TextView chooseEndTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setText(R.id.right, "清空");
        initTitle("查询");
        presenter.setOnclickListener(R.id.right, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.back(view);
            }
        });
        findViews();
        getData(false);
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_sales_return_search;
    }

    private void getData(boolean isShowDialog) {

    }

    private void findViews() {
        chooseStockTextView = (TextView) findViewById(R.id.chooseStock);
        orderIdEditText = (EditText) findViewById(R.id.orderId);
        chooseStartTextView = (TextView) findViewById(R.id.chooseStart);
        chooseEndTextView = (TextView) findViewById(R.id.chooseEnd);

    }

    //���õ���¼�


}


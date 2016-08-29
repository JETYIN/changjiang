package tech.boshu.changjiangshidai.ui.activity.basic.product;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;

/**
 * Created by Stone on 2016/1/7.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class ProductSearchActivity extends BaseActivity {

    private TextView tvTitle;
    private TextView tvCancle;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    public void initView() {
        tvTitle = (TextView) findViewById(R.id.title);
        tvCancle = (TextView) findViewById(R.id.right);
        btnSearch = (Button) findViewById(R.id.btn_search);

        tvTitle.setText("查询");
        tvCancle.setText("清空");
        tvCancle.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }

    @Override
    protected void setLayoutResId() {
        this.layoutResId = R.layout.activity_product_search;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.right) {
            finish();
        } else if (id == R.id.btn_search) {
            searchProduct();
        }
    }

    private void searchProduct() {

    }

}

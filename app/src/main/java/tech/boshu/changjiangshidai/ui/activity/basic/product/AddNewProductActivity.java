package tech.boshu.changjiangshidai.ui.activity.basic.product;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.gson.Gson;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.mode.Product;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;

/**
 * Created by Stone on 2016/1/9.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class AddNewProductActivity extends BaseActivity implements
        CompoundButton.OnCheckedChangeListener {
    public static final int ADD = 0;
    public static final int EDIT = 1;
    public static final int COPY = 2;

    private TextView tvTitle;
    private TextView tvSave;
    private int type;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    public void initView() {
        tvTitle = (TextView) findViewById(R.id.title);
        tvSave = (TextView) findViewById(R.id.right);

        tvSave.setText("保存");
        tvSave.setOnClickListener(this);

        type = getIntent().getIntExtra("type", ADD);

        if (type == EDIT) {
            tvTitle.setText("商品信息");
            product = new Gson().fromJson(getIntent().getStringExtra("product"), Product.class);
        } else if (type == ADD) {
            tvTitle.setText("新增商品");
            product = new Product();
        } else if (type == COPY) {
            tvTitle.setText("客户商品");
            product = new Gson().fromJson(getIntent().getStringExtra("product"), Product.class);
        }
    }

    @Override
    protected void setLayoutResId() {
        this.layoutResId = R.layout.activity_add_product;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.right) {
            saveProduct();
        }
    }

    //保存商品信息
    private void saveProduct() {
        ToastUtils.show(mContext, "保存成功");
        setResult(RESULT_OK);
        finish();
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        int id = compoundButton.getId();
        if (id == R.id.cb_is_available) {//是否启用账号
        }
    }
}

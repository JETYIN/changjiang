package tech.boshu.changjiangshidai.ui.activity.basic.warehouse;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.SelectedProductAdapter;
import tech.boshu.changjiangshidai.bean.mode.Product;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;

/**
 * Created by Stone on 2016/1/6.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class SeleteProductActivity extends BaseActivity implements
        AdapterView.OnItemClickListener,SelectedProductAdapter.SelectedProductListener {

    private ImageButton ibAdd;
    private ImageButton ibScan;
    private ListView listView;
    private SelectedProductAdapter adapter;
    private List<Product> datas;

    @Override
    protected void setLayoutResId() {
        this.layoutResId = R.layout.activity_selected_product;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ibAdd = (ImageButton) findViewById(R.id.add);
        ibScan = (ImageButton) findViewById(R.id.scan);
        listView = (ListView) findViewById(R.id.list);
        datas = getDatas();
        adapter = new SelectedProductAdapter(mContext, datas,this);
        listView.setAdapter(adapter);
        ibAdd.setOnClickListener(this);
        ibScan.setOnClickListener(this);
        listView.setOnItemClickListener(this);
    }

    public List<Product> getDatas() {
        List<Product> datas = new ArrayList<>();
        datas.add(new Product());
        datas.add(new Product());
        datas.add(new Product());
        datas.add(new Product());
        datas.add(new Product());
        datas.add(new Product());
        datas.add(new Product());
        datas.add(new Product());
        datas.add(new Product());
        datas.add(new Product());
        datas.add(new Product());
        datas.add(new Product());
        return datas;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.add) {
        } else if (id == R.id.scan) {
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void deleteProduct(int position) {

    }

    @Override
    public void addProductNum(int position) {

    }

    @Override
    public void subProductNum(int position) {

    }
}

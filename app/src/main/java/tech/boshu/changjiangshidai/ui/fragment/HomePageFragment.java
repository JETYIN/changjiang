package tech.boshu.changjiangshidai.ui.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.HomepageItem;
import tech.boshu.changjiangshidai.database.DBUtils;
import tech.boshu.changjiangshidai.libs.adapter.CommonAdapter;
import tech.boshu.changjiangshidai.libs.adapter.ViewHolder;
import tech.boshu.changjiangshidai.ui.activity.home.CaptureActivity;
import tech.boshu.changjiangshidai.ui.activity.home.SearchProductActivity;

/**
 * Created by zoulinlin on 16/1/1.
 */
public class HomePageFragment extends Fragment {
    GridView mHomeView;
    FrameLayout searchFl;
    ImageView scanIv;
    List<HomepageItem> mPageItems;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page, null);
        mHomeView = (GridView) view.findViewById(R.id.homepage_grid);
        searchFl = (FrameLayout) view.findViewById(R.id.search);
        scanIv = (ImageView) view.findViewById(R.id.right);
        scanIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                getActivity().startActivity(intent);
            }
        });
        searchFl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchProductActivity.class);
                getActivity().startActivity(intent);
            }
        });
        view.findViewById(R.id.title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setData(Uri.parse("officeHelper://main"));
                startActivity(intent);
            }
        });
        return view;
    }

    public void onStart() {
        super.onStart();
        initHomeItems();

        mHomeView.setAdapter(new CommonAdapter<HomepageItem>(getActivity(), mPageItems, R.layout
                .homepage_cell) {
            @Override
            public void convert(ViewHolder holder, HomepageItem homepageItem) {
                ((TextView) holder.getView(R.id.title)).setText(homepageItem.name);
                ((ImageView) holder.getView(R.id.image)).setImageResource(getResources()
                        .getIdentifier("shortcut" + homepageItem.imageName, "mipmap", getActivity
                                ().getPackageName()));
            }
        });
        mHomeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HomepageItem item = mPageItems.get(position);
                try {
                    Intent intent = new Intent(getActivity(), Class.forName(getActivity().getApplication().getPackageName() + item.jumptoClass));
                    getActivity().startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void initHomeItems() {
        mPageItems = DBUtils.queryAvaliableShorcuts();
        if (mPageItems != null && mPageItems.size() > 0) {
            return;
        }
        InputStream inputStream = null;
        AssetManager assetManager = getActivity().getAssets();
        try {
            inputStream = assetManager.open("shortcut.csv");
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        String content = readTextFile(inputStream);
        Gson gson = new Gson();
        mPageItems = gson.fromJson(content, new TypeToken<List<HomepageItem>>() {
        }.getType());
        DBUtils.initShortcuts(mPageItems);
        //去掉不可用的
        for (int i = 0; i < mPageItems.size(); i++) {
            HomepageItem item = mPageItems.get(i);
            if (item.status == 0) {
                mPageItems.remove(i);
                i--;
            }
        }
    }


    private String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toString();
    }
}

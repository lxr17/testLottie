package com.lxr.testlottie;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> mDatas;
    private RelativeLayout parent;
    private SmartRefreshLayout srl;

    private int index = 0;

    private Adapter mAdapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatas = new ArrayList<>();

        mAdapter = new Adapter(mDatas);

        parent = findViewById(R.id.rl_parent);

        srl = findViewById(R.id.srl);

        // 设置rv属性
        RecyclerView rv = findViewById(R.id.rv);
        rv.setAdapter(mAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        // 设置数据
        for (int i = index; i < 5 + index; i++) {
            mDatas.add("这是第" + i + "条数据数据数据数据数据数据数据数据数据数据数据数据数据数据。");
        }
        index += 5;

        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new CustomRefreshHead(getApplication());
            }
        });

        // 刷新时间
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                // 设置数据
                for (int i = index; i < 5 + index; i++) {
                    mDatas.add("这是第" + i + "条数据数据数据数据数据数据数据数据数据数据数据数据数据数据。");
                }

                index += 5;

                mAdapter.notifyDataSetChanged();
                refreshLayout.finishRefresh(3500);
            }
        });


    }
}

package org.gxz.mydemo.listview.xrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.gxz.mydemo.BaseActivity;
import org.gxz.mydemo.R;
import org.gxz.mydemo.listview.xrecyclerview.lib.XRecyclerView;

import java.util.ArrayList;

public class LinearActivity extends BaseActivity {
	private XRecyclerView mRecyclerView;
	private MyAdapter mAdapter;
	private ArrayList<String> listData;
	private int refreshTime = 0;
	private int times = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recyclerview);
		int style = getIntent().getIntExtra("style", -1);
		mRecyclerView = (XRecyclerView) this.findViewById(R.id.recyclerview);
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mRecyclerView.setLayoutManager(layoutManager);

		mRecyclerView.setRefreshProgressStyle(style);
		mRecyclerView.setLaodingMoreProgressStyle(style);
		mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

		View header = LayoutInflater.from(this).inflate(R.layout.recyclerview_header, (ViewGroup) findViewById(android.R.id.content), false);
		mRecyclerView.addHeaderView(header);

		mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
			@Override
			public void onRefresh() {
				refreshTime++;
				times = 0;
				new Handler().postDelayed(new Runnable() {
					public void run() {

						listData.clear();
						for (int i = 0; i < 15; i++) {
							listData.add("item" + i + "after " + refreshTime + " times of refresh");
						}
						mAdapter.notifyDataSetChanged();
						mRecyclerView.refreshComplete();
					}

				}, 1000);            //refresh data here
			}

			@Override
			public void onLoadMore() {
				if (times < 2) {
					new Handler().postDelayed(new Runnable() {
						public void run() {
							mRecyclerView.loadMoreComplete();
							for (int i = 0; i < 15; i++) {
								listData.add("item" + (i + listData.size()));
							}
							mAdapter.notifyDataSetChanged();
							mRecyclerView.refreshComplete();
						}
					}, 1000);
				} else {
					new Handler().postDelayed(new Runnable() {
						public void run() {

							mAdapter.notifyDataSetChanged();
							mRecyclerView.loadMoreComplete();
						}
					}, 1000);
				}
				times++;
			}
		});

		listData = new ArrayList<String>();
		for (int i = 0; i < 15; i++) {
			listData.add("item" + (i + listData.size()));
		}
		mAdapter = new MyAdapter(listData);

		mRecyclerView.setAdapter(mAdapter);
	}



}

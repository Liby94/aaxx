package com.liby.lego.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author geyifeng
 * @date 2017/5/12
 */
public class HomeOneFragment extends BaseImmersionFragment {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.llScan)
    LinearLayout mLlScan;
    private OneAdapter mOneAdapter;
    private List<String> mItemList = new ArrayList<>();
//    private List<String> mImages = new ArrayList<>();
//    private int bannerHeight;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one_home;
    }

    @Override
    protected void initData() {
        for (int i = 1; i <= 20; i++) {
            mItemList.add("item" + i);
        }
//        mImages = Utils.getPics();
    }

    void test() {
//        mToolbar = mRootView.findViewById(R.id.toolbar);
//        mRv = mRootView.findViewById(R.id.rv);
//        refreshLayout = mRootView.findViewById(R.id.refreshLayout);
//        mLlScan = mRootView.findViewById(R.id.llScan);
    }

    @Override
    protected void initView() {
        Log.i("BaseImmersionFragment", "initView toolbar: " + mToolbar);
//        refreshLayout.setEnableLoadmore(false);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(linearLayoutManager);
        mOneAdapter = new OneAdapter();
        // mOneAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRv.setAdapter(mOneAdapter);

        // mOneAdapter.setPreLoadNumber(1);
        mOneAdapter.setList(mItemList);
    }

    private void addHeaderView() {
//        if (mImages != null && mImages.size() > 0) {
//            View headView = LayoutInflater.from(mActivity).inflate(R.layout.item_banner, (ViewGroup) mRv.getParent(), false);
//            Banner banner = headView.findViewById(R.id.banner);
//            banner.setImages(mImages)
//                    .setImageLoader(new GlideImageLoader())
//                    .setDelayTime(5000)
//                    .start();
//            mOneAdapter.addHeaderView(headView);
//            ViewGroup.LayoutParams bannerParams = banner.getLayoutParams();
//            ViewGroup.LayoutParams titleBarParams = mToolbar.getLayoutParams();
//            bannerHeight = bannerParams.height - titleBarParams.height - ImmersionBar.getStatusBarHeight(mActivity);
//        }
    }
    private int bannerHeight;
    @Override
    protected void setListener() {
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalDy = 0;

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy += dy;
                if (totalDy <= bannerHeight) {
                    float alpha = (float) totalDy / bannerHeight;
                    mToolbar.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT
                            , ContextCompat.getColor(mActivity, R.color.white), alpha));
                } else {
                    mToolbar.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT
                            , ContextCompat.getColor(mActivity, R.color.white), 1));
                }
            }
        });
//        mOneAdapter.setOnLoadMoreListener(() -> new Handler().postDelayed(() -> {
//            mOneAdapter.addData(addData());
//            if (mItemList.size() == 100) {
//                mOneAdapter.loadMoreEnd();
//            } else {
//                mOneAdapter.loadMoreComplete();
//            }
//        }, 2000), mRv);
//        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
//            @Override
//            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
//                new Handler().postDelayed(() -> {
//                    mItemList.clear();
//                    mItemList.addAll(newData());
//                    mOneAdapter.setNewData(mItemList);
//                    refreshLayout.finishRefreshing();
//                    if (mToolbar != null) {
//                        mToolbar.setVisibility(View.VISIBLE);
//                    }
//                }, 2000);
//            }
//
//            @Override
//            public void onPullingDown(TwinklingRefreshLayout refreshLayout, float fraction) {
//                if (mToolbar != null) {
//                    mToolbar.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onPullDownReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {
//                if (mToolbar != null) {
//                    if (Math.abs(fraction - 1.0f) > 0) {
//                        mToolbar.setVisibility(View.VISIBLE);
//                    } else {
//                        mToolbar.setVisibility(View.GONE);
//                    }
//                }
//            }
//        });

        // mLlScan.setOnClickListener(v -> startActivity(new Intent(getActivity(), FragmentOneActivity.class)));
    }

    private List<String> addData() {
        List<String> data = new ArrayList<>();
        for (int i = mItemList.size() + 1; i <= mItemList.size() + 20; i++) {
            data.add("item" + i);
        }
        return data;
    }

    private List<String> newData() {
        List<String> data = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            data.add("item" + i);
        }
        return data;
    }

    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarColorTransformEnable(false)
                .statusBarDarkFont(true)
                .keyboardEnable(false)
                .navigationBarColor(R.color.color_256ffff)
                .init();
    }
}

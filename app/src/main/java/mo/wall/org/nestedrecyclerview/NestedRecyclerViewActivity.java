package mo.wall.org.nestedrecyclerview;

import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import org.wall.mo.base.mvp.BaseMVPAppCompatActivity;

import java.util.List;

import mo.wall.org.R;
import mo.wall.org.databinding.ActivityNestedRecyclerviewBinding;

/**
 * Copyright (C), 2018-2020
 * Author: ziqimo
 * Date: 2020-01-06 20:34
 * Description:
 * <p>
 * <p>
 * 这里还有一参考对象
 * <p>
 * https://github.com/hh-pan/NestRecyScroll
 * <p>
 * <p>
 * <p>
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class NestedRecyclerViewActivity extends
        BaseMVPAppCompatActivity<
                NestedRecyclerViewContract.View,
                NestedRecyclerViewContract.Presenter,
                ActivityNestedRecyclerviewBinding>
        implements NestedRecyclerViewContract.View {

    @Nullable
    NestedParentMultiItemQuickAdapter multiItemQuickAdapter = null;

    @Override
    public NestedRecyclerViewPresenter createPresenter() {
        return new NestedRecyclerViewPresenter();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_nested_recyclerview;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

//        if (savedInstanceState != null) {
//            List<Fragment> fragments = getSupportFragmentManager().getFragments();
//            for (Fragment fragment : fragments) {
//                getSupportFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
//            }
//        }

        if (mViewDataBinding != null) {
            mViewDataBinding.topView.tvTopBarLeftBack.setVisibility(View.VISIBLE);
            mViewDataBinding.topView.tvTopBarLeftBack.setOnClickListener(v -> {
                onBackPressed();
            });
            mViewDataBinding.topView.tvTopBarTitle.setText("NestedRecyclerView");


            multiItemQuickAdapter = new NestedParentMultiItemQuickAdapter(this, getLifecycle(), null);

            multiItemQuickAdapter.bindToRecyclerView(mViewDataBinding.parentView);

            GridLayoutManager gridLayoutManager = mViewDataBinding.parentView.initLayoutManager(12);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    NestedParentMultiItemEntity nestedMultiItemEntity = multiItemQuickAdapter.getData().get(position);
                    int itemType = nestedMultiItemEntity.getItemType();
                    switch (itemType) {
                        case 1:
                        case 3:
                        case 5:
                            return 12;

                        case 4:
                            return 4;
                    }
                    return 3;
                }
            });
        }

        if (mPresenter != null) {
            mPresenter.load();
        }
    }


    @Override
    public void showData(List<NestedParentMultiItemEntity> itemEntityList) {
        if (multiItemQuickAdapter != null) {
            multiItemQuickAdapter.setNewData(itemEntityList);
        }
        //bottomView();
    }

    @Override
    public void parseIntentData() {

    }

    @Override
    public void handleSubMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setTopBarTitle() {

    }

    @Override
    public void setTopBarBack() {

    }

    @Override
    public Parcelable getBundle() {
        return null;
    }

    @Override
    public void showShortToast(String msg) {

    }

    @Override
    public void showLongToast(String msg) {

    }

    @Override
    public void showDialog(String msg) {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void showInfoDialog(String msg) {

    }


    @Override
    public void statusLoadingView() {

    }

    @Override
    public void statusNetWorkView() {

    }

    @Override
    public void statusErrorView(int type, String msg) {

    }

    @Override
    public void statusContentView() {

    }
}

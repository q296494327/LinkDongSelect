package com.explam.linkdongdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

/**
 * User: xiemiao
 * Date: 2017-05-14
 * Time: 12:20
 * Desc: 医院列表fragment
 */
public class HospitalListFragment extends Fragment {
    private List<Hospital> mHospitalList;
    private RecyclerView rvList;
    private HospitalListAdapter mAdapter;
    private MainActivity mMainActivity;

    public HospitalListFragment(List<Hospital> hospitalList) {
        mHospitalList = hospitalList;
    }

    public void setHospitalList(List<Hospital> hospitalList) {
        mHospitalList.clear();
        mHospitalList.addAll(hospitalList);
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hospital_list, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mMainActivity = (MainActivity) getActivity();
        rvList = (RecyclerView) view.findViewById(R.id.rvList);
        //线性布局管家
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        //创建适配器
        mAdapter = new HospitalListAdapter(R.layout.item_textview, mHospitalList);
        rvList.setAdapter(mAdapter);
        mAdapter.openLoadAnimation();

        //设置点击监听
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter
                .OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mAdapter.setSelectPosition(position);
                mMainActivity.setViewPagerCanScroll();
                //跳转到科室的viewpager
                mMainActivity.showDepartmentPage(mHospitalList.get(position).getUid());
            }
        });
    }

    public void clearAdapterSelectPosition() {
        mAdapter.setSelectPosition(-1);
    }

    public int getSelectedPosition() {
        return mAdapter.getSelectPosition();
    }

    class HospitalListAdapter extends BaseQuickAdapter<Hospital> {

        public HospitalListAdapter(int layoutResId, List<Hospital> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Hospital item) {
            helper.setText(R.id.tvName, item.getName());
            int position = helper.getLayoutPosition();
            View convertView = helper.getConvertView();
            if (selectPosition == position) {
                convertView.setBackgroundColor(Color.GRAY);
            } else {
                convertView.setBackgroundColor(Color.WHITE);
            }
        }

        private int selectPosition = -1;

        public void setSelectPosition(int selectPosition) {
            this.selectPosition = selectPosition;
            notifyDataSetChanged();
        }

        public int getSelectPosition() {
            return selectPosition;
        }
    }
}

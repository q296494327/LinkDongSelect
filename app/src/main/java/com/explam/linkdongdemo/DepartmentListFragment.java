package com.explam.linkdongdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xiemiao
 * Date: 2017-05-14
 * Time: 12:20
 * Desc: 科室列表fragment
 */
public class DepartmentListFragment extends Fragment {
    private List<Department> mDepartmentList = new ArrayList<>();
    private RecyclerView rvList;
    private DepartmentListAdapter mAdapter;

    public void setDepartmentlList(List<Department> departmentList) {
        mDepartmentList.clear();
        mDepartmentList.addAll(departmentList);
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
        rvList = (RecyclerView) view.findViewById(R.id.rvList);
        //线性布局管家
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        //创建适配器
        mAdapter = new DepartmentListAdapter(R.layout.item_textview, mDepartmentList);
        rvList.setAdapter(mAdapter);
        mAdapter.openLoadAnimation();

        //科室点击事件监听
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter
                .OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Department department = mDepartmentList.get(position);
                Toast.makeText(getActivity(), department.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    class DepartmentListAdapter extends BaseQuickAdapter<Department> {

        public DepartmentListAdapter(int layoutResId, List<Department> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Department item) {
            helper.setText(R.id.tvName, item.getName());
        }
    }
}

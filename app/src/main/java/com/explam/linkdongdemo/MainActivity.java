package com.explam.linkdongdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnWH;
    private Button btnQG;
    private TextView tvHospital;
    private TextView tvDepartment;
    private NoScrollViewPager viewPager;
    private List<HospitalGroup> mHospitalGroupList;
    private List<Fragment> mFragments = new ArrayList<>();
    private HospitalListFragment mHospitalListFragment;
    private DepartmentListFragment mDepartmentListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHospitalGroupList = GetData.getHospitalGroupList();
        initView();
        initListener();
    }

    private void initView() {
        btnWH = (Button) findViewById(R.id.btnWH);
        btnQG = (Button) findViewById(R.id.btnQG);
        tvHospital = (TextView) findViewById(R.id.tvHospital);
        tvDepartment = (TextView) findViewById(R.id.tvDepartment);
        viewPager = (NoScrollViewPager) findViewById(R.id.viewPager);
        //添加医院和科室fragment到集合,默认给武汉
        mHospitalListFragment = new HospitalListFragment(mHospitalGroupList
                .get(0).getListHospital());
        mDepartmentListFragment = new DepartmentListFragment();
        mFragments.add(mHospitalListFragment);
        mFragments.add(mDepartmentListFragment);

        HospitalAndDepartmentAdapter adapter = new HospitalAndDepartmentAdapter
                (getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    private void initListener() {
        btnWH.setOnClickListener(this);
        btnQG.setOnClickListener(this);
        tvHospital.setOnClickListener(this);
        tvDepartment.setOnClickListener(this);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tvHospital.setTextColor(Color.BLUE);
                        tvDepartment.setTextColor(Color.BLACK);
                        break;
                    case 1:
                        tvHospital.setTextColor(Color.BLACK);
                        tvDepartment.setTextColor(Color.BLUE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnWH:
                mHospitalListFragment.setHospitalList(GetData.getHospitalListByName("武汉"));
                //清空列表适配器所选择的position
                mHospitalListFragment.clearAdapterSelectPosition();
                viewPager.setCurrentItem(0);
                viewPager.setCanScroll(false);//设置viewpager不能滚动
                break;
            case R.id.btnQG:
                mHospitalListFragment.setHospitalList(GetData.getHospitalListByName("全国"));
                //清空列表适配器所选择的position
                viewPager.setCurrentItem(0);
                mHospitalListFragment.clearAdapterSelectPosition();
                viewPager.setCanScroll(false);//设置viewpager不能滚动
                break;
            case R.id.tvHospital:
                tvHospital.setTextColor(Color.BLUE);
                tvDepartment.setTextColor(Color.BLACK);
                viewPager.setCurrentItem(0);
                break;
            case R.id.tvDepartment:
                if (mHospitalListFragment.getSelectedPosition() != -1) {
                    tvHospital.setTextColor(Color.BLACK);
                    tvDepartment.setTextColor(Color.BLUE);
                    viewPager.setCurrentItem(1);
                } else {
                    Toast.makeText(this, "请先选择医院", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void setViewPagerCanScroll() {
        viewPager.setCanScroll(true);
    }

    public void showDepartmentPage(int uid) {
        viewPager.setCurrentItem(1);
        mDepartmentListFragment.setDepartmentlList(GetData.getDepartmentListByUid(uid));
    }

    class HospitalAndDepartmentAdapter extends FragmentPagerAdapter {

        public HospitalAndDepartmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 1) {
            viewPager.setCurrentItem(0);
        } else {
            super.onBackPressed();
        }
    }
}

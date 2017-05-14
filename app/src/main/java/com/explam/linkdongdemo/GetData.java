package com.explam.linkdongdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xiemiao
 * Date: 2017-05-14
 * Time: 11:12
 * Desc:
 */
public class GetData {
    /**
     * 获取医院组
     */
    public static List<HospitalGroup> getHospitalGroupList() {
        List<HospitalGroup> list = new ArrayList<>();
        list.add(new HospitalGroup("武汉", getHospitalListByName("武汉")));
        list.add(new HospitalGroup("全国", getHospitalListByName("全国")));
        return list;
    }

    /**
     * 获取组下医院列表
     */
    public static List<Hospital> getHospitalListByName(String name) {
        List<Hospital> list = new ArrayList<>();
        switch (name) {
            case "武汉":
                list.add(new Hospital(0, "武汉红伞医院"));
                list.add(new Hospital(1, "武汉白捡医院"));
                break;
            case "全国":
                list.add(new Hospital(0, "武汉红伞医院"));
                list.add(new Hospital(1, "武汉白捡医院"));
                list.add(new Hospital(2, "北京蓝色医院"));
                list.add(new Hospital(3, "北京情色医院"));
                break;
        }
        return list;
    }

    /**
     * 获取医院下科室列表
     */
    public static List<Department> getDepartmentListByUid(int hospitalId) {
        List<Department> list = new ArrayList<>();
        switch (hospitalId) {
            case 0:
                for (int i = 0; i < 10; i++) {
                    list.add(new Department("第一牛叉科室" + i));
                }
                break;
            case 1:
                for (int i = 0; i < 6; i++) {
                    list.add(new Department("第二牛叉科室" + i));
                }
                break;
            case 2:
                for (int i = 0; i < 15; i++) {
                    list.add(new Department("第三牛叉科室" + i));
                }
                break;
            case 3:
                for (int i = 0; i < 12; i++) {
                    list.add(new Department("第四牛叉科室" + i));
                }
                break;
        }
        return list;
    }
}

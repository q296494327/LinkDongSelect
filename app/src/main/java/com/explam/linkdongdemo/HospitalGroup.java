package com.explam.linkdongdemo;

import java.io.Serializable;
import java.util.List;

/**
 * User: xiemiao
 * Date: 2017-05-14
 * Time: 11:00
 * Desc:
 */
public class HospitalGroup implements Serializable {
    private String groupname;
    private List<Hospital> listHospital;

    public HospitalGroup(String groupname, List<Hospital> listHospital) {
        this.groupname = groupname;
        this.listHospital = listHospital;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public List<Hospital> getListHospital() {
        return listHospital;
    }

    public void setListHospital(List<Hospital> listHospital) {
        this.listHospital = listHospital;
    }
}

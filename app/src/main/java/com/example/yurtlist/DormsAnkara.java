package com.example.yurtlist;

public class DormsAnkara {
    private String  name;
    private String province;
    private int imageResourceId;

    public DormsAnkara(String name, String province, int imageResourceId) {
        this.name = name;
        this.province = province;
        this.imageResourceId = imageResourceId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getProvince() {
        return province;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}

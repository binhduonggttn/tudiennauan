package com.mode;

import java.io.Serializable;

public class ObjectMonNgon implements Serializable {




    String id;
    String show;
    String name;
    String img;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public String getShow() {
        return show;
    }
    public void setShow(String show) {
        this.show = show;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



}

package com.nhom4web.model;

import com.google.gson.annotations.Expose;

public abstract class AbstractModel {
    @Expose private int ma;

    public AbstractModel() {
        this.ma = -1;
    }

    public AbstractModel(int ma) {
        this.ma = ma;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }
}

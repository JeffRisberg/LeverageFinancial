package com.example.android.sunshine.app.model;

/**
 * Created by jeff on 8/8/15.
 */
public class AccountDataRecord {
    String name;
    int value;
    String detail;

    public AccountDataRecord(String name, int value, String detail) {
        this.name = name;
        this.value = value;
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
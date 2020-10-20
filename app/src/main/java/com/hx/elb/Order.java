package com.hx.elb;

/**
 * Created by Hello on 2020/10/13.
 */

public class Order {
    private String id;
    private String UserPhone;
    private String context;

    public Order(String id, String userPhone, String context) {
        this.id = id;
        UserPhone = userPhone;
        this.context = context;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}

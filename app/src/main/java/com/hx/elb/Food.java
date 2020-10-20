package com.hx.elb;

/**
 * Created by Hello on 2020/10/13.
 */

public class Food {
    private String name;
    private int imageId;

    public Food(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}

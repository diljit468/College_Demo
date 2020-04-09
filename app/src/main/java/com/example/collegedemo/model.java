package com.example.collegedemo;

public class model {
    String name;
    int price,time;

    public model(String name, int price, int time) {
        this.name = name;
        this.price = price;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getTime() {
        return time;
    }
}

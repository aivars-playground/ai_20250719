package com.example.demo.service.generics;

public class Widget<T> {

    static void main() {
        Widget<String> widget1 = new Widget<String>();
        widget1.setType("abc");
        Widget<Integer> widget2 = new Widget<Integer>();
        widget2.setType(1);
        widget2.doSomething("abc");

    }

    private T type;
    public T getType() {
        return type;
    }
    public void setType(T type) {
        this.type = type;
    }
    public <T> void doSomething(T in) {
    }

}

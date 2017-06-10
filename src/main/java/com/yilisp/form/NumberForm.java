package com.yilisp.form;

/**
 * Created by wjj on 6/10/17.
 */
public class NumberForm implements Form {

    private Long num;

    public NumberForm(Long num) {
        this.num = num;
    }

    public Object eval() {
        return num;
    }

    @Override
    public String toString() {
        return num.toString();
    }
}

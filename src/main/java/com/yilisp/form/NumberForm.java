package com.yilisp.form;

import com.yilisp.env.Environment;

/**
 * Created by wjj on 6/10/17.
 */
public class NumberForm implements Form {

    private Long num;

    public NumberForm(Long num) {
        this.num = num;
    }

    public Object eval(Environment env) {
        return num;
    }

    @Override
    public String toString() {
        return num.toString();
    }

    @Override public int hashCode() {
        return num.hashCode();
    }

    @Override public boolean equals(Object obj) {
        return (obj instanceof NumberForm) && this.num .equals( ( (NumberForm) obj ).num );
    }
}

package com.yilisp.form;

import com.yilisp.env.Environment;

/**
 * Created by wjj on 6/10/17.
 */
public class BooleanForm implements Form {

    private Boolean bool;

    public BooleanForm(Boolean num) {
        this.bool = num;
    }

    public Object eval(Environment env) {
        return bool;
    }

    @Override public int hashCode() {
        return bool.hashCode();
    }

    @Override public boolean equals(Object obj) {
        return obj instanceof Boolean && bool.equals(obj);
    }

    @Override
    public String toString() {
        return bool.toString();
    }
}

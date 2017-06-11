package com.yilisp.form;

/**
 * Created by wjj on 6/10/17.
 */
public class SymbolForm implements Form {

    private final  String name;

    public SymbolForm(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "'" + this.name;
    }



    @Override
    public Object eval() {
        return null;
    }
}

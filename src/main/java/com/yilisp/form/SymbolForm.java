package com.yilisp.form;

import com.yilisp.env.Environment;

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

    @Override public int hashCode() {
        return this.name.hashCode();
    }

    @Override public boolean equals(Object obj) {
         if(obj instanceof SymbolForm && ((SymbolForm) obj).name.equals(this.name)){
            return true;
        }
        return false;
    }

    @Override
    public Object eval(Environment env) {
        return env.getValue(this);
    }
}

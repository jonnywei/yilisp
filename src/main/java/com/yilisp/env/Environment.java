package com.yilisp.env;

import com.yilisp.form.SymbolForm;

import java.util.HashMap;

/**
 *
 * environment
 *
 * Created by wjj on 6/11/17.
 */
public class Environment {
    private final HashMap<SymbolForm , Object> env = new HashMap<>();

    private Environment parent; //parent

    public Environment() {
    }

    public Environment(Environment parent) {
        this.parent = parent;
    }

    public Object getValue(SymbolForm symbolForm){
        Object result = env.get(symbolForm);
        if(parent != null &&  result == null ){
            return  parent.getValue(symbolForm);
        }
        return result;
    }

    public Object putValue(SymbolForm symbolForm,Object object){
        return env.put(symbolForm,object);
    }
}

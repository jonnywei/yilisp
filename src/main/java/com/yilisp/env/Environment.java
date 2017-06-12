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

    public Object getValue(SymbolForm symbolForm){

        return  env.get(symbolForm);
    }

    public Object putValue(SymbolForm symbolForm,Object object){
        return env.put(symbolForm,object);
    }
}

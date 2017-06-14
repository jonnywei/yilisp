package com.yilisp.env;

import com.yilisp.Function;
import com.yilisp.form.SymbolForm;

/**
 * Created by wjj on 6/11/17.
 */
public class BaseEnvironment {

    public  static class BuiltinFunction implements Function{

        private String name;

        public BuiltinFunction(String name) {
            this.name = name;
        }

        @Override public Object apply(Object... args) {
            return null;
        }

        @Override public Object eval(Environment env) {
            return this;
        }

        @Override public String toString() {
            return "<procedure:"  + name  + '>';
        }


    }

    public static class PLUS  extends BuiltinFunction{

        public PLUS(String name) {
            super(name);
        }

        @Override
        public Object apply(Object... args) {
            long result =0;
            for(Object arg : args){
                result +=  (Long) arg;
            }
            return result;
        }
    }

    public static class MULT  extends BuiltinFunction{

        public MULT(String name) {
            super(name);
        }

        @Override
        public Object apply(Object... args) {
            long result =1;
            for(Object arg : args){
                result *=  (Long) arg;
            }
            return result;
        }
    }


    public static Environment getBaseEnvironment(){
        Environment environment = new Environment();
        environment.putValue(new SymbolForm("+"),new PLUS("+"));
        environment.putValue(new SymbolForm("*"),new MULT("*"));
        return environment;
    }
}

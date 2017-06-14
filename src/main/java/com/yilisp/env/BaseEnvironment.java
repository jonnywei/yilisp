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


    public static class MINUS  extends BuiltinFunction{

        public MINUS(String name) {
            super(name);
        }

        @Override
        public Object apply(Object... args) {
            if(args.length ==1 ){
                return  - (long)args[0];
            }
            long arg0 = (Long) args[0];
            long result = 0;
            for(Object arg : args){
                result -=  (Long) arg;
            }
            return result + arg0;
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



    public static class DIV  extends BuiltinFunction{

        public DIV(String name) {
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




    public static class EQUAL  extends BuiltinFunction{

        public EQUAL(String name) {
            super(name);
        }

        @Override
        public Object apply(Object... args) {
            Long last = (Long) args[0];
            for(Object arg : args){
                if(!last.equals( arg)){
                    return false;
                }
            }
            return Boolean.TRUE;
        }
    }






    public static class GT  extends BuiltinFunction{

        public GT(String name) {
            super(name);
        }

        @Override
        public Object apply(Object... args) {
            Long last = (Long) args[0];
            for(Object arg : args){
                Long current = (Long) arg;
                if(last.longValue() < current.longValue() ){
                    return false;
                }
            }
            return true;
        }
    }





    public static class LT  extends BuiltinFunction{

        public LT (String name) {
            super(name);
        }

        @Override
        public Object apply(Object... args) {
            Long last = (Long) args[0];
            for(Object arg : args){
                Long current = (Long) arg;
                if(last.longValue() > current.longValue() ){
                    return false;
                }
            }
            return true;
        }
    }



    public static class PRINTLN  extends BuiltinFunction{

        public PRINTLN (String name) {
            super(name);
        }

        @Override
        public Object apply(Object... args) {

            for(Object arg : args){
                System.out.print(arg);
                System.out.print(" ");
            }
            System.out.println();
            return args[args.length-1];
        }
    }




    public static Environment getBaseEnvironment(){
        Environment environment = new Environment();

        environment.putValue(new SymbolForm("+"),new PLUS("+"));
        environment.putValue(new SymbolForm("-"),new MINUS("-"));
        environment.putValue(new SymbolForm("*"),new MULT("*"));
        environment.putValue(new SymbolForm("/"),new DIV("/"));


        environment.putValue(new SymbolForm(">"),new GT(">"));
        environment.putValue(new SymbolForm("="),new EQUAL("="));
        environment.putValue(new SymbolForm("<"),new LT("<"));

        environment.putValue(new SymbolForm("println"),new PRINTLN("println"));


        return environment;
    }
}

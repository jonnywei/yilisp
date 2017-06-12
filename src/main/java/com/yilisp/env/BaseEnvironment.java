package com.yilisp.env;

import com.yilisp.Function;
import com.yilisp.form.SymbolForm;

/**
 * Created by wjj on 6/11/17.
 */
public class BaseEnvironment {

    public static class PLUS  implements Function{

        @Override
        public Object apply(Object... args) {
            long result =0;
            for(Object arg : args){
                result +=  (Long) arg;
            }
            return result;
        }
    }



    public static Environment getBaseEnvironment(){
        Environment environment = new Environment();
        environment.putValue(new SymbolForm("+"),new PLUS());
        return environment;
    }
}

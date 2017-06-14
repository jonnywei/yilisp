package com.yilisp;

import com.yilisp.env.Environment;

/**
 * Created by wjj on 6/11/17.
 */
public interface Function {
    /**
     * apply function
     * @param args
     * @return
     */
    Object apply (Object... args);


    Object eval(Environment env);
}

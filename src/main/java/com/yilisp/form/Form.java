package com.yilisp.form;

import com.yilisp.env.Environment;

/**
 * Created by wjj on 6/10/17.
 */
public interface Form {
    /**
     * eval
     * @return
     */
    Object eval(Environment env);
}

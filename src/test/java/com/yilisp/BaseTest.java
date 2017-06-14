package com.yilisp;

import com.yilisp.env.Environment;
import com.yilisp.form.Form;
import com.yilisp.form.ListForm;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jianjunwei on 2017/6/14.
 */
public class BaseTest {


    public InputStream stringToInpuStream(String s){
        return    new ByteArrayInputStream(s.getBytes());
    }


    public ListForm readLisp (String expression){
        InputStream is = stringToInpuStream(expression);
        try {
            ListForm result  = Reader.read(is);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object  evalResult (ListForm form,  Environment environment){
        Object output = null;
        for(Form x :  form){
            output = x.eval(environment);
        }
        return output;
    }
}

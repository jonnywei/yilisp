package com.yilisp;

import com.yilisp.env.BaseEnvironment;
import com.yilisp.env.Environment;
import com.yilisp.form.Form;
import com.yilisp.form.ListForm;
import com.yilisp.form.NumberForm;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianjunwei on 2017/6/12.
 */
public class EvalTest {

    public InputStream stringToInpuStream(String s){
        return    new ByteArrayInputStream(s.getBytes());
    }


    @Test
    public void testPlus () throws IOException {
        String expression = "(+  3434 555 )";
        InputStream is = stringToInpuStream(expression);
        ListForm result  = Reader.read(is);
        Environment environment = BaseEnvironment.getBaseEnvironment();

        ListForm form = result;
        Object output = null;
        while (form.car != null){
            output = form.car.eval(environment);
            form = form.cdr;
        }

        Assert.assertEquals(new Long(3989L), output);
    }


    @Test
    public void testMulti () throws IOException {
        String expression = "(* 3 9 )";
        InputStream is = stringToInpuStream(expression);
        ListForm result  = Reader.read(is);
        Environment environment = BaseEnvironment.getBaseEnvironment();

        ListForm form = result;
        Object output = null;
        while (form.car != null){
            output = form.car.eval(environment);
            form = form.cdr;
        }

        Assert.assertEquals(new Long(27L), output);
    }



    @Test
    public void testAll () throws IOException {
        String expression = "(+ 4  (* 3 9  ) 3)";
        InputStream is = stringToInpuStream(expression);
        ListForm result  = Reader.read(is);
        Environment environment = BaseEnvironment.getBaseEnvironment();

        ListForm form = result;
        Object output = null;
        while (form.car != null){
            output = form.car.eval(environment);
            form = form.cdr;
        }

        Assert.assertEquals(new Long(34L), output);
    }



    @Test
    public void testDefine  () throws IOException {
        String expression = "(define pi  (* 3 9  ) )   (+ 4  pi 3)";
        InputStream is = stringToInpuStream(expression);
        ListForm result  = Reader.read(is);
        Environment environment = BaseEnvironment.getBaseEnvironment();

        ListForm form = result;
        Object output = null;
        while (form.car != null){
            output = form.car.eval(environment);
            form = form.cdr;
        }

        Assert.assertEquals(new Long(34L), output);
    }



    @Test
    public void testMultiDefine  () throws IOException {
        String expression = "(define b  (* 3 9  ) )  (define pi 3 ) (+ 4  b pi)";
        InputStream is = stringToInpuStream(expression);
        ListForm result  = Reader.read(is);
        Environment environment = BaseEnvironment.getBaseEnvironment();

        ListForm form = result;
        Object output = null;
        while (form.car != null){
            output = form.car.eval(environment);
            form = form.cdr;
        }

        Assert.assertEquals(new Long(34L), output);
    }
}

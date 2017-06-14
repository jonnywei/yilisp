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
public class EvalTest extends BaseTest {

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


//
    //    @Test
    //    public void testQuote  () throws IOException {
    //        String expression = "( (quote +) 3  4)";
    //        ListForm result  =readLisp(expression);
    //        Environment environment = BaseEnvironment.getBaseEnvironment();
    //        Object output = evalResult(result, environment);
    //        Assert.assertEquals(new Long(7L), output);
    //    }

    @Test
    public void testQuoteList  () throws IOException {
        String expression = "( quote  aaa)";

        ListForm result  =readLisp(expression);
        Environment environment = BaseEnvironment.getBaseEnvironment();
        Object output = evalResult(result, environment);

        Assert.assertEquals("'aaa", output);
    }

    @Test
    public void testQuoteNumber  () throws IOException {
        String expression = "( quote  333)";

        ListForm result  =readLisp(expression);
        Environment environment = BaseEnvironment.getBaseEnvironment();
        Object output = evalResult(result, environment);
        NumberForm numberForm = new NumberForm(333L);
        Assert.assertEquals(numberForm, output);
    }
}

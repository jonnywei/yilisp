package com.yilisp.form;

import com.yilisp.BaseTest;
import com.yilisp.env.BaseEnvironment;
import com.yilisp.env.Environment;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jianjunwei on 2017/6/14.
 */
public class SpecialFormTest extends BaseTest {


//    @Test
//    public  void testLength1(){
//        String expression = "( quote  aaa)";
//        ListForm result  = readLisp(expression);
//        Form car = result.car;
//        Assert.assertEquals(2L,  ((ListForm)result.car).length());
//    }

    @Test
    public  void testIfSpecialForm(){
        String expression = "( if #t 3 5 )";
        ListForm result  = readLisp(expression);
        Object xx = evalResult(result, new Environment());
        Assert.assertEquals(3L,  xx);
    }





    @Test
    public  void testIfSpecialFormNullList(){
        String expression = "( if () 3 5 )";
        ListForm result  = readLisp(expression);
        Object xx = evalResult(result, new Environment());
        Assert.assertEquals(5L,  xx);
    }


    @Test
    public  void testIfSpecialDefineSymbol(){
        String expression = "(define a 10 ) ( if a a 5 )";
        ListForm result  = readLisp(expression);
        Object xx = evalResult(result, BaseEnvironment.getBaseEnvironment());
        Assert.assertEquals(10L,  xx);
    }



    @Test
    public  void testLambdaSpecialForm(){
        String expression = "( (lambda  (xxx yy) ( * xxx yy))  3 4)";
        ListForm result  = readLisp(expression);
        Object xx = evalResult(result, BaseEnvironment.getBaseEnvironment());
        Assert.assertEquals(12L,  xx);
    }


    @Test
    public  void testDefineLambdaSpecialForm(){
        String expression = "(define xy  (lambda  (xxx yy) ( * xxx yy)) ) ( xy  3 4)";
        ListForm result  = readLisp(expression);
        Object xx = evalResult(result, BaseEnvironment.getBaseEnvironment());
        Assert.assertEquals(12L,  xx);
    }


    @Test
    public  void testLambdaSpecialFormMutibody(){
        String expression = "( (lambda  (xxx yy) ( * xxx yy) (+ xxx yy) )  3 4)";
        ListForm result  = readLisp(expression);
        Object xx = evalResult(result, BaseEnvironment.getBaseEnvironment());
        Assert.assertEquals(7L,  xx);
    }
}

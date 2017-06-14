package com.yilisp.form;

import com.yilisp.BaseTest;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by jianjunwei on 2017/6/14.
 */
public class ListFormTest extends BaseTest {


//    @Test
//    public  void testLength1(){
//        String expression = "( quote  aaa)";
//        ListForm result  = readLisp(expression);
//        Form car = result.car;
//        Assert.assertEquals(2L,  ((ListForm)result.car).length());
//    }

    @Test
    public  void testEmptyLength(){
        String expression = "(  )";
        ListForm result  = readLisp(expression);
        Assert.assertEquals(0L,  ((ListForm)result.car).length());
    }


    @Test
    public  void testLength2(){
        String expression = "( (dd)  aaa (dafdsa) )";
        ListForm result  = readLisp(expression);
        Assert.assertEquals(3L,  ((ListForm)result.car).length());
    }
}

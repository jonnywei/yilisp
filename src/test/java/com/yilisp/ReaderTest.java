package com.yilisp;

import com.yilisp.form.*;
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

public class ReaderTest extends BaseTest {

    public InputStream stringToInpuStream(String s){
        return    new ByteArrayInputStream(s.getBytes());
    }

    @Test
    public void testReadNumber () throws IOException {
        String expression = "1234";
        InputStream is = stringToInpuStream(expression);
        ListForm result  = Reader.read(is);

        List<Form> list = new ArrayList<>();
        list.add(new NumberForm(1234L));
        Form expect = ListForm.list(list);

         Assert.assertEquals(expect, result);
    }

    @Test
    public void testReadNegativeNumber () throws IOException {
        String expression = "-1234";

        ListForm result  = readLisp(expression);

        List<Form> list = new ArrayList<>();
        list.add(new NumberForm(-1234L));
        Form expect = ListForm.list(list);

        Assert.assertEquals(expect, result);
    }

    @Test
    public void testReadBoolean () throws IOException {
        String expression = "#t";
        InputStream is = stringToInpuStream(expression);
        ListForm result  = Reader.read(is);

        List<Form> list = new ArrayList<>();
        list.add(new BooleanForm(true));
        Form expect = ListForm.list(list);

        Assert.assertEquals(expect, result);
    }


    @Test
    public void testReadEmptyList () throws IOException {
        String expression = "()";
        InputStream is = stringToInpuStream(expression);
        ListForm result  = Reader.read(is);

        List<Form> list = new ArrayList<>();
        list.add(  ListForm.EMPTY);
        Form expect = ListForm.list(list);

        Assert.assertEquals(expect, result);
    }


    @Test
    public void testReadWhitespace () throws IOException {
        String expression = "   (   )";
        InputStream is = stringToInpuStream(expression);
        ListForm result  = Reader.read(is);

        List<Form> list = new ArrayList<>();
        list.add(  ListForm.EMPTY);
        Form expect = ListForm.list(list);

        Assert.assertEquals(expect, result);
    }


    @Test
    public void testReadList() throws IOException {
        String expression = "   (  123  3333 443434  )";
        InputStream is = stringToInpuStream(expression);
        ListForm result  = Reader.read(is);

        List<Form> list = new ArrayList<>();
        list.add(  new NumberForm(123L));
        list.add(  new NumberForm(3333L));
        list.add(  new NumberForm(443434L));

        Form a = ListForm.list(list);

        List<Form> b = new ArrayList<>();
        b.add(a);

        Form expect = ListForm.list(b);

        Assert.assertEquals(expect, result);
    }




    @Test
    public void testReadSymbol() throws IOException {
        String expression = "foo";
        InputStream is = stringToInpuStream(expression);
        ListForm result  = Reader.read(is);

        List<Form> list = new ArrayList<>();
        list.add(  new SymbolForm("foo"));

        Form expect = ListForm.list(list);

        Assert.assertEquals(expect, result);
    }


    @Test
    public void testMultiForm() throws IOException {
        String expression = "foo (12 3)";
        InputStream is = stringToInpuStream(expression);
        ListForm result  = Reader.read(is);

        List<Form> list = new ArrayList<>();
        list.add(  new SymbolForm("foo"));


        Form a = ListForm.list(list);

        List<Form> blist = new ArrayList<>();
        blist.add(  new NumberForm(12L));
        blist.add(  new NumberForm(3L));

        Form b = ListForm.list(blist);

        List<Form> last = new ArrayList<>();
        last.add( new SymbolForm("foo"));
        last.add(b);
        Form expect = ListForm.list(last);

        Assert.assertEquals(expect, result);
    }


}

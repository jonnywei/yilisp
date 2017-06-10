package com.yilisp;

import com.yilisp.form.Form;
import com.yilisp.form.ListForm;
import com.yilisp.form.NumberForm;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

/**
 * Created by wjj on 6/10/17.
 */
public class Reader {


    public static Form readForm(InputStream inputStream) throws IOException {

        PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream);
        pushbackInputStream.mark(1);

        readWhitespace(pushbackInputStream);
        char ch = (char) pushbackInputStream.read();

        if(Character.isDigit(ch)){
            pushbackInputStream.unread(ch);
            return  readNumber(pushbackInputStream);
        }else  if(ch == '('){
            return readList(pushbackInputStream);
        }

        else {
            throw new IllegalArgumentException("Illegal character: " + ch);
        }

//        return null;
    }



    public static ListForm readList(PushbackInputStream pushbackInputStream) throws IOException {


        readWhitespace(pushbackInputStream); //
        char ch = (char) pushbackInputStream.read();
        if(Character.isDigit(ch)){
            pushbackInputStream.unread(ch);

            NumberForm numberForm  =  readNumber(pushbackInputStream);
            ListForm form = readList(pushbackInputStream);
            return  form.cons(numberForm);
        }else  if(ch == '('){

            ListForm lform =readList(pushbackInputStream);
            ListForm form = readList(pushbackInputStream);
            return form.cons(lform);

        }else if(ch == ')'){
            return ListForm.EMPTY ;
        }
        else {
            throw new IllegalArgumentException("Illegal character: " + ch);
        }

    }


    public static NumberForm readNumber(PushbackInputStream pushbackInputStream) throws IOException {

        StringBuilder sb = new StringBuilder();

        char ch = (char) pushbackInputStream.read();

        while (Character.isDigit(ch)){
            sb.append(ch);
            ch = (char) pushbackInputStream.read();
        }

        return new NumberForm(Long.valueOf(sb.toString(), 10));
    }

    /**
     * process whitespace
     * @param pstream
     * @throws IOException
     */
    public static void readWhitespace(PushbackInputStream pstream) throws IOException {

        char ch = (char) pstream.read();
        while (Character.isWhitespace(ch)){
            ch = (char) pstream.read();
        }
        pstream.unread(ch);

    }
}

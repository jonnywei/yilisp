package com.yilisp;

import com.yilisp.form.Form;
import com.yilisp.form.ListForm;
import com.yilisp.form.NumberForm;
import com.yilisp.form.SymbolForm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wjj on 6/10/17.
 */
public class Reader {



    public static Form read(InputStream inputStream) throws IOException {

         return  read(new PushbackReader(new InputStreamReader(inputStream)));

    }


    public static ListForm read(PushbackReader pushbackReader) throws IOException{

        List<Form> formList = new ArrayList<>();
        readWhitespace(pushbackReader);
        char ch = (char) pushbackReader.read();
        while ((byte)ch != -1){
            pushbackReader.unread(ch);
            formList.add(readForm(pushbackReader));
             ch = (char) pushbackReader.read();

        }
        return ListForm.list(formList);

    }

    public static Form readForm(PushbackReader pushbackReader) throws IOException{

        readWhitespace(pushbackReader);
        char ch = (char) pushbackReader.read();

        if(Character.isDigit(ch)){
            pushbackReader.unread(ch);
            return  readNumber(pushbackReader);
        }else  if(ch == '('){
            return readList(pushbackReader);
        }else if(CharUtil.isSymbolChar(ch)) {
            pushbackReader.unread(ch);
            return readSymbol(pushbackReader);
        }//
        else {
            throw new IllegalArgumentException("Illegal character: " + ch);
        }

    }


    public static ListForm readList(PushbackReader reader) throws IOException {


        readWhitespace(reader); //
        char ch = (char) reader.read();
        if(ch == ')'){
            return ListForm.EMPTY ;
        }else {
            reader.unread(ch);
        }
        Form form1 = readForm(reader);
        ListForm form = readList(reader);
        return  form.cons(form1);

//        if(Character.isDigit(ch)){
//            reader.unread(ch);
//
//            NumberForm numberForm  =  readNumber(reader);
//            ListForm form = readList(reader);
//            return  form.cons(numberForm);
//        }else  if(ch == '('){
//
//            ListForm lform =readList(reader);
//            ListForm form = readList(reader);
//            return form.cons(lform);
//
//        }else if(ch == ')'){
//            return ListForm.EMPTY ;
//        }else {
//            reader.unread(ch);
//            SymbolForm  symbolForm = readSymbol(reader);
//            ListForm form = readList(reader);
//            return  form.cons(symbolForm);
//        }

//        throw new IllegalArgumentException("Illegal character: " + ch);


    }


    public static NumberForm readNumber(PushbackReader reader) throws IOException {

        StringBuilder sb = new StringBuilder();

        char ch = (char) reader.read();

        while (Character.isDigit(ch)){
            sb.append(ch);
            ch = (char) reader.read();
        }
//        if(!Character.is(ch)){
//            throw new IllegalArgumentException("Illegal character: " + ch);
//        }
        reader.unread(ch);
        return new NumberForm(Long.valueOf(sb.toString(), 10));
    }

    /**
     * process whitespace
     * @param reader
     * @throws IOException
     */
    public static void readWhitespace(PushbackReader reader) throws IOException {

        char ch = (char) reader.read();
        while (Character.isWhitespace(ch)){
            ch = (char) reader.read();
        }
        reader.unread(ch);

    }



    public static SymbolForm readSymbol(PushbackReader reader) throws IOException {

        StringBuilder sb = new StringBuilder();

        char ch = (char) reader.read();

        while (CharUtil.isSymbolChar(ch)) {
            sb.append(ch);
            ch = (char) reader.read();
        }
        reader.unread(ch);
        return new SymbolForm(sb.toString());
    }
}

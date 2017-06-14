package com.yilisp;

import com.yilisp.form.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wjj on 6/10/17.
 */
public class Reader {



    public static ListForm read(InputStream inputStream) throws IOException {

         return  read(new PushbackReader(new InputStreamReader(inputStream)));

    }


    private static ListForm read(PushbackReader pushbackReader) throws IOException{

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

    private static Form readForm(PushbackReader pushbackReader) throws IOException{

        readWhitespace(pushbackReader);
        char ch = (char) pushbackReader.read();

        if(Character.isDigit(ch)){
            pushbackReader.unread(ch);
            return  readNumber(pushbackReader);
        }else  if(ch == '('){
            return SpecialForm.check( readList(pushbackReader));
        }else if(ch =='#' ) {
//            pushbackReader.unread(ch);
            return readBoolean(pushbackReader);
        }else if(CharUtil.isSymbolChar(ch)) {
            pushbackReader.unread(ch);
            return readSymbol(pushbackReader);
        }
        else {
            throw new IllegalArgumentException("Illegal character: " + ch);
        }

    }


    private static ListForm readList(PushbackReader reader) throws IOException {


        readWhitespace(reader); //
        char ch = (char) reader.read();
        if(ch == ')'){
            return ListForm.EMPTY ;
        }else if( (byte)ch == -1){
            throw new IllegalArgumentException("EOF read before closing list");
        }
        else {
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


    private static NumberForm readNumber(PushbackReader reader) throws IOException {

        StringBuilder sb = new StringBuilder();

        char ch = (char) reader.read();
//        if(ch =='-' || Character.isDigit(ch) ){
//            sb.append(ch);
//            ch = (char) reader.read();
//        }
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
    private static void readWhitespace(PushbackReader reader) throws IOException {

        char ch = (char) reader.read();
        while (Character.isWhitespace(ch)){
            ch = (char) reader.read();
        }
        reader.unread(ch);

    }



    private static SymbolForm readSymbol(PushbackReader reader) throws IOException {

        StringBuilder sb = new StringBuilder();

        char ch = (char) reader.read();

        while (CharUtil.isSymbolChar(ch)) {
            sb.append(ch);
            ch = (char) reader.read();
        }
        reader.unread(ch);
        return new SymbolForm(sb.toString());
    }


    private static BooleanForm readBoolean(PushbackReader reader) throws IOException {

        Boolean  aBoolean =  Boolean.FALSE;

        char ch = (char) reader.read();

        if (ch =='t' || ch =='T'){
            aBoolean = Boolean.TRUE;
        }
        if (ch =='f' || ch =='F'){
            aBoolean = Boolean.FALSE;
        }
        ch = (char) reader.read();

        if(!CharUtil.isEndBoolean(ch)){
            throw new IllegalArgumentException("Illegal character: " + ch);
        }
        reader.unread(ch);
        return new BooleanForm(aBoolean);
    }

}

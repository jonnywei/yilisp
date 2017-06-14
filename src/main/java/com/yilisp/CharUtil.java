package com.yilisp;

/**
 * Created by wjj on 6/11/17.
 */
public class CharUtil {

    public static boolean isSymbolChar(char ch){
//        if(ch >='a' && ch <='z' || ch >='A' && ch <='Z' ||
//                ch=='_' || ch =='-' || ch=='+' || ch == '*'){
//            return true;
//        }
        if(ch =='(' || ch == ')' || Character.isWhitespace(ch) || (byte)ch == -1) {
            return  false;
        }
        return  true;
    }

    public static boolean isEndBoolean(char ch){
        if(Character.isWhitespace(ch) || ch == ')'  || ch == '(' || (byte)ch == -1){
            return true;
        }
        return  false;
    }
}

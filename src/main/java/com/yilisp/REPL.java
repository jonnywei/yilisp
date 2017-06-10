package com.yilisp;

import com.yilisp.form.Form;

import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * 1, Read program text and return tree of expressions(AST)
 * 2, evaluate tree
 * 3, print
 * 4, loop
 * Created by wjj on 6/10/17.
 */
public class REPL {


    private static  Reader reader ;

    public static Form read() throws IOException {

       Console console =  System.console();

        Scanner scanner = new Scanner(System.in);

        System.out.print("->");

       String line = scanner.nextLine();

       System.out.println(line);

       return Reader.readForm(new ByteArrayInputStream(line.getBytes("UTF-8")));


    }

    public static Form read2() throws IOException {

        String line = "(  333  444 ( 777 8888 ) )";


        return Reader.readForm(new ByteArrayInputStream(line.getBytes("UTF-8")));


    }
    public static void main(String[] args) {
        while (true){

            try{
                Form form =  read();
                Object output = form.eval();
                System.out.println(output);

            }catch (Exception e){
                System.err.println(e);
            }
        }


    }
}

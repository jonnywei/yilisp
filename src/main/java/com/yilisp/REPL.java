package com.yilisp;

import com.yilisp.env.BaseEnvironment;
import com.yilisp.env.Environment;
import com.yilisp.form.Form;
import com.yilisp.form.ListForm;

import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.EOFException;
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

    public static ListForm read() throws IOException {

       Console console =  System.console();

        Scanner scanner = new Scanner(System.in);

        System.out.print("->");

       String line = scanner.nextLine();

       System.out.println(line);

       return Reader.read(new ByteArrayInputStream(line.getBytes("UTF-8")));


    }

    public static ListForm read2() throws IOException {

        String line = "(  333  444 ( 777 8888 ) )";


        return Reader.read(new ByteArrayInputStream(line.getBytes("UTF-8")));


    }
    public static void main(String[] args) {
        Environment environment = BaseEnvironment.getBaseEnvironment();
        while (true)
            try {
                ListForm form = read();
                Object output = null;
                while (form.car != null){
                      output = form.car.eval(environment);
                      form = form.cdr;
                }

                System.out.println(output);

            } catch (EOFException e) {
                return;
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }


    }
}

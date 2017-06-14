package com.yilisp;

import com.yilisp.env.BaseEnvironment;
import com.yilisp.env.Environment;
import com.yilisp.form.Form;
import com.yilisp.form.ListForm;

import java.io.*;
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

    public static void runYilisp(String file) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(file);
        Environment environment = BaseEnvironment.getBaseEnvironment();
        try {
            ListForm lform =Reader.read(fileInputStream);
            Object output = null;
            for(Form f : lform){
                output = f.eval(environment);
            }
            System.out.println(output);
        } catch (EOFException e) {
            return;
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] files) {
        if(files.length == 0){
            startREPL();
        }else {
            for (int i = 0; i < (files == null ? 0 : files.length); i++) {
                try {
                    runYilisp(files[i]);
                }catch (Exception e){
                    System.out.println(e);
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    private static void startREPL() {
        Environment environment = BaseEnvironment.getBaseEnvironment();
        while (true){
            try {
                ListForm lform = read();
                Object output = null;

                for(Form f : lform){
                    output = f.eval(environment);
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
}

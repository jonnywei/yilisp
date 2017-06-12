package com.yilisp.form;

import com.yilisp.Function;
import com.yilisp.env.Environment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wjj on 6/10/17.
 */
public class ListForm implements Form {

    public static final ListForm EMPTY = new ListForm(null, null);

    public Form car;

    public Form cdr;

    public ListForm() {
        car = null;
        cdr = null;
    }

    public ListForm(Form car, Form cdr) {
        this.car = car;
        this.cdr = cdr;
    }

    public ListForm cons(Form form) {
        return new ListForm(form, this);
    }


    public static ListForm list(List<Form> forms){
        ListForm l = ListForm.EMPTY;
        for(int i= forms.size()-1; i >=0;  i--){
            l = l.cons(forms.get(i));
        }
        return l;
    }

    public Object eval(Environment env) {
        Function function = (Function) this.car;
        List<Object> args = new ArrayList<Object>();
        ListForm cdr = (ListForm)this.cdr;
        while (cdr != EMPTY){
            args.add(cdr.eval(env));
            cdr = (ListForm) cdr.cdr;
        }

        return function.apply(args);
    }

    @Override
    public String toString() {
        if(this == EMPTY){
            return "()";
        }
        return "(" +
                " " +   car +
                " , " + cdr +
                " )";
    }
}

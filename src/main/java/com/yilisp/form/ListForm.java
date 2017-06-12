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

    public ListForm cdr;

    public ListForm() {
        car = null;
        cdr = null;
    }

    public ListForm(Form car, ListForm cdr) {
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
        Function function = (Function) this.car.eval(env);
        if(function == null){
            throw  new UnsupportedOperationException( car.toString() + " no such function");
        }
        List<Object> args = new ArrayList<Object>();
        ListForm cdr = (ListForm)this.cdr;
        while (cdr != EMPTY){
            args.add(cdr.car.eval(env));
            cdr = (ListForm) cdr.cdr;
        }

        return function.apply(args.toArray());
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

    @Override
    public boolean equals(Object obj) {

        if(! (obj instanceof ListForm) ) {
            return false;
        }
        if( this == EMPTY && obj == EMPTY){
            return true;
        }
        ListForm that = (ListForm) obj;

        if(this.cdr ==EMPTY && that.cdr != EMPTY ){
            return false;
        }
//        System.out.println(this.car.equals(that.car));
        return this.car.equals(that.car) && this.cdr.equals(that.cdr);

    }
}

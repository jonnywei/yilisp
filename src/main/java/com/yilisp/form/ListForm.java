package com.yilisp.form;

/**
 * Created by wjj on 6/10/17.
 */
public class ListForm implements Form {

    public static final ListForm EMPTY = new ListForm(null, null);

    private Form car;

    private Form cdr;

    public ListForm() {
        car = null;
        cdr = null;
    }

    public ListForm(Form car, Form cdr) {
        this.car = car;
        this.cdr = cdr;
    }

    public Form getCar() {
        return car;
    }

    public void setCar(Form car) {
        this.car = car;
    }

    public Form getCdr() {
        return cdr;
    }

    public void setCdr(Form cdr) {
        this.cdr = cdr;
    }

    public ListForm cons(Form form) {
        return new ListForm(form, this);
    }

    public Object eval() {
        return toString();
    }

    @Override
    public String toString() {
        if(this == EMPTY){
            return "()";
        }
        return "(" +
                " " +  car +
                ", " + cdr +
                ')';
    }
}

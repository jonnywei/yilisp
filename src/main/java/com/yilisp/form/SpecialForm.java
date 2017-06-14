package com.yilisp.form;

import com.yilisp.env.Environment;

/**
 * Created by jianjunwei on 2017/6/12.
 */
public class SpecialForm   implements Form{

    private  static  String DEFINE = "define";
    private  static   SymbolForm DEFINE_SYMBOL_FORM = new SymbolForm(DEFINE);

    static  String QUOTE = "quote";
    private  static   SymbolForm QUOTE_SYMBOL_FORM = new SymbolForm(QUOTE);

    @Override public Object eval(Environment env) {
        return null;
    }


    public static class DefineSpecialForm extends SpecialForm {

        protected final ListForm form;

        private DefineSpecialForm(ListForm form) {
            this.form = form;
        }

        @Override public Object eval(Environment env) {
            SymbolForm symbol = (SymbolForm) this.form.cdr.car;
            env.putValue(symbol, this.form.cdr.cdr.car.eval(env));
            return null;
        }

        @Override public String toString() {
            return form.toString();
        }
    }


    public static class QuoteSpecialForm extends SpecialForm {

        protected final ListForm form;

        private QuoteSpecialForm(ListForm form) {
            this.form = form;
        }

        @Override public Object eval(Environment env) {
//            SymbolForm symbol = (SymbolForm) this.form.cdr.car;
//            env.putValue(symbol, this.form.cdr.cdr.car.eval(env));
            Form a = this.form.cdr.car;
            if(this.form.cdr.cdr != ListForm.EMPTY){
                throw  new IllegalArgumentException("quote part number must be 1");
            }
            return a;
        }

        @Override public String toString() {
            return form.toString();
        }
    }



    public static Form check(ListForm l){
        if(l == ListForm.EMPTY){
            return l;
        }
        if(l.car.equals(SpecialForm.DEFINE_SYMBOL_FORM)){
            return new DefineSpecialForm(l);
        }else if (l.car.equals(SpecialForm.QUOTE_SYMBOL_FORM)){
            return new QuoteSpecialForm(l);
        }
        return l;
    }


}

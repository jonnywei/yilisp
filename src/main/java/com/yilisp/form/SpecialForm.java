package com.yilisp.form;

import com.yilisp.env.Environment;

/**
 * Created by jianjunwei on 2017/6/12.
 */
public class SpecialForm   implements Form{

    private  static  String DEFINE = "define";
    private  static   SymbolForm DEFINE_SYMBOL_FORM = new SymbolForm(DEFINE);

    private static  String QUOTE = "quote";
    private  static   SymbolForm QUOTE_SYMBOL_FORM = new SymbolForm(QUOTE);

    private static  String IF = "if";
    private  static   SymbolForm IF_SYMBOL_FORM = new SymbolForm(IF);

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

    //(if test-expr then-expr else-expr)

    public static class IfSpecialForm extends SpecialForm {

        protected final ListForm form;

        private IfSpecialForm(ListForm form) {
            this.form = form;
        }

        @Override public Object eval(Environment env) {
            if(this.form.length() != 4){
                throw  new IllegalArgumentException("if part number must be 3");
            }
            Form test_expr = this.form.cdr.car;  //second
            Form then_expr = this.form.cdr.cdr.car; //third
            Form else_expr = this.form.cdr.cdr.cdr.car; //four

            boolean testVal = true;
            if(test_expr instanceof BooleanForm){
               BooleanForm d =  (BooleanForm)  test_expr;
                testVal = (Boolean) d.eval(env);
            }else  if(test_expr instanceof  ListForm && test_expr ==ListForm.EMPTY){
                testVal = false;
            }else  if(test_expr instanceof  NumberForm){
                testVal =  ((Long) (  ( (NumberForm)test_expr ).eval(env) ) ).longValue() != 0;
            }

            if(testVal){
                return  then_expr.eval(env);
            }else {
                return  else_expr.eval(env);
            }
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
        }else if (l.car.equals(SpecialForm.QUOTE_SYMBOL_FORM)){
            return new QuoteSpecialForm(l);
        }else if (l.car.equals(SpecialForm.IF_SYMBOL_FORM)){
            return new IfSpecialForm(l);
        }
        return l;
    }


}

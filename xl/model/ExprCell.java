package xl.model;

import java.io.IOException;

import xl.expr.Environment;
import xl.expr.Expr;
import xl.expr.ExprParser;

public class ExprCell implements Cell {

    private Expr expr;

    public ExprCell(Environment env, String expr) {

        ExprParser parser = new ExprParser();

        try {
            this.expr = parser.build(expr);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public double getValue(Environment env) {

    double value = expr.value(env);

    return value;

    }

    @Override
    public String getValueAsString(Environment env) {
        return "";
    }

    @Override
    public String getFormula() {
        return "";
    }

}

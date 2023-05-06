package xl.model;

import xl.expr.*;

public class ExprCell implements Cell {

    private Expr expr;

    public ExprCell(Expr expr) {
        this.expr = expr;
    }

    @Override
    public double getValue(Environment e) {
        return 0;
    }

    @Override
    public String getValueAsString(Environment e) {
        return "";
    }

    @Override
    public String getFormula() {
        return "";
    }

}

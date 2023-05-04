package xl.model;

import xl.expr.Environment;

public class ExprCell implements Cell {

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

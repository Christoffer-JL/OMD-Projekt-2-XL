package xl.model;

import xl.expr.Environment;
import xl.util.XLException;

public class BombCell implements Cell {

    @Override
    public double getValue(Environment e) {
        throw new XLException("Circular reference detected");
    }

    @Override
    public String getValueAsString(Environment e) {
        throw new XLException("Circular reference detected");
    }

    @Override
    public String getFormula() {
        throw new XLException("Circular reference detected");
    }
}

package xl.model;

import xl.expr.Environment;

public interface Cell {

    public double getValue(Environment e);

    public String getValueAsString(Environment e);

    public String getFormula();

}

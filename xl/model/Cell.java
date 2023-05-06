package xl.model;

import xl.expr.*;

public interface Cell {

    public double getValue(Environment e);

    public String getValueAsString(Environment e);

    public String getFormula();

}

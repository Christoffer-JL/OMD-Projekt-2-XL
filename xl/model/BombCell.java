package xl.model;

import xl.expr.Environment;

public class BombCell implements Cell {

    @Override
    public double getValue(Environment e) {
        throw new CircularReferenceException("Circular reference detected");
    }

    @Override
    public String getValueAsString(Environment e) {
        throw new CircularReferenceException("Circular reference detected");
    }

    @Override
    public String getFormula() {
        throw new CircularReferenceException("Circular reference detected");
    }

    public static class CircularReferenceException extends RuntimeException {
        public CircularReferenceException(String message) {
            super(message);
        }
    }
}

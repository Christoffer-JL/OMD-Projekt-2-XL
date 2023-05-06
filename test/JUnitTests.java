package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.BeforeClass;
import org.junit.Test;


import xl.model.Grid;

public class JUnitTests {

    private static Grid grid;

    @BeforeClass
    public static void setUp() {

        grid = new Grid();

    }

    @Test
    public void testGridValues() {
        grid.newFormula("A2", "3");
        grid.newFormula("A1", "A2+3");
        grid.newFormula("A2", "6");

        grid.newFormula("B4", "#Hej");
        grid.newFormula("C6", "B4 + 2");

        assertEquals(9.0, grid.value("A1"), 0);
        assertEquals("A2+3.00", grid.getCell("A1").getFormula());
        assertEquals(9.0, grid.getCell("A1").getValue(grid), 0);
        assertEquals("9.0", grid.getCell("A1").getValueAsString(grid));
        assertEquals("2.0", grid.getCell("C6").getValueAsString(grid));
    }
    
    @Test
    public void testCircularReference() {
        grid.newFormula("A2", "A3");
        grid.newFormula("A1", "A2+3");
        grid.newFormula("A3", "A2+3");
        Exception exception = assertThrows(Exception.class, () -> grid.newFormula("A3", "A2+3"));
        assertEquals("Circular reference detected", exception.getMessage());
    }

}

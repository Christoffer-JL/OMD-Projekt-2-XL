package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import xl.model.BombCell;
import xl.model.Grid;
import xl.util.XLException;

public class JUnitTests {

    private static Grid grid;

    @BeforeClass
    public static void setUp() throws IOException {
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        grid = new Grid();
    }

    @AfterClass
    public static void tearDown() throws IOException {
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
    public void testCircularReference() throws IOException {
        grid.newFormula("A2", "A3");
        grid.newFormula("A1", "A2+3");
        Exception exception = assertThrows(BombCell.CircularReferenceException.class, () -> {
            grid.newFormula("A3", "A2+3");
        });

        assertEquals("Circular reference detected", exception.getMessage());
   
    }

    @Test
    public void testDivisionByZero() throws IOException {
        Exception exception = assertThrows(XLException.class, () -> {
            grid.newFormula("B1", "1/0");
        });

        assertEquals("division by zero", exception.getMessage());

        grid.newFormula("B1", "0");
        grid.newFormula("A1", "1-A3");
        grid.newFormula("A2", "5/A1");

         exception = assertThrows(XLException.class, () -> {
            grid.newFormula("A3", "1");
        });
       
        assertEquals("division by zero", exception.getMessage());


    }

}

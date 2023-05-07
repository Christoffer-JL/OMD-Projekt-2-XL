package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.hamcrest.core.IsInstanceOf;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import xl.model.BombCell;
import xl.model.Grid;
import xl.model.BombCell.CircularReferenceException;
import xl.util.XLException;

public class JUnitTests {

    private static Grid grid;

    @BeforeClass
    public static void setUp() throws IOException {
       // System.setOut(new PrintStream(new ByteArrayOutputStream()));
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

        try{
            grid.newFormula("A3", "A2+3");
            fail("No exception thrown");
        }
        catch(BombCell.CircularReferenceException e) {
            assertEquals("Circular reference detected", e.getMessage());
        }
    }

    @Test
    public void testDivisionByZero() throws IOException {

        try {
            grid.newFormula("B1", "1/0");
            fail("No exception thrown");
        }

        catch(XLException e) {
            assertEquals("division by zero", e.getMessage());
        }

        grid.newFormula("B1", "0");
        grid.newFormula("A1", "1-A3");
        grid.newFormula("A2", "5/A1");

        try {
            grid.newFormula("A3", "1");
            fail("No exception thrown");
        }

        catch(XLException e) {
            assertEquals("division by zero", e.getMessage());
        }

        try {
            grid.newFormula("A3", "1");
            fail("No exception thrown");
        }

        catch(XLException e) {
            assertEquals("division by zero", e.getMessage());
        }


    }

    @Test
    public void testCellReplacedAfterException() {

        grid.newFormula("A1", "23");
        try {
        grid.newFormula("A1", "1/0");
        fail("No exception thrown");
        }
        catch(Exception e) {}
        assertEquals(23, grid.getCell("A1").getValue(grid), 0);
    }

}

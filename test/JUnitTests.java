package test;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import xl.model.Grid;

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
    public void testCircularReference() {

        grid.newFormula("A2", "A3");
        grid.newFormula("A1", "A2+3");

        grid.newFormula("A3", "A2+3");

    }

    @Test
    public void testDivisionByZero() {

        grid.newFormula("B1", "12");
        grid.newFormula("B1", "1/0");

        assertEquals(12, grid.getCell("B1").getValue(grid), 0);

        grid.newFormula("A1", "1-A3");
        grid.newFormula("A2", "5/A1");

        grid.newFormula("A3", "1");

        assertEquals(0, grid.getCell("A3").getValue(grid), 0);

    }

    @Test
    public void testCellReplacedAfterException() {

        grid.newFormula("A1", "23");
        grid.newFormula("A1", "1/0");

        assertEquals(23, grid.getCell("A1").getValue(grid), 0);
    }

    @Test
    public void insertGibberishCellValue() {

        grid.newFormula("A1", "}\\61§lol");

        assertEquals(0, grid.getCell("A1").getValue(grid), 0);

    }

    @Test
    public void insertGibberishCellAddress() {

        grid.newFormula("lol", "1");
        grid.newFormula("S1", "1");
        grid.newFormula("A11", "1");

    }

}

package xl.model;

public class testingClass {

    public static void main(String[] args)  {
        
        Grid grid = new Grid();
        grid.newFormula("A2", "3");
        grid.newFormula("A1", "A2+3");
        grid.newFormula("A2", "6");

        grid.newFormula("B4", "#Hej");
        grid.newFormula("C6", "B4 + 2");

       System.out.println("val:   " + grid.value("A1"));
       System.out.println("val2:   " + grid.getCell("A1").getFormula());
       System.out.println("val3:   " + grid.getCell("A1").getValue(grid));
       System.out.println("val4:   " + grid.getCell("A1").getValueAsString(grid));
       
       System.out.println("val4:   " + grid.getCell("C6").getValueAsString(grid));

    }
    
}

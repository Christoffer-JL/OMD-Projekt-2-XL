package xl.model;

public class testingClass {

    public static void main(String[] args)  {
        
        Grid grid = new Grid();
        grid.newFormula("A2", "3");
        grid.newFormula("A1", "A2+3");
        grid.newFormula("A2", "6");
       System.out.println("val:   " + grid.value("A1"));
       

    }
    
}

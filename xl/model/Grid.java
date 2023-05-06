
package xl.model;

import xl.expr.*;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;

import xl.expr.Environment;

public class Grid extends Observable implements Environment{

		private Map<String, Cell> grid;
		
		
		
	    public Grid() {
	    grid=new HashMap<String, Cell>();
	  }

	   
	    public Cell getCell(String cellAddress) {
	        if(grid.containsKey(cellAddress)) {
	        	return grid.get(cellAddress);
	        }
			return null;
	    }

	   
	    public void clearAllCells() {
	    	grid.clear();
	    	//not sure how observable should be implemeneted.. we should somehow notify GUI of the changes done 
	    	setChanged();
	    	notifyObservers();
	    }
	  
	 

	    public void clearCell(String cellAddress) {
	     if(grid.containsKey(cellAddress)) {
	    	 grid.remove(cellAddress);
	    	setChanged();
	    	notifyObservers();
	     }
	    }

	    public String display(String cellAddress) {
	    	if(getCell(cellAddress) == null) {
	    		throw new NoSuchElementException();
	    	}
	        return null;
			//return getCell(cellAddress).getValueAsString();
	    }

	    
	    public String displayFormula(String cellAddress) {
	    	if(getCell(cellAddress) == null) {
	    		throw new NoSuchElementException();
	    	}
	    	return getCell(cellAddress).getFormula();
	    }
	    
	    
	    
	    public void newFormula(String cellAddress, String newFormula) {

	    	//temp code för testning
			ExprCell newCell = new ExprCell(this, newFormula);
			grid.put(cellAddress, newCell);
	    	
	    	
	    }

	     
	    
	    public void loadFile(String fileName) {
	    	try {
				XLBufferedReader loadfile= new XLBufferedReader(fileName);
				loadfile.load(grid);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	    }

	    
	    public void saveFile(String fileName) {
	    	try (XLPrintStream savefile = new XLPrintStream(fileName)) {
				savefile.save(grid.entrySet());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	    }


	    @Override
	    public void setChanged() {

	    }

	    @Override
	    public void notifyObservers(Object o) {

	    }


		@Override
		public double value(String name) {
			System.out.println("sfsef " + name);
			return grid.get(name).getValue(this);
		}

	}

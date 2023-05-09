
package xl.model;

import xl.expr.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;

import xl.expr.Environment;
import xl.util.XLException;

@SuppressWarnings("deprecation")
public class Grid extends Observable implements Environment {

	private Map<String, Cell> grid;
	private CellFactory fact = new CellFactory();
	private String selected = "A1";

	public Grid() throws IOException {
		grid = new HashMap<String, Cell>();
		fact = new CellFactory();

		for (char c = 'A'; c <= 'H'; c++) {
			for (int i = 1; i <= 10; i++) {
				grid.put("" + c + i, fact.buildCell("0"));
			}
		}

	}

	public Cell getCell(String cellAddress) {
		if (grid.containsKey(cellAddress)) {
			return grid.get(cellAddress);
		}
		return null;
	}

	public void selectCell(String cellAddress) {
		selected = cellAddress;
		setChanged();
		notifyObservers(cellAddress);
	}

	public Cell getSelectedCell() {
		return grid.get(selected);
	}

	public String getSelectedCellAddress() {
		return selected;
	}

	public void clearAllCells() {
		grid.clear();
		// not sure how observable should be implemeneted.. we should somehow notify GUI
		// of the changes done
		setChanged();
		notifyObservers();
	}

	public void clearCell(String cellAddress) {
		if (grid.containsKey(cellAddress)) {
			grid.remove(cellAddress);
			setChanged();
			notifyObservers();
		}
	}

	public String display(String cellAddress) {
		if (getCell(cellAddress) == null) {
			throw new NoSuchElementException();
		}
		return null;
		// return getCell(cellAddress).getValueAsString();
	}

	public String displayFormula(String cellAddress) {
		if (getCell(cellAddress) == null) {
			throw new NoSuchElementException();
		}
		return getCell(cellAddress).getFormula();
	}

	public void statusUpdate(String message) {
		setChanged();
		notifyObservers("Error: " + message);
	}

	public void newFormula(String cellAddress, String newFormula) {

		// Om input-addressen inte finns, return (Ska ej kunna hända)
		if (!(grid.containsKey(cellAddress)))
			return;

		// tempCell kommer hålla det gamla värdet av cellen som ska ersättas. Om cellen
		// ej kan ersätta pga exception så återställs cellen tillbaka till gammalt värde
		Cell tempCell = null;
		try {
			tempCell = fact.buildCell(grid.get(cellAddress).getFormula());
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Cell newCell = null;
			grid.put(cellAddress, new BombCell());
			try {
				newCell = fact.buildCell(newFormula);
			} catch (IOException e) {
				// notifiera GUI om fel i input newFormula
				statusUpdate(e.getMessage());
			}

			// Värdet kollas för att upptäcka cirkulära referenser
			newCell.getValue(this);
			// Om cirkulära referenser ok, sätt in värdet i cellen
			grid.put(cellAddress, newCell);

			// Loop för att upptäcka division by zero
			for (char c = 'A'; c <= 'H'; c++) {
				for (int i = 1; i <= 10; i++) {
					if (!("" + c + i).equals(cellAddress))
						grid.get("" + c + i).getValue(this);
				}
			}

			// Om inget fel upptäckts, notifieras alla SlotLabel-objek att uppdateras
			setChanged();
			notifyObservers(newCell.getValueAsString(this));

		}

		catch (Exception e) {
			// Skicka e.getMessage() till statusLabel
			statusUpdate(e.getMessage());

			grid.put(cellAddress, tempCell);
		}

	}

	public void loadFile(String fileName) {
		try {
			XLBufferedReader loadfile = new XLBufferedReader(fileName);
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
	public double value(String name) {
		return grid.get(name).getValue(this);
	}

}

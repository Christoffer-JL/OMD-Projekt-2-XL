
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
	private String status = "";

	public Grid() throws IOException {
		grid = new HashMap<String, Cell>();
		fact = new CellFactory();

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
		notifyObservers("updateSelectedCell");
	}

	public Cell getSelectedCell() {
		return grid.get(selected);
	}

	public String getSelectedCellAddress() {
		return selected;
	}

	public String getStatus() {
		return status;
	}

	public void clearAllCells() throws IOException {

		for (char c = 'A'; c <= 'H'; c++) {
			for (int i = 1; i <= 10; i++) {
				grid.remove("" + c + i);
			}
		}
		setChanged();
		notifyObservers("updateSlotLabels");
		statusUpdate("");

	}

	public void clearCell(String cellAddress) {
		grid.remove(cellAddress);

		setChanged();
		notifyObservers("updateSlotLabels");
		statusUpdate("");

	}

	public String display(String cellAddress) {
		if (getCell(cellAddress) == null) {
			return "";
		}
		return null;
		// return getCell(cellAddress).getValueAsString();
	}

	public String displayFormula(String cellAddress) {
		if (getCell(cellAddress) == null) {
			return "";
		}
		return getCell(cellAddress).getFormula();
	}

	public void statusUpdate(String message) {

		if (message.length() > 0)
			message = "Error: " + message;

		this.status = message;

		setChanged();
		notifyObservers("updateStatus");
	}

	public void newFormula(String cellAddress, String newFormula) {

		// tempCell kommer hålla det gamla värdet av cellen som ska ersättas. Om cellen
		// ej kan ersätta pga exception så återställs cellen tillbaka till gammalt värde
		Cell tempCell = null;

		if (grid.containsKey(cellAddress)) {
			try {
				tempCell = fact.buildCell(grid.get(cellAddress).getFormula());
			} catch (IOException e) {
			}
		}

		if (newFormula.isBlank()) {
			clearCell(cellAddress);
			return;
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
					if ((!("" + c + i).equals(cellAddress)) && grid.containsKey("" + c + i))
						grid.get("" + c + i).getValue(this);

				}
			}
			// Om inget fel upptäckts, notifieras alla SlotLabel-objek att uppdateras
			setChanged();
			notifyObservers("updateSlotLabels");
			statusUpdate("");

		}

		catch (Exception e) {
			// Skicka e.getMessage() till statusLabel

			if (tempCell != null)
				grid.put(cellAddress, tempCell);

			else
				clearCell(cellAddress);

			statusUpdate(e.getMessage());
		}

	}

	public void loadFile(String fileName) {
		XLBufferedReader loadfile = null;
		try {
			loadfile = new XLBufferedReader(fileName);
			loadfile.load(grid);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				loadfile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			setChanged();
			notifyObservers("updateSelectedCell");
			setChanged();
			notifyObservers("updateSlotLabels");

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
		if (grid.containsKey(name))
			return grid.get(name).getValue(this);
		else
			return 0;
	}

}

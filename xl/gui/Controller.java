package xl.gui;

import java.awt.Color;
import java.util.Observable;

public class Controller extends Observable {

    private SlotLabel currentLabel;

    public Controller() {

    }

    public void save() {

    }

    public void load() {

    }

    public void newFormula(String newFormula) {

    }

    public void changeFocus(SlotLabel label, String address) {
        if (currentLabel != null)
            currentLabel.setBackground(Color.WHITE);
        currentLabel = label;
        currentLabel.setBackground(Color.YELLOW);

        setChanged();
        notifyObservers(address);

    }

    public void statusUpdate() {

    }

    public void clearAllCells() {

    }

    public void clearCells(String cellAddress) {

    }

}

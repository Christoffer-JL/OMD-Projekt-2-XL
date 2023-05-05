
package xl.model;

import java.util.Observable;
import java.util.Observer;

public class Grid extends Observable {

    public Grid() {

    }

    public Cell getCell(String cellAddress) {
        return null;
    }

    public void clearAllCells() {

    }

    public void clearCell(String cellAddress) {
   
    }

    public String display(String cellAddress) {
        return "";
    }

    public String formula(String cellAddress) {
        return "";
    }

    public void newFormula(String cellAddress, String newFormula) {

    }

    public void loadFile(String fileName) {

    }

    public void saveFile(String fileName) {

    }

    @Override
    public void addObserver(Observer ob) {

    }

    @Override
    public void setChanged() {

    }

    @Override
    public void notifyObservers(Object o) {

    }

}

package xl.gui;

import xl.model.Grid;

public class Controller {

    private Grid grid;

    public Controller(Grid grid) {
        this.grid = grid;
    }

    public void save() {

    }

    public void load() {

    }

    private String getCurrentCellAddress() {
        return grid.getSelectedCellAddress();
    }

    public void newFormula(String newFormula) {
        grid.newFormula(getCurrentCellAddress(), newFormula);
    }

    public void changeFocus(String cellAddress) {
        grid.selectCell(cellAddress);
    }

    public void statusUpdate() {

    }

    public void clearAllCells() {

    }

    public void clearCells(String cellAddress) {

    }

}
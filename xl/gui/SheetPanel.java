package xl.gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

import xl.model.Grid;

public class SheetPanel extends BorderPanel {

    public SheetPanel(int rows, int columns, Controller controller, Grid grid, Editor editor) {

        SlotLabels slots = new SlotLabels(rows, columns, controller);
        grid.addObserver(slots);
        grid.addObserver(editor);

        add(WEST, new RowLabels(rows));
        add(CENTER, slots);
    }
}

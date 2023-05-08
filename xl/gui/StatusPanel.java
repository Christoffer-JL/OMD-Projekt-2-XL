package xl.gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

import xl.model.Grid;

public class StatusPanel extends BorderPanel {

    protected StatusPanel(StatusLabel statusLabel, Grid grid) {
        CurrentLabel insertLabel = new CurrentLabel();
        add(WEST, insertLabel);
        add(CENTER, statusLabel);

        grid.addObserver(insertLabel);
    }
}

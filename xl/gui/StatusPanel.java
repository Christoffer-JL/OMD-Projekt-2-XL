package xl.gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

public class StatusPanel extends BorderPanel {

    protected StatusPanel(StatusLabel statusLabel, Controller c) {
        CurrentLabel insertLabel = new CurrentLabel();
        c.addObserver(insertLabel);
        add(WEST, insertLabel);
        add(CENTER, statusLabel);
    }
}

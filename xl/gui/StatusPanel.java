package xl.gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

public class StatusPanel extends BorderPanel {

    protected StatusPanel(StatusLabel statusLabel) {
        CurrentLabel insertLabel = new CurrentLabel();
        add(WEST, insertLabel);
        add(CENTER, statusLabel);
    }
}

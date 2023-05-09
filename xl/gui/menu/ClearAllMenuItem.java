package xl.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import xl.gui.Controller;


class ClearAllMenuItem extends JMenuItem implements ActionListener {
    private Controller controller;

    public ClearAllMenuItem(Controller controller) {
        super("Clear all");
        addActionListener(this);
        this.controller=controller;
    }

    public void actionPerformed(ActionEvent e) {
       controller.clearAllCells();
    }
}

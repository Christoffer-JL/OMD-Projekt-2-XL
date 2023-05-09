package xl.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JMenuItem;
import xl.gui.Controller;

class ClearAllMenuItem extends JMenuItem implements ActionListener {
    private Controller controller;

    public ClearAllMenuItem(Controller controller) {
        super("Clear all");
        addActionListener(this);
        this.controller = controller;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            controller.clearAllCells();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}

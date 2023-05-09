package xl.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import xl.gui.Controller;
import xl.model.Grid;


class ClearMenuItem extends JMenuItem implements ActionListener {
    private Controller controller;
    private Grid grid;

    public ClearMenuItem(Controller controller, Grid grid) {
        super("Clear");
        addActionListener(this);
        this.controller = controller;
        this.grid=grid;

    }

    public void actionPerformed(ActionEvent e) {
        controller.clearCells(grid.getSelectedCellAddress());
    }
}

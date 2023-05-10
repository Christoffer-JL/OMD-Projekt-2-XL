package xl.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import xl.gui.Controller;
import xl.gui.Editor;
import xl.model.Grid;


class ClearMenuItem extends JMenuItem implements ActionListener {
    private Controller controller;
    private Grid grid;
    private Editor editor;

    public ClearMenuItem(Controller controller, Grid grid, Editor editor) {
        super("Clear");
        addActionListener(this);
        this.controller = controller;
        this.grid=grid;
        this.editor=editor;

    }

    public void actionPerformed(ActionEvent e) {
        controller.clearCells(grid.getSelectedCellAddress());
        editor.setText(null);
    }
}

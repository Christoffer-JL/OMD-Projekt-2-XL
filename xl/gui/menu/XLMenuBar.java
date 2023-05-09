package xl.gui.menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import xl.gui.Controller;
import xl.gui.StatusLabel;
import xl.gui.XL;
import xl.gui.XLList;
import xl.model.Grid;

public class XLMenuBar extends JMenuBar {

    public XLMenuBar(XL xl, XLList xlList, StatusLabel statusLabel, Controller controller, Grid grid) {
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        file.add(new SaveMenuItem(xl, statusLabel));
        file.add(new LoadMenuItem(xl, statusLabel));
        file.add(new NewMenuItem(xl));
        file.add(new CloseMenuItem(xl, xlList));
        edit.add(new ClearMenuItem(controller, grid));
        edit.add(new ClearAllMenuItem(controller));
        add(file);
        add(edit);
        add(new WindowMenu(xlList));
    }
}

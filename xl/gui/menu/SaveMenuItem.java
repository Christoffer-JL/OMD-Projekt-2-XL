package xl.gui.menu;

import java.io.FileNotFoundException;
import xl.gui.Controller;
import javax.swing.JFileChooser;
import xl.gui.StatusLabel;
import xl.gui.XL;

class SaveMenuItem extends OpenMenuItem {
    private Controller controller;


    public SaveMenuItem(XL xl, StatusLabel statusLabel, Controller controller) {
        super(xl, statusLabel, "Save");
        this.controller=controller;
    }

    protected void action(String path) throws FileNotFoundException {
        controller.save(path+".xl");
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showSaveDialog(xl);
    }
}

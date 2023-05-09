package xl.gui.menu;

import java.io.FileNotFoundException;
import xl.gui.Controller;

import javax.swing.JFileChooser;
import xl.gui.StatusLabel;
import xl.gui.XL;

class LoadMenuItem extends OpenMenuItem {

    private Controller controller;


    public LoadMenuItem(XL xl, StatusLabel statusLabel, Controller controller) {
        super(xl, statusLabel, "Load");
        this.controller=controller;
    }

    protected void action(String path) throws FileNotFoundException {
       controller.load(path);
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showOpenDialog(xl);
    }
}

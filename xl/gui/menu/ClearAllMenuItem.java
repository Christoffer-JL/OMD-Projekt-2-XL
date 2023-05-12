package xl.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JMenuItem;
import xl.gui.Controller;
import xl.gui.Editor;

class ClearAllMenuItem extends JMenuItem implements ActionListener {
    private Controller controller;
    private Editor editor;

    public ClearAllMenuItem(Controller controller, Editor editor) {
        super("Clear all");
        addActionListener(this);
        this.controller = controller;
        this.editor = editor;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            controller.clearAllCells();
            editor.setText(null);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}

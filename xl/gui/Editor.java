package xl.gui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

public class Editor extends JTextField implements Observer {

    public Editor() {
        setBackground(Color.WHITE);
    }

    @Override
    public void update(Observable o, Object arg) {
    }

}
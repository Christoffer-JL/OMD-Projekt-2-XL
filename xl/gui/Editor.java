package xl.gui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

public class Editor extends JTextField implements Observer {

    public Editor() {
        setBackground(Color.WHITE);
    }

    public void update() {
        // Kollar värdet i textfältet och skickar vidare
    }

    public void checkEnter() {
        // Kollar om Enter klickats i
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}

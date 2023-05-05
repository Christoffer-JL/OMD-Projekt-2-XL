package xl.gui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

public class Editor extends JTextField implements Observer {

    private Controller c;

    public Editor(Controller c) {
        setBackground(Color.WHITE);
        this.c = c;
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

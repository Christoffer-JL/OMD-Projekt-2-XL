package xl.gui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

public class CurrentLabel extends ColoredLabel implements Observer {

    public CurrentLabel() {
        super("A1", Color.YELLOW);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!(arg instanceof String))
            return;

        setText((String) arg);
        System.out.print("Test");
    }

}
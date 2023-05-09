package xl.gui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import xl.model.Grid;

public class StatusLabel extends ColoredLabel implements Observer {

    public StatusLabel() {
        super("", Color.WHITE);
    }

    public void update(Observable o, Object arg) {

        if (!((String) arg).equals("updateStatus"))
            return;

        Grid g = (Grid) o;

        setText(g.getStatus()); // :)
    }
}

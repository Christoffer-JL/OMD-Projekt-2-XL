package xl.gui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

public class StatusLabel extends ColoredLabel implements Observer {

    private Controller c;

    public StatusLabel(Controller c) {
        super("", Color.WHITE);
        this.c = c;
    }

    public void update(Observable observable, Object arg) {

    }
}

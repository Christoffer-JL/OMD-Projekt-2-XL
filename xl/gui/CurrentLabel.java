package xl.gui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import xl.model.Grid;

@SuppressWarnings("deprecation")
public class CurrentLabel extends ColoredLabel implements Observer {

    public CurrentLabel() {
        super("A1", Color.YELLOW);
    }

    // Då currentCell uppdateras i Grid skickas denna uppdatering hit med addressen
    // som parameter
    @Override
    public void update(Observable o, Object arg) {

        if (!((String) arg).equals("updateSelectedCell"))
            return;

        Grid g = (Grid) o;
        setText(g.getSelectedCellAddress());

    }

}
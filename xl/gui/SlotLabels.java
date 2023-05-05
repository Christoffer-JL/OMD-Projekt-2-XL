package xl.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingConstants;

public class SlotLabels extends GridPanel implements Observer {

    private List<SlotLabel> labelList;

    private Controller c;

    public SlotLabels(int rows, int cols, Controller c) {
        super(rows + 1, cols);
        this.c = c;
        labelList = new ArrayList<SlotLabel>(rows * cols);
        for (char ch = 'A'; ch < 'A' + cols; ch++) {
            add(new ColoredLabel(Character.toString(ch), Color.LIGHT_GRAY, SwingConstants.CENTER));
        }
        for (int row = 1; row <= rows; row++) {
            for (char ch = 'A'; ch < 'A' + cols; ch++) {
                SlotLabel label = new SlotLabel(c, "" + ch + row);
                add(label);
                labelList.add(label);
            }
        }
        SlotLabel firstLabel = labelList.get(0);
        c.changeFocus(firstLabel, "A1");
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
    }
}
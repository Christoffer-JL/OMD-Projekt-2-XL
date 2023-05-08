package xl.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingConstants;

public class SlotLabels extends GridPanel implements Observer {

    private List<SlotLabel> labelList;
    private Controller controller;
    private SlotLabel currentLabel;

    public SlotLabels(int rows, int cols, Controller controller) {
        super(rows + 1, cols);
        this.controller = controller;
        labelList = new ArrayList<SlotLabel>(rows * cols);
        for (char ch = 'A'; ch < 'A' + cols; ch++) {
            add(new ColoredLabel(Character.toString(ch), Color.LIGHT_GRAY, SwingConstants.CENTER));
        }
        for (int row = 1; row <= rows; row++) {
            for (char ch = 'A'; ch < 'A' + cols; ch++) {
                SlotLabel label = new SlotLabel("" + ch + row);
                add(label);
                labelList.add(label);
                label.addMouseListener(new SlotLabelMouseListener());
            }
        }
        currentLabel = labelList.get(0);
        currentLabel.setBackground(Color.YELLOW);
    }

    private class SlotLabelMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {

            currentLabel.setBackground(Color.WHITE);
            SlotLabel label = (SlotLabel) e.getSource();
            String labelValue = label.getAddress();
            controller.changeFocus(labelValue);
            currentLabel = label;
            currentLabel.setBackground(Color.YELLOW);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!(arg instanceof String))
            return;

        String newText = (String) arg;
        currentLabel.setText(newText);

    }

}
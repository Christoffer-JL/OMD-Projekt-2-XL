package xl.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import xl.model.Grid;

import javax.swing.SwingConstants;

import xl.model.Grid;

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

    // Varje label får en onClick listener. Vid klick uppdateras färgen av den gamla
    // rutan (mha currentLabel) till vit, och den nya till gul. Addressen till
    // labelen skickas även vidare till controllern
    private class SlotLabelMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {

            currentLabel.setBackground(Color.WHITE);
            SlotLabel label = (SlotLabel) e.getSource();
            String labelValue = label.getAddress();
            controller.changeFocus(labelValue);
            currentLabel = label;
            currentLabel.setBackground(Color.YELLOW);
            controller.statusUpdate("");

            // controller.statusUpdate(""); //ska rensa eventuella statusmeddelanden, men
            // detta blir galet
        }
    }

    // När någon formula i Grid uppdateras, kommer även varje label att uppdateras
    @Override
    public void update(Observable o, Object arg) {
        if (!((String) arg).equals("updateSlotLabels"))
            return;
        ;
        Grid g = (Grid) o;

        for (SlotLabel lbl : labelList) {

            if (!(g.getCell(lbl.getAddress()) == null)) {
                String cellAddress = g.getCell(lbl.getAddress()).getValueAsString(g);

                lbl.setText(cellAddress);
            } else
                lbl.setText("");
        }

    }

}
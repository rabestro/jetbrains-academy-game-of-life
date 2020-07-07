package life;

import javax.swing.*;
//import java.awt.*;

public class OptionsPanel extends JPanel {
    private final JLabel generationLabel;
    private final JLabel aliveLabel;

    public OptionsPanel() {
        //setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        //setPreferredSize(new Dimension(500, 300));

        generationLabel = new JLabel();
        generationLabel.setName("GenerationLabel");
        add(generationLabel);

        aliveLabel = new JLabel();
        aliveLabel.setName("AliveLabel");
        add(aliveLabel);
    }

    public void setGenerationLabel(int generation) {
        generationLabel.setText("Generation #" + generation);
    }

    public void setAliveLabel(int alive) {
        aliveLabel.setText("Alive : " + alive);
    }
}

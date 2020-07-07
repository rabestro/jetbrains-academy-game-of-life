package life;

import javax.swing.*;
import java.awt.*;
//import java.awt.*;

public class OptionsPanel extends JPanel {
    private final JLabel generationLabel;
    private final JLabel aliveLabel;
    private final JToggleButton pauseButton;
    private final JLabel speedLabel;
    private final JSlider speedSlider;
    private final JColorChooser colorChooser;
    private final JTextField fieldSize;
    private boolean pause = false;
    private boolean reset = false;

    public OptionsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(500, 300));

        final var dimension = new Dimension(150, 25);
        pauseButton = new JToggleButton("Pause");
        pauseButton.setName("PlayToggleButton");
        pauseButton.setPreferredSize(dimension);
        pauseButton.addItemListener(e -> pauseButton.setText(
                pauseButton.getText().equals("Pause") ? "Resume" : "Pause")
        );
        add(pauseButton);

        final var resetButton = new JButton("Reset");
        resetButton.setName("ResetButton");
        resetButton.setPreferredSize(dimension);
        resetButton.addActionListener(actionEvent -> reset = !reset);
        add(resetButton);

        add(new JLabel("Enter field size : "));
        fieldSize = new JTextField();
        fieldSize.setPreferredSize(dimension);
        fieldSize.setMaximumSize(dimension);
        add(fieldSize);

        generationLabel = new JLabel();
        generationLabel.setName("GenerationLabel");
        add(generationLabel);

        aliveLabel = new JLabel();
        aliveLabel.setName("AliveLabel");
        add(aliveLabel);

        speedLabel = new JLabel("Speed mode : 5");
        add(speedLabel);
        speedSlider = new JSlider();
        speedSlider.setMaximum(10);
        speedSlider.setMinimum(1);
        speedSlider.setValue(5);
        speedSlider.addChangeListener(changeEvent ->
                speedLabel.setText("Speed mode : " + speedSlider.getValue()));


        add(new JLabel("Choose color : "));
        colorChooser = new JColorChooser();
        colorChooser.setColor(Color.BLACK);
        add(colorChooser);
    }

    public void setGenerationLabel(int generation) {
        generationLabel.setText("Generation #" + generation);
    }

    public void setAliveLabel(int alive) {
        aliveLabel.setText("Alive : " + alive);
    }

    public Color getColor() {
        return this.colorChooser.getColor();
    }

    public int getFieldSize() {
        int size = 50;
        try {
            size = Integer.parseInt(fieldSize.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return size;
    }

    public boolean getPause() {
        return pause;
    }

    public int waitTime() {
        return 5000 / speedSlider.getValue();
    }

    public boolean getReset() {
        if (reset) {
            reset = false;
            return true;
        }
        return false;
    }
}

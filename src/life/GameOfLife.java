package life;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {
    private final OptionsPanel optionsPanel;
    private Universe board;
    private int generation = 1;

    public GameOfLife() {
        super("Game of life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        optionsPanel = new OptionsPanel();
//        this.board = Board.createRandom(150);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
        setSize(300, 300);
        this.board = new Universe(150);
        setVisible(true);
        updateVisuals();
    }

    private void updateVisuals() {
        this.getContentPane().removeAll();
        optionsPanel.setAliveLabel(board.getAliveCount());
        optionsPanel.setGenerationLabel(generation);
        this.add(optionsPanel);
        this.add(new BoardPanel(board, Color.BLACK));
        this.repaint();
        this.pack();
        this.validate();
        this.setVisible(true);
    }
}
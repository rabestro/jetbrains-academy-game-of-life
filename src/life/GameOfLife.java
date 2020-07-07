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
        board = new Universe(150);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
        setSize(300, 300);
        this.board = new Universe(150);
        setVisible(true);
        updateVisuals();
    }

    public void nextGeneration() {
        Evolution t = new Evolution(board);
        t.start();
        updateVisuals();
        System.out.println(this);
        try {
            Thread.sleep(optionsPanel.waitTime());
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        generation++;
        this.board = t.getNextGeneration();
    }

    private void resetGame() {
        this.board = new Universe(optionsPanel.getFieldSize());
        this.generation = 1;
    }

    public void advanceGenerations() {
        while (true) {
            if (optionsPanel.getPause()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            nextGeneration();
            if (optionsPanel.getReset()) {
                this.resetGame();
            }
        }
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

    @Override
    public String toString() {
        return String.format("Generation #%d\nAlive: %d\n%s", generation, board.getAliveCount(), board);
    }
}
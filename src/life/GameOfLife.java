package life;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class GameOfLife extends JFrame {
    private static final Logger log = Logger.getLogger(GameOfLife.class.getName());

    private final OptionsPanel optionsPanel;
    private Universe board;
    private int generation = 1;

    public GameOfLife() {
        super("Game of life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        board = new Universe(150);
        board.initialise(1000);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
        setSize(300, 300);
        optionsPanel = new OptionsPanel();
        setVisible(true);
        updateVisuals();
    }

    public void nextGeneration() {
        Evolution t = new Evolution(board);
        t.start();
        updateVisuals();
        log.finer(this::toString);
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
        this.board = new Universe(optionsPanel.getFieldSize()).initialise();
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
        getContentPane().removeAll();
        optionsPanel.setAliveLabel(board.getAliveCount());
        optionsPanel.setGenerationLabel(generation);
        add(optionsPanel);
        add(new BoardPanel(board, optionsPanel.getColor()));
        repaint();
        pack();
        validate();
        setVisible(true);
    }

    @Override
    public String toString() {
        if (board == null) {
            return "Board is null";
        }
        return String.format("Generation #%d%nAlive: %d%n%s", generation, board.getAliveCount(), board);
    }
}
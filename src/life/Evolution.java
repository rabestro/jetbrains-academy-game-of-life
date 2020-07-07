package life;

public class Evolution extends Thread {
    private final Universe oldGeneration;
    private final Universe nextGeneration;

    public Evolution(Universe oldGeneration) {
        this.oldGeneration = oldGeneration;
        nextGeneration = new Universe(oldGeneration.getSize());
    }

    @Override
    public void run() {
        for (int row = 0; row < oldGeneration.getSize(); ++row) {
            for (int col = 0; col < oldGeneration.getSize(); ++col) {
                final int neighbours = oldGeneration.getNeighboursCount(row, col);
                final var isLive = neighbours == 3 || neighbours == 2 && oldGeneration.isAlive(row, col);
                nextGeneration.setCell(row, col, isLive);
            }
        }
    }

    public Universe getNextGeneration() {
        return nextGeneration;
    }
}

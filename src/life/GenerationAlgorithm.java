package life;

public class GenerationAlgorithm {
    static Universe getNextGeneration(Universe oldGeneration) {
        Universe nextGeneration = new Universe(oldGeneration.getSize());

        for (int row = 0; row < oldGeneration.getSize(); ++row) {
            for (int col = 0; col < oldGeneration.getSize(); ++col) {
                final int neighbours = oldGeneration.getNeighboursCount(row, col);
                final var isLive = neighbours == 3 || neighbours == 2 && oldGeneration.isAlive(row, col);
                nextGeneration.setCell(row, col, isLive);
            }
        }
        return nextGeneration;
    }
}
